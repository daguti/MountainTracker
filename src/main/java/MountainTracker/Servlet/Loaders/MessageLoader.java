/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Loaders;

import MountainTracker.Beans.Message;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.MessagePersistenceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "MessageLoader", urlPatterns = {"/MessageLoader"})
public class MessageLoader extends HttpServlet {

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
      boolean fst = true;
      List<Message> messageList;
      MessagePersistenceDAO dao = new MessagePersistenceDAO();
      String data = "{ \"messages\":[";
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      UserPersistanceDAO userDao = new UserPersistanceDAO();
      User user;

      user = userDao.retrieveByUserUsername(name);
      if(request.getParameter("filtro") != null) {
        List<User> userList = userDao.getAllUsers();
        String json = "";
        for(User usr : userList) json += usr.getUsername() + ", ";
        json.substring(0, json.lastIndexOf(","));
        out.write(json);
        out.flush();
      } else if(request.getParameter("unread") != null) {
        String num = dao.getUnreadMessages(user);
        request.getSession().setAttribute("unreadMsg", num);
        out.write(num);
        out.flush();
      } else if(request.getParameter("send") != null) {
        User userTo = userDao.retrieveByUserUsername(request.getParameter("userTo"));
        Message msg = new Message();
        msg.setUserFrom(user);
        msg.setUserTo(userTo);
        msg.setSubject(request.getParameter("subject"));
        msg.setText(request.getParameter("text"));
        msg.setSendDate(new Date());
        msg.setTimeMilis(System.currentTimeMillis());
        msg.setIsRead(false);
        dao.storeMessage(msg);
        request.getRequestDispatcher("/messages.jsp").forward(request, response);
      } else if(request.getParameter("id") != null) {
        int num = Integer.valueOf(request.getSession().getAttribute("unreadMsg").toString());
        num = num - 1;
        if(num < 0) num = 0;
        request.getSession().setAttribute("unreadMsg", num);
        dao.setMessageToRead(Integer.valueOf(request.getParameter("id")));
        out.write("OK");
        out.flush();
      } else if(request.getParameter("delete") != null) {
        dao.deleteMessage(Integer.valueOf(request.getParameter("idDel")));
        out.write("OK");
      }else {
        if(request.getParameter("sended") != null) {
          messageList = dao.getSendedMessages(user);
        } else {
          messageList = dao.getReceivedMessages(user);
        }
        for(Message msg : messageList) {
          if(!fst) {
              data +=",";
          }
          Date date = new Date(msg.getTimeMilis());
          data +="[\"" + msg.getUserFrom().getUsername() + "\", \"" + msg.getUserTo().getUsername() 
                  + "\", \"" + new SimpleDateFormat("dd-MM-yyyy").format(msg.getSendDate()) + " " 
                  + new SimpleDateFormat("HH:mm:ss").format(date)
                  + "\", \"" + msg.getSubject() + "\", \""  + msg.getText();
          data += request.getParameter("sended") == null ? "\", \""  + msg.isIsRead() : 
                  msg.isIsRead() ? "\", \"" + new SimpleDateFormat("dd-MM-yyyy").format(msg.getReadDate()) + " " + new SimpleDateFormat("HH:mm:ss").format(new Date(msg.getReadMilis()))
                  : "\", \"" + "NO";
          data += "\", \"" + msg.getMessageRef() + "\"]";
          fst = false;
        }
        data += "]}";
        System.out.println("Respuesta = " + data);
        out.write(data);
      }
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
