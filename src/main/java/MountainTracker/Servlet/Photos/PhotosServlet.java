/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Photos;

import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "PhotosServlet", urlPatterns = {"/photos"})
public class PhotosServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Variable definition
    PhotoPersistanceDAO dao = new PhotoPersistanceDAO();
    List<Photo> imageList;
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      if(request.getParameter("mine") == null && request.getParameter("newId") == null) imageList = dao.getAllPhotos();
      else if(request.getParameter("newId") != null) {
        imageList = dao.getPhotosByNew(Integer.valueOf(request.getParameter("newId")));
      } else {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        User user;

        user = userDao.retrieveByUserUsername(name);
        imageList = dao.getPhotosByUsername(user);
      }
      /*if(request.getParameter("carousel") != null && imageList != null) {
        out.write(addImagesToCarousel(imageList));
      } else */if(imageList != null) {
        out.write(addImagesToGallery(imageList));
      }
    } finally {
      out.close();
    }
  }
  
  public String addImagesToGallery(List<Photo> imageList) {
    //Variable definition
    String html = "<ul class=\"row\">";
    
    for(Photo image : imageList) {
      html += "<li class=\"col-lg-2 col-md-2 col-sm-3 col-xs-4\">";
      html += "<img class=\"img-responsive\" src=\"" + getBase64Image(image) + "\">";
      html += "</li>";
    }
    html += "</ul>";
    return html;
  }
  
  private String getBase64Image(Photo image) {
    //Variable definition
    StringBuilder sb = new StringBuilder();
    
    sb.append("data:image/png;base64,");
    sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getImage(), false)));
    
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
    processRequest(request, response);
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
    processRequest(request, response);
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
