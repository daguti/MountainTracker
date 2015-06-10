/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.GpxFiles;

import MountainTracker.Beans.Coordinate;
import MountainTracker.Beans.Route;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    List<Coordinate> coordinates = new ArrayList<Coordinate>();
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
  
    NodeList list = doc.getElementsByTagName("name");
    track.setRouteName(list.item(0).getTextContent());
    list = doc.getElementsByTagName("desc");
    track.setDescription(list.item(0).getTextContent());
    list = doc.getElementsByTagName("gpsies:trackLengthMeter");
    track.setTrackDistance(Double.valueOf(list.item(0).getTextContent()));
    list = doc.getElementsByTagName("gpsies:totalAscentMeter");
    track.setTotalAscend(Double.valueOf(list.item(0).getTextContent()));
    list = doc.getElementsByTagName("gpsies:totalDescentMeter");
    track.setTotalDescend(Double.valueOf(list.item(0).getTextContent()));
    list = doc.getElementsByTagName("gpsies:minHeightMeter");
    track.setMinHeight(Double.valueOf(list.item(0).getTextContent()));
    list = doc.getElementsByTagName("gpsies:maxHeightMeter");
    track.setMaxHeight(Double.valueOf(list.item(0).getTextContent()));
 
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
        coordinates.add(cord);
      }
	}
    track.setCoordinates(coordinates);
  }
}
