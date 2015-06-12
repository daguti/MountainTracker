/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.New;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface InterfaceNewsPersistance {
  public void storeNew(New cliNew);
  public List<New> getAllNews();
}
