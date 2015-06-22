/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.DAO;

import MountainTracker.Beans.Message;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.Connection.ConnectionBuilder;
import MountainTracker.Persistance.InterfaceMessagePersistance;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author ESa10969
 */
public class MessagePersistenceDAO implements InterfaceMessagePersistance {
  private final ConnectionBuilder con = new ConnectionBuilder();
  
  @Override
  public List<Message> getSendedMessages(User userFrom) {
    //Variable definition
    String qryStr = "select a from Message a where a.userFrom = :userFrom";
    Query qry; 
    List<Message> sendedList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setEntity("userFrom", userFrom);
      sendedList = qry.list();

      for(Message msg : sendedList) {
        Hibernate.initialize(msg.getUserFrom());
        Hibernate.initialize(msg.getUserTo());
      }
      return sendedList; 
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public List<Message> getReceivedMessages(User userTo) {
    //Variable definition
    String qryStr = "select a from Message a where a.userTo = :userTo";
    Query qry;
    List<Message> receivedList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setEntity("userTo", userTo);
      receivedList = qry.list();

      for(Message msg : receivedList) {
        Hibernate.initialize(msg.getUserFrom());
        Hibernate.initialize(msg.getUserTo());
      }
      return receivedList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public void storeMessage(Message message) {
    //Variable definition
    Transaction trans = null;
    
    try {
      con.openSession();
      
      trans = con.session.getTransaction();
      trans.begin();
      con.session.save(message);
      trans.commit();
    } catch(HibernateException ex) {
      if(trans != null) trans.rollback();
      con.closeSession();
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
  }
  
}
