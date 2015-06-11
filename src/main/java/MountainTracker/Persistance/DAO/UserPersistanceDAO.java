/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.DAO;

import MountainTracker.Beans.New;
import MountainTracker.Beans.Route;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.Connection.ConnectionBuilder;
import MountainTracker.Persistance.InterfaceUserPersistance;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ESa10969
 */
public class UserPersistanceDAO implements InterfaceUserPersistance {
  private final ConnectionBuilder con = new ConnectionBuilder();

  public static SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  @Override
  public User retrieveByUserUsername(String username) {
    //Variable definition
    String qryStr = "select a from User a where a.username = :username";
    Query qry;
    List<User> userList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setString("username", username);
      userList = qry.list();

      if(userList != null && userList.size() > 0) return userList.get(0);
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public void storeUser(User user) {
    //Variable definition
    Transaction trans = null;
    
    try {
      user.setNewList(new HashSet<New>());
      user.setRouteList(new HashSet<Route>());
      con.openSession();
      
      trans = con.session.getTransaction();
      trans.begin();
      con.session.save(user);
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
