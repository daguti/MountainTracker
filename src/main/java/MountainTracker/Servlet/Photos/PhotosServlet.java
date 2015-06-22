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
      if(request.getParameter("carousel") != null && imageList != null) {
        out.write(addImagesToCarousel(imageList));
      } else if(imageList != null) {
        out.write(addImagesToGallery(imageList));
      }
    } finally {
      out.close();
    }
  }

  public String addImagesToCarousel(List<Photo> imageList) {
    //Variable definition
    String html = "<ol class=\"carousel-indicators\">";
    boolean fst = true;
    html += "<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>";
    for(int i = 1; i< imageList.size(); i++) html += "<li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\"></li>";
    html += "</ol>";
    html += "<div class=\"carousel-inner\">";
    for(Photo image : imageList) {
      if(fst)html += "<div class=\"item active\">";
      else html += "<div class=\"item\">";
      html += "<img src=\"" + getBase64Image(image) + "\">";
      html += "<div class=\"carousel-caption\">";
      html += "<p>" + image.getDesription() + "</p>";
      html += "<p>" + image.getUser().getUsername() + "</p>";
      html += "</div>";
      html += "</div>";
      fst = false;
    }
    return html;
  }
  
  public String addImagesToGallery(List<Photo> imageList) {
    //Variable definition
    String html = "<div class=\"row\">";
    int count = 0;
    
    for(Photo image : imageList) {
      if(count > 0 && count % 3 == 0) {
        html += "</div>";
        html += "<div class=\"row\">";
      }
      html += "<div class=\"col-md-4\">";
      html += "<a class=\"thumbnail\" data-toggle=\"modal\" data-target=\"#myModal\" data-local=\"#myCarousel\">";
      html += "<img src=\"" + getBase64Image(image) + "\" style=\"width:150px;height:150px\">";
      html += "<p>" + image.getDesription() + "</p> ";
      html += "</a>";
      html += "</div>";
      count++;
    }
    html += "</div>";
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
