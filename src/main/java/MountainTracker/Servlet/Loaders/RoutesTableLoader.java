/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Loaders;

import MountainTracker.Beans.Route;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.TrackPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RoutesTableLoader", urlPatterns = {"/RoutesTableLoader"})
public class RoutesTableLoader extends HttpServlet {

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
    TrackPersistanceDAO dao = new TrackPersistanceDAO();
    List<Route> routeList;
    String data = "{ \"routes\":[";
    boolean fst = true;
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      if(request.getParameter("mine") == null) routeList = dao.getAllRoutes();
      else {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        UserPersistanceDAO userDao = new UserPersistanceDAO();
        User user;

        user = userDao.retrieveByUserUsername(name);
        routeList = dao.getRoutesByUsername(user);
      }
      
      for(Route route: routeList) {
        if(!fst) {
            data +=",";
        }
        data +="[\"" + route.getRefRoute() + "\", \"" + route.getRouteName() + "\", \"" + route.getDescription()
                + "\", \"" + Math.round(route.getTrackDistance() * 100) / 100 + "\", \"" + route.getTotalAscend() + "\", \"" + route.getTotalDescend() 
              + "\", \"" + route.getMinHeight() + "\", \"" + route.getMaxHeight() + "\"]";
        fst = false;
      }
      data += "]}";
      System.out.println("Respuesta = " + data);
      out.write(data);
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
