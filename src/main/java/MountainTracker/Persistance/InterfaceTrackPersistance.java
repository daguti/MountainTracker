/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.Route;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface InterfaceTrackPersistance {
  public Route getRoute(int routeId);
  public List<Route> getAllRoutes();
  public void storeRoute(Route route);
}

