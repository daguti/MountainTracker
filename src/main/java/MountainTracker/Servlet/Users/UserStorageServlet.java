/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Users;

import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "UserStorageServlet", urlPatterns = {"/userStorage"})
public class UserStorageServlet extends HttpServlet {

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
    try {
      UserPersistanceDAO dao = new UserPersistanceDAO();
      User user = new User();
      user.setUsername(request.getParameter("username"));
      user.setPass(request.getParameter("password"));
      user.setName(request.getParameter("name"));
      user.setSurname(request.getParameter("surname"));
      user.setCity(request.getParameter("city"));
      user.setCountry(request.getParameter("country"));
      user.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("birthday")));
      user.setEmail(request.getParameter("email"));
      user.setUserType(1);
      dao.storeUser(user);
      request.getRequestDispatcher("login.jsp").forward(request, response);
    } catch(ParseException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      out.close();
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