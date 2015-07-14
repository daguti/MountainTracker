/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.GpxFiles;

import MountainTracker.Beans.Coordinate;
import MountainTracker.Beans.Route;
import java.io.File;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ESa10969
 */
public class GpxFileWriter {
  public void WriteFile(Route route, File file) throws ParserConfigurationException {
    //Variable definition
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer;
    DOMSource source;
    StreamResult result;
    
    try {
      Document doc = dBuilder.newDocument();
      Element rootElement = doc.createElement("gpx");
      doc.appendChild(rootElement);
      
      rootElement.setAttributeNode(getAtributeNode("xmlns", "http://www.topografix.com/GPX/1/1", doc));
      rootElement.setAttributeNode(getAtributeNode("version", "1.1",doc));
      rootElement.setAttributeNode(getAtributeNode("creator", "MountainTracker", doc));
      rootElement.setAttributeNode(getAtributeNode("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance",doc));
      rootElement.setAttributeNode(getAtributeNode("xsi:schemaLocation", "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.gpsies.com/GPX/1/0 http://www.gpsies.com/gpsies.xsd", doc));
      
      Element metaElm = doc.createElement("metadata");
      rootElement.appendChild(metaElm);
      appendToMetadata(doc, route, metaElm);
      
      Element trkElm = doc.createElement("trk");
      rootElement.appendChild(trkElm);
      Element trkSegElm = doc.createElement("trkseg");
      trkElm.appendChild(trkSegElm);
      appendToTrkSegElm(doc, route, trkSegElm);
      
      transformer = transformerFactory.newTransformer();
      source = new DOMSource(doc);
      result = new StreamResult(file);
      
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.transform(source, result);
      
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
    
  }
  
  private Attr getAtributeNode(String name, String value, Document doc) {
    //Variable definition
    Attr attr;
    
    attr = doc.createAttribute(name);
    attr.setValue(value);
    
    return attr;
  }
  
  private void appendToMetadata(Document doc, Route route, Element metaElm) {
    //Variable definition
    String length  = route.getTrackDistance() != null ? route.getTrackDistance().toString() : "";
    String ascend  = route.getTotalAscend()   != null ? route.getTotalAscend().toString()   : "";
    String descend = route.getTotalDescend()  != null ? route.getTotalDescend().toString()  : "";
    String minHgh  = route.getMinHeight()     != null ? route.getMinHeight().toString()     : "";
    String maxHgh  = route.getMaxHeight()     != null ? route.getMaxHeight().toString()     : "";
    Element nameElm = doc.createElement("name");
    Element descElm = doc.createElement("desc");
    Element extElm  = doc.createElement("extensions");
    Element lengthElm = doc.createElement("gpsies:trackLengthMeter");
    Element ascendElm = doc.createElement("gpsies:totalAscentMeter");
    Element descenElm = doc.createElement("gpsies:totalDescentMeter");
    Element minHghElm = doc.createElement("gpsies:minHeightMeter");
    Element maxHghElm = doc.createElement("gpsies:maxHeightMeter");

    //Append to metadata element
    metaElm.appendChild(nameElm);
    metaElm.appendChild(descElm);
    metaElm.appendChild(extElm);
    
    //Set name and description
    nameElm.appendChild(doc.createTextNode(route.getRouteName()));
    descElm.appendChild(doc.createTextNode(route.getDescription()));
    
    //Append to extensions element
    extElm.appendChild(lengthElm);
    extElm.appendChild(ascendElm);
    extElm.appendChild(descenElm);
    extElm.appendChild(minHghElm);
    extElm.appendChild(maxHghElm);
    
    //Set extensions element childs values
    lengthElm.appendChild(doc.createTextNode(length));
    ascendElm.appendChild(doc.createTextNode(ascend));
    descenElm.appendChild(doc.createTextNode(descend));
    minHghElm.appendChild(doc.createTextNode(minHgh));
    maxHghElm.appendChild(doc.createTextNode(maxHgh));
  }
  
  private void appendToTrkSegElm(Document doc, Route route, Element trkSegElm) {
    //Variable definition
    Set<Coordinate> coordList = route.getCoordinates();
    Element trkptElm;
    Element eleElm;
    String lat;
    String lon;
    String ele;
    
    try {
      for(Coordinate cor : coordList) {
        lat = cor.getLatitude() != null ? cor.getLatitude().toString() : "";
        lon = cor.getLongitude() != null ? cor.getLongitude().toString() : "";
        ele = cor.getElevation() != null ? cor.getElevation().toString() : "";

        trkptElm = doc.createElement("trkpt");
        trkSegElm.appendChild(trkptElm);

        trkptElm.setAttributeNode(getAtributeNode("lat", lat, doc));
        trkptElm.setAttributeNode(getAtributeNode("lon", lon, doc));
        eleElm   = doc.createElement("ele");
        trkptElm.appendChild(eleElm);
        eleElm.appendChild(doc.createTextNode(ele));
      }
    }catch(Exception ex) {
      System.out.println(ex);
    }
  }
}
