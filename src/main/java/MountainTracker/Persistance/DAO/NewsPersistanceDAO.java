/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.DAO;

import MountainTracker.Beans.New;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.Connection.ConnectionBuilder;
import MountainTracker.Persistance.InterfaceNewsPersistance;
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
public class NewsPersistanceDAO implements InterfaceNewsPersistance {
private final ConnectionBuilder con = new ConnectionBuilder();

  @Override
  public void storeNew(New cliNew) {
    //Variable definition
    Transaction trans = null;
    
    try {
      con.openSession();
      
      trans = con.session.getTransaction();
      trans.begin();
      con.session.save(cliNew);
      trans.commit();
      
    } catch(HibernateException ex) {
      if(trans != null) trans.rollback();
      con.closeSession();
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
  }

  @Override
  public List<New> getAllNews() {
    //Variable definition
    String qryStr = "select a from New a";
    Query qry;
    List<New> newList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      newList = qry.list();
      for(New nw : newList) Hibernate.initialize(nw.getAuthor()); 
      return newList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public List<New> getNewsByUsername(User user) {
    //Variable definition
    String qryStr = "select a from New a where a.author = :user";
    Query qry;
    List<New> newList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setEntity("user", user);
      newList = qry.list();

      for(New cliNew : newList)Hibernate.initialize(cliNew.getAuthor());
      return newList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }
  
}
