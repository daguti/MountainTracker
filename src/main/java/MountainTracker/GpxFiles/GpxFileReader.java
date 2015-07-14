/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.GpxFiles;

import MountainTracker.Beans.Coordinate;
import MountainTracker.Beans.Route;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.DAO.TrackPersistanceDAO;
import MountainTracker.Persistance.DAO.UserPersistanceDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ESa10969
 */
public class GpxFileReader {
  
  public void processFile(File file) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
    //Variable definition
    Route track = new Route();
    Set<Coordinate> coordinates = new LinkedHashSet<Coordinate>();
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);
    TrackPersistanceDAO dao = new TrackPersistanceDAO();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName(); //get logged in username
    UserPersistanceDAO userDao = new UserPersistanceDAO();
    User user;
    
    user = userDao.retrieveByUserUsername(name);
    track.setUser(user);
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
  
    NodeList list = doc.getElementsByTagName("name");
    if(list != null && list.getLength() > 0)track.setRouteName(list.item(0).getTextContent());
    else track.setRouteName("");
    list = doc.getElementsByTagName("desc");
    if(list != null && list.getLength() > 0)track.setDescription(list.item(0).getTextContent());
    else track.setDescription("");
    list = doc.getElementsByTagName("gpsies:trackLengthMeter");
    if(list != null && list.getLength() > 0)track.setTrackDistance(Double.valueOf(list.item(0).getTextContent()));
    else track.setTrackDistance(0.0);
    list = doc.getElementsByTagName("gpsies:totalAscentMeter");
    if(list != null && list.getLength() > 0)track.setTotalAscend(Double.valueOf(list.item(0).getTextContent()));
    else track.setTotalAscend(0.0);
    list = doc.getElementsByTagName("gpsies:totalDescentMeter");
    if(list != null && list.getLength() > 0)track.setTotalDescend(Double.valueOf(list.item(0).getTextContent()));
    else track.setTotalDescend(0.0);
    list = doc.getElementsByTagName("gpsies:minHeightMeter");
    if(list != null && list.getLength() > 0)track.setMinHeight(Double.valueOf(list.item(0).getTextContent()));
    else track.setMinHeight(0.0);
    list = doc.getElementsByTagName("gpsies:maxHeightMeter");
    if(list != null && list.getLength() > 0)track.setMaxHeight(Double.valueOf(list.item(0).getTextContent()));
    else track.setMaxHeight(0.0);
    
    list = doc.getElementsByTagName("trkpt");
	for (int temp = 0; temp < list.getLength(); temp++) {
      Coordinate cord = new Coordinate();
      Node nNode = list.item(temp);

      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) nNode;
        cord.setLatitude(Double.valueOf(eElement.getAttribute("lat")));
        cord.setLongitude(Double.valueOf(eElement.getAttribute("lon")));
        
        if(eElement.getElementsByTagName("ele").item(0) != null) {
          cord.setElevation(Double.valueOf(eElement.getElementsByTagName("ele").item(0).getTextContent()));
        }
        cord.setRoute(track);
        coordinates.add(cord);
      }
	}
    track.setCoordinates(coordinates);
    dao.storeRoute(track);
  }
}