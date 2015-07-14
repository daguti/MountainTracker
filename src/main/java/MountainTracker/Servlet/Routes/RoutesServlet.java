/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Servlet.Routes;

import MountainTracker.Beans.Coordinate;
import MountainTracker.Beans.Route;
import MountainTracker.GpxFiles.GpxFileWriter;
import MountainTracker.Persistance.DAO.TrackPersistanceDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "RoutesServlet", urlPatterns = {"/routes"})
public class RoutesServlet extends HttpServlet {
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
      throws ServletException, IOException {
    //Variable definition
    TrackPersistanceDAO dao = new TrackPersistanceDAO();
    PrintWriter out = null;
    
    response.setContentType("text/html;charset=UTF-8");
    
    try {
      boolean fst = true;
      
      if(request.getParameter("detail") != null) {
        Route track = dao.getRoute(Integer.valueOf(request.getParameter("routeId")));
        String coordinates = "";
        out = response.getWriter();
        for(Coordinate coord : track.getCoordinates()) {
          if(!fst) {
            coordinates += "|";
          }
          coordinates += coord.getLatitude() + "," + coord.getLongitude();
          fst = false;
        }
        out.write(coordinates);
      } else if(request.getParameter("download") != null) {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        GpxFileWriter writer = new GpxFileWriter();
        File genFile;
        
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
        int routeId = Integer.valueOf(request.getParameter("routeId"));
        Route route = dao.getRoute(routeId);
        
        genFile = new File(filesDir.getPath() + "/" + route.getRouteName() + ".gpx");
        writer.WriteFile(route, genFile);
        
        if(!genFile.exists()){
			throw new ServletException("File doesn't exists on server.");
		}
        ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(genFile);
		String mimeType = ctx.getMimeType(genFile.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) genFile.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + route.getRouteName() + ".gpx" + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
      }
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(RoutesServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch(Exception ex) {
      System.out.println(ex);
    } finally {
      if(out != null)out.close();
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
