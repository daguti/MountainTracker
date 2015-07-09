/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet;

import MountainTracker.Beans.New;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.GpxFiles.GpxFileReader;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
public class UploadUtils {
  public void loadImages(HttpServletRequest request, List<FileItem> fileItemsList, New userNew) throws Exception {
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
      if(!fileItem.isFormField()) {
        img = new Photo();
        img.setUser(user);
        img.setUserNew(userNew);
        System.out.println("FieldName="+fileItem.getFieldName());
        System.out.println("FileName="+fileItem.getName());
        System.out.println("ContentType="+fileItem.getContentType());
        System.out.println("Size in bytes="+fileItem.getSize());
        img.setDesription("");
        file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
        System.out.println("Absolute Path at server="+file.getAbsolutePath());
        fileItem.write(file);
        img.setImage(Files.readAllBytes(Paths.get(file.getPath())));
        img.setUploadDate(new Date());
        imgList.add(img);
      }
    }
    dao.storePhotos(imgList);
    request.getSession().setAttribute("result", "ok");
  }
  
  public void loadRouteFile(HttpServletRequest request, List<FileItem> fileItemsList) throws Exception {
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
