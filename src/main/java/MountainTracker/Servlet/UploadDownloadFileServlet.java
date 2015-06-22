package MountainTracker.Servlet;


import MountainTracker.Beans.New;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.GpxFiles.GpxFileReader;
import MountainTracker.Persistance.DAO.NewsPersistanceDAO;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
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

@WebServlet(name="UploadDownloadFileServlet", urlPatterns={"/UploadDownloadFileServlet"})
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletFileUpload uploader = null;
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
      UploadUtils upd = new UploadUtils();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      try {
        List<FileItem> fileItemsList = uploader.parseRequest(request);
        if(request.getSession().getAttribute("toNew") != null) {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          String name = auth.getName();
          UserPersistanceDAO userDao = new UserPersistanceDAO();
          NewsPersistanceDAO newDao  = new NewsPersistanceDAO();
          New newId = null;
          for(New userNew : newDao.getNewsByUsername(userDao.retrieveByUserUsername(name))) {
            if(newId == null || userNew.getNewId() > newId.getNewId()) newId = userNew;
          }
          upd.loadImages(request, fileItemsList, newId);
          request.getSession().setAttribute("toNew", null);
          request.getRequestDispatcher("/news.jsp").forward(request, response);
        } else if(request.getParameter("img") != null) {
          upd.loadImages(request, fileItemsList, null);
          request.getRequestDispatcher("/photos.jsp").forward(request, response);
        } else {
          upd.loadRouteFile(request, fileItemsList);
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
}

