/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

/**
 *
 * @author ESa10969
 */
public class Coordinate {
  private int refCoordinate;
  private Double latitude;
  private Double longitude;
  private Double elevation;
  private Route route;
  
  public Coordinate() {
  }

  public Coordinate(Double latitude, Double longitude, Double elevation) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.elevation = elevation;
  }

  public int getRefCoordinate() {
    return refCoordinate;
  }

  public void setRefCoordinate(int refCoordinate) {
    this.refCoordinate = refCoordinate;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getElevation() {
    return elevation;
  }

  public void setElevation(Double elevation) {
    this.elevation = elevation;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

}
