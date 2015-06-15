/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.News;

import MountainTracker.Beans.New;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.NewsPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "NewsServlet", urlPatterns = {"/news"})
public class NewsServlet extends HttpServlet {

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
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    NewsPersistanceDAO dao = new NewsPersistanceDAO();
    
    try {
      
      if(request.getParameter("crea") != null) {
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        User user;
        New cliNew = new New();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        user = userDao.retrieveByUserUsername(name);
        
        cliNew.setAuthor(user);
        cliNew.setTitle(request.getParameter("title"));
        cliNew.setText(request.getParameter("text"));
        cliNew.setWriteDate(new Date());
        dao.storeNew(cliNew);
        showNews(request, response, dao);
      } else {
        showNews(request, response, dao);
        out.write("OK");
      }
    } finally {
      out.close();
    }
  }
  
  private void showNews(HttpServletRequest request, HttpServletResponse response, NewsPersistanceDAO dao) throws ServletException, IOException {
    //Variable definition
    String htmlRespond = "";
    List<New> newList;
    
    if(request.getParameter("mine") == null) newList = dao.getAllNews();
    else {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      UserPersistanceDAO userDao = new UserPersistanceDAO();
      User user;

      user = userDao.retrieveByUserUsername(name);
      newList = dao.getNewsByUsername(user);
    }
    for(New cliNew : newList) {
      htmlRespond += newTemplate(cliNew.getTitle(), 
                                 cliNew.getWriteDate(), 
                                 cliNew.getAuthor().getName() + " " + cliNew.getAuthor().getSurname(),
                                 cliNew.getText(), 
                                 cliNew.getNewId());
    }
    
    request.getSession().setAttribute("publicNews", htmlRespond);
    //request.getRequestDispatcher("news.jsp").forward(request, response);
  }
  
  private String newTemplate(String title, Date date, String author, String text, int newId) {    
    return "<div class='panel panel-inverse'>" +
           "<a onClick(openNewDetail(" + newId + "))><div id='inverse-heading' class='panel-heading' style='padding:1px 15px;'>" +
           "<div class='panel-inverse-title' style='font-size:30px;'>" + title + "</div>" +
           "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + author + "</div></br>" +
           "<div style='float:right; font-size: 85%; position: relative; top:-30px; color:white;'>" + new SimpleDateFormat("dd/MM/yyyy").format(date) + "</div>" +
           "</div></a>" +
           "<div class='panel-body'>" +
            text +
           "</div>" +
           "</div>";  
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
