/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Loaders;

import MountainTracker.Beans.Album;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "AlbumLoader", urlPatterns = {"/AlbumLoader"})
public class AlbumLoader extends HttpServlet {

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
    List<Album> albumList;
    PhotoPersistanceDAO dao = new PhotoPersistanceDAO();
    String htmlResponse = "";
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      if(request.getParameter("create") != null) {
        Album album = new Album();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        User user;

        user = userDao.retrieveByUserUsername(name);
        album.setOwner(user);
        album.setAlbumName(request.getParameter("albumName"));
        album.setPhotoList(new HashSet<Photo>());
        dao.storeAlbum(album);
        request.getRequestDispatcher("/myAlbums.jsp").forward(request, response);
      } else if(request.getParameter("photoList") != null) {
        String[] photos = request.getParameter("photoList").split(";");
        Album album = dao.getAlbumById(Integer.valueOf(request.getParameter("album_Id")));
        Photo photo;
        Set<Album> albumSet;
        for(String img : photos) {
          if(!img.equals("")) {
            photo = dao.getPhotoById(Integer.valueOf(img));
            if(photo != null) {
              if(photo.getAlbumList() != null) albumSet = photo.getAlbumList();
              else albumSet = new HashSet<Album>();
              albumSet.add(album);
              photo.setAlbumList(albumSet);
              dao.storePhoto(photo);
            }
          }
        }
      } else {
        if(request.getParameter("mine") != null) {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          String name = auth.getName(); //get logged in username
          UserPersistanceDAO userDao = new UserPersistanceDAO();
          User user;

          user = userDao.retrieveByUserUsername(name);
          albumList = dao.getUserAlbums(user);
        } else {
          albumList = dao.getAllAlbums();
        }

        htmlResponse += getAlbumHtmlTemplate(albumList);
        out.write(htmlResponse);
        out.flush();
      }
    } finally {
      out.close();
    }
  }

  private String getAlbumHtmlTemplate(List<Album> albumList) {
    //Variable definition
    String html = "<ul class=\"row\" style=\"margin-top:40px;\">";
    for(Album album : albumList) {
      html += "<li class=\"col-lg-2 col-md-2 col-sm-2 col-xs-3 albumListStyle\">";
      html += "<div class=\"container-fluid miniAlbum\"onClick=\"openAlbumPhotos(" + album.getRefAlbum() + ",'"+ album.getAlbumName() + "')\">";
      html += "<h4 class=\"albumName\">" + album.getAlbumName() + "</h4>";
      html += "</div>";
      html += "</li>";
    }
    
    html += "</ul>";

    return html;
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
