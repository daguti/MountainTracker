/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

import java.util.List;

/**
 *
 * @author ESa10969
 */
public class Route {
  private int refRoute;
  private String routeName;
  private String description;
  private Double trackDistance;
  private Double totalAscend;
  private Double totalDescend;
  private Double minHeight;
  private Double maxHeight;
  private List<Coordinate> coordinates;
  private List<New> newList;
  private User user;
  
  public Route() {
  }

  public Route(String routeName, String description, Double trackDistance, Double totalAscend, Double totalDescend, Double minHeight, Double maxHeight, List<Coordinate> coordinates, List<New> newList) {
    this.routeName = routeName;
    this.trackDistance = trackDistance;
    this.totalAscend = totalAscend;
    this.totalDescend = totalDescend;
    this.minHeight = minHeight;
    this.maxHeight = maxHeight;
    this.coordinates = coordinates;
    this.newList = newList;
  }

  public int getRefRoute() {
    return refRoute;
  }

  public void setRefRoute(int refRoute) {
    this.refRoute = refRoute;
  }

  public String getRouteName() {
    return routeName;
  }

  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getTrackDistance() {
    return trackDistance;
  }

  public void setTrackDistance(Double trackDistance) {
    this.trackDistance = trackDistance;
  }

  public Double getTotalAscend() {
    return totalAscend;
  }

  public void setTotalAscend(Double totalAscend) {
    this.totalAscend = totalAscend;
  }

  public Double getTotalDescend() {
    return totalDescend;
  }

  public void setTotalDescend(Double totalDescend) {
    this.totalDescend = totalDescend;
  }

  public Double getMinHeight() {
    return minHeight;
  }

  public void setMinHeight(Double minHeight) {
    this.minHeight = minHeight;
  }

  public Double getMaxHeight() {
    return maxHeight;
  }

  public void setMaxHeight(Double maxHeight) {
    this.maxHeight = maxHeight;
  }

  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<New> getNewList() {
    return newList;
  }

  public void setNewList(List<New> newList) {
    this.newList = newList;
  }
}