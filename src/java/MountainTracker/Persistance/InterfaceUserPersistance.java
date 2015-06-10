/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.User;

/**
 *
 * @author ESa10969
 */
public interface InterfaceUserPersistance {
  public User retrieveByUserUsername(String username);
  public void storeUser(User user);
}
