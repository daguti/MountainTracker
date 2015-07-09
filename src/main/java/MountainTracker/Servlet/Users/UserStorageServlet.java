/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Users;

import MountainTracker.Beans.New;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.NewsPersistanceDAO;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import MountainTracker.Servlet.UploadUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "UserStorageServlet", urlPatterns = {"/userStorage"})
public class UserStorageServlet extends HttpServlet {
  public ServletFileUpload uploader = null;
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, FileUploadException, Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    try {
      UserPersistanceDAO dao = new UserPersistanceDAO();
      User user;
      
      if(request.getParameter("profile") != null) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        user = userDao.retrieveByUserUsername(name);
        String base64 = user.getProfilePhoto() != null ? "$%&" + getBase64(user.getProfilePhoto().getImage()) : null;
        String output = user.getUsername() + "$%&" + user.getPass() + "$%&" + user.getName() + "$%&" 
                      + user.getSurname() + "$%&" + new SimpleDateFormat("dd-MM-yyyy").format(user.getBirthday()) + "$%&" + user.getEmail() + "$%&" 
                      + user.getCity() + "$%&" + user.getCountry();
        if(base64 != null) output += base64;
        
        out.write(output);
        out.flush();
      } else {
        initDirectory();
        List<FileItem> fileItemsList = uploader.parseRequest(request);
        int i = 0;
        Photo img = null;
        String redPage;
        
        if(fileItemsList.get(0).getFieldName().equals("file")) {
          img = uploadPhoto(fileItemsList.get(i), request, response);
          i++;
        }
        user = dao.retrieveByUserUsername(fileItemsList.get(i).toString()) != null ? dao.retrieveByUserUsername(fileItemsList.get(i).toString()) : new User();
        if(img != null) user.setProfilePhoto(img);
        if(fileItemsList.get(i).getFieldName().equals("username")) {
          user.setUsername(fileItemsList.get(i).getString());
          i+=2;
          redPage = "login.jsp";
        } else {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          String name = auth.getName(); //get logged in username
          user.setUsername(name);
          redPage = "perfil.jsp";
        }
        user.setPass(fileItemsList.get(i).getString()); i++;
        user.setName(fileItemsList.get(i).getString()); i++; 
        user.setSurname(fileItemsList.get(i).getString()); i++;
        user.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse(fileItemsList.get(i).getString())); i++;
        user.setEmail(fileItemsList.get(i).getString()); i++;
        user.setCity(fileItemsList.get(i).getString()); i++;
        user.setCountry(fileItemsList.get(i).getString()); i++;
        user.setUserType(1);
        dao.storeUser(user);
        request.getRequestDispatcher(redPage).forward(request, response);
      }
    } catch(ParseException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      out.close();
    }
  }
  private void initDirectory() {
    DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
    fileFactory.setRepository(filesDir);
    this.uploader = new ServletFileUpload(fileFactory);
  }
  
    private Photo uploadPhoto(FileItem fileItem, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
      //Variable definition
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName();
      UserPersistanceDAO userDao = new UserPersistanceDAO();
      PhotoPersistanceDAO imgDao  = new PhotoPersistanceDAO();
      User user = userDao.retrieveByUserUsername(name);
      Photo img = new Photo();
      File file = null;
      boolean add = true;
      
      img.setUser(user);
      
      System.out.println("FieldName="+fileItem.getFieldName());
      System.out.println("FileName="+fileItem.getName());
      System.out.println("ContentType="+fileItem.getContentType());
      System.out.println("Size in bytes="+fileItem.getSize());
      
      if(fileItem.getSize() != 0) {
        img.setDesription("");
        file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
        System.out.println("Absolute Path at server="+file.getAbsolutePath());
        fileItem.write(file);
        img.setImage(Files.readAllBytes(Paths.get(file.getPath())));
        if(img.getUserProfileList() != null) img.getUserProfileList().add(user);
        else {
          Set<User>set = new HashSet<User>();
          set.add(user);
          img.setUserProfileList(set);
        }
        img.setUploadDate(new Date());
        
        imgDao.storePhoto(img);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        for(Photo im : imgDao.getPhotosByUsername(user)){
          if(fmt.format(im.getUploadDate()).equals(fmt.format(img.getUploadDate()))) return im;
        } 
        return null; 
      }
      return null;
  }
    
  private String getBase64(byte[] image) {
    //Variable definition
    StringBuilder sb = new StringBuilder();

    sb.append("data:image/png;base64,");
    sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(image, false)));
    
    return sb.toString();
  }
  
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (FileUploadException ex) {
      java.util.logging.Logger.getLogger(UserStorageServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(UserStorageServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (FileUploadException ex) {
      java.util.logging.Logger.getLogger(UserStorageServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(UserStorageServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}

