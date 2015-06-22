/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.News;

import MountainTracker.Beans.New;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.NewsPersistanceDAO;
import MountainTracker.Persistance.DAO.PhotoPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import MountainTracker.Servlet.Photos.PhotosServlet;
import MountainTracker.Servlet.UploadDownloadFileServlet;
import MountainTracker.Servlet.UploadUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "NewsServlet", urlPatterns = {"/news"})
public class NewsServlet extends HttpServlet {
  
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
      throws ServletException, IOException, Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    NewsPersistanceDAO dao = new NewsPersistanceDAO();
    
    try {
      
      if(request.getParameter("crea") != null) {
        initDirectory();
        List<FileItem> fileItemsList = uploader.parseRequest(request);
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        User user;
        New cliNew = new New();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        
        user = userDao.retrieveByUserUsername(name);
        cliNew.setAuthor(user);
        cliNew.setTitle(fileItemsList.get(0).getString());
        cliNew.setText(fileItemsList.get(1).getString());
        cliNew.setWriteDate(new Date());
        dao.storeNew(cliNew);
        uploadPhotos(fileItemsList, request, response);
        showNews(request, response, dao);
      } else {
        showNews(request, response, dao);
      }
    } finally {
      out.close();
    }
  }
  
  private void uploadPhotos(List<FileItem> fileItemsList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
    UploadUtils upd = new UploadUtils();
    
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
  }
  
  private void initDirectory() {
    DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
    fileFactory.setRepository(filesDir);
    this.uploader = new ServletFileUpload(fileFactory);
  }
  
  private void showNews(HttpServletRequest request, HttpServletResponse response, NewsPersistanceDAO dao) throws ServletException, IOException {
    //Variable definition
    String htmlRespond = "";
    List<New> newList;
    String text;
    
    if(request.getParameter("mine") == null && request.getParameter("detail") == null) newList = dao.getAllNews();
    else if(request.getParameter("detail") != null) {
      newList = new ArrayList<New>();
      newList.add(dao.getNewById(Integer.valueOf(request.getParameter("id"))));
    } else {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      UserPersistanceDAO userDao = new UserPersistanceDAO();
      User user;

      user = userDao.retrieveByUserUsername(name);
      newList = dao.getNewsByUsername(user);
    }
    
    for(New cliNew : newList) {
      int length = cliNew.getText().length() > 200 && request.getParameter("detail") == null ? 200 : cliNew.getText().length();
      
      text = request.getParameter("detail") != null ? cliNew.getText() : cliNew.getText().substring(0, length - 1) + "";
      if(length == 200) text += "...";
      htmlRespond += newTemplate(cliNew.getTitle(), 
                                 cliNew.getWriteDate(), 
                                 cliNew.getAuthor().getName() + " " + cliNew.getAuthor().getSurname(),
                                 text, 
                                 cliNew.getNewId(),
                                 cliNew.getImageList(),
                                 request);
    }
    
    //request.getSession().setAttribute("publicNews", htmlRespond);
    if(request.getParameter("detail") != null) {
      request.getSession().setAttribute("detailNew", htmlRespond);
    } 
    response.getWriter().write(htmlRespond);
    response.getWriter().flush();
  }
  
  private String newTemplate(String title, Date date, String author, String text, int newId, Set<Photo> imageList, HttpServletRequest request) {  
    if(request.getParameter("detail") != null) {
      PhotosServlet servlet = new PhotosServlet();
      String images = "";
      
      if(imageList != null && imageList.size() > 0) {
        List<Photo> photoList = new ArrayList<Photo>();
        for(Photo ph : imageList) photoList.add(ph);
        
        images = "<div id=\"photoGallery\" class=\"container\" style=\"margin-bottom: 30px;\">" + 
                  "<h2>Photos</h2>" +
                  servlet.addImagesToGallery(photoList);
      }
      return  "<div class='panel panel-inverse'>" +
              "<a style='cursor:pointer;' onClick(openNewDetail(" + newId + "))><div id='inverse-heading' class='panel-heading' style='padding:1px 15px;'>" + "<p id='newId' style:'display:none;'>" + newId + "</p>" +
              "<div class='panel-inverse-title' style='font-size:30px;'>" + title + "</div>" +
              "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + author + "</div></br>" +
              "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + new SimpleDateFormat("dd/MM/yyyy").format(date) + "</div>" +
              "</div></a>" +
              "<div class='panel-body'>" +
              text + 
              images +
              "</div>" + 
              "</div>" +
              "</div>";  
    } else {
      return "<div class='panel panel-inverse'>" +
             "<a style='cursor:pointer;' href=\"#\" onClick='(openNewDetail(" + newId + "));return false;'><div id='inverse-heading' class='panel-heading' style='padding:1px 15px;'>" +
             "<div class='panel-inverse-title' style='font-size:30px;'>" + title + "</div>" +
             "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + author + "</div></br>" +
             "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + new SimpleDateFormat("dd/MM/yyyy").format(date) + "</div>" +
             "</div></a>" +
             "<div class='panel-body'>" +
              text +
             "</div>" +
             "</div>";  
    }
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
    } catch (Exception ex) {
      Logger.getLogger(NewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (Exception ex) {
      Logger.getLogger(NewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
