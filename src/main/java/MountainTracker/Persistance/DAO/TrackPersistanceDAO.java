/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.DAO;

import MountainTracker.Beans.Route;
import MountainTracker.Persistance.Connection.ConnectionBuilder;
import MountainTracker.Persistance.InterfaceTrackPersistance;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author ESa10969
 */
public class TrackPersistanceDAO implements InterfaceTrackPersistance {
  private final ConnectionBuilder con = new ConnectionBuilder();

  @Override
  public Route getRoute(int routeId) {
    //Variable definition
    String qryStr = "select a from Route a where a.refRoute = :ref";
    Query qry;
    List<Route> routeList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setInteger("ref", routeId);
      routeList = qry.list();

      if(routeList != null && routeList.size() > 0) return routeList.get(0);
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public void storeRoute(Route route) {
    //Variable definition
    Transaction trans = null;
    
    try {
      con.openSession();
      
      trans = con.session.getTransaction();
      trans.begin();
      con.session.save(route);
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