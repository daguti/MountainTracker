/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.Message;
import MountainTracker.Beans.User;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface InterfaceMessagePersistance {
  public List<Message> getSendedMessages(User userFrom);
  public List<Message> getReceivedMessages(User userTo);
  public void storeMessage(Message message);
}
