package MountainTracker.Servlet;


import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.GpxFiles.GpxFileReader;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		if(fileName == null || fileName.equals("")){
			throw new ServletException("File Name can't be null or empty");
		}
		File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
		if(!file.exists()){
			throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::"+file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os       = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if(!ServletFileUpload.isMultipartContent(request)){
              throw new ServletException("Content type is not multipart/form-data");
      }

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      try {
        List<FileItem> fileItemsList = uploader.parseRequest(request);

        if(request.getParameter("img") != null) {
          loadImages(request, fileItemsList);
          request.getRequestDispatcher("/photos.jsp").forward(request, response);
        } else {
          loadRouteFile(request, fileItemsList);
          request.getRequestDispatcher("/routes.jsp").forward(request, response);
        }

      } catch (FileUploadException e) {
          System.out.println(e);
          out.write("Exception in uploading file.");
      } catch (Exception e) {
          System.out.println(e);
          out.write("Exception in uploading file.");
      } finally {
          out.close();
      }
	}
    
    private void loadImages(HttpServletRequest request, List<FileItem> fileItemsList) throws Exception {
      //Variable definition
      File file = null;
      List<Photo> imgList = new ArrayList<Photo>();
      Photo img;
      PhotoPersistanceDAO dao = new PhotoPersistanceDAO();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      UserPersistanceDAO userDao = new UserPersistanceDAO();
      User user;
    
      user = userDao.retrieveByUserUsername(name);
      for(FileItem fileItem : fileItemsList) {
        if(fileItem.getFieldName().equals("_csrf")) {
          break;
        }
        img = new Photo();
        img.setUser(user);
        System.out.println("FieldName="+fileItem.getFieldName());
        System.out.println("FileName="+fileItem.getName());
        System.out.println("ContentType="+fileItem.getContentType());
        System.out.println("Size in bytes="+fileItem.getSize());
        img.setDesription("");
        file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
        System.out.println("Absolute Path at server="+file.getAbsolutePath());
        fileItem.write(file);
        img.setImage(Files.readAllBytes(Paths.get(file.getPath())));
        imgList.add(img);
      }
      dao.storePhotos(imgList);
      request.getSession().setAttribute("result", "ok");
    }
    
    private void loadRouteFile(HttpServletRequest request, List<FileItem> fileItemsList) throws Exception {
      //Variable definition
      File file = null;
      GpxFileReader reader = new GpxFileReader();
      
      FileItem fileItem = fileItemsList.get(0);
      System.out.println("FieldName="+fileItem.getFieldName());
      System.out.println("FileName="+fileItem.getName());
      System.out.println("ContentType="+fileItem.getContentType());
      System.out.println("Size in bytes="+fileItem.getSize());

      file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
      System.out.println("Absolute Path at server="+file.getAbsolutePath());
      fileItem.write(file);
      reader.processFile(file);
      request.getSession().setAttribute("result", "ok");
    }
}

