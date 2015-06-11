/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.Util;

import org.hibernate.SessionFactory;

/**
 *
 * @author ESa10969
 */
public class HibernateUtil {

  public SessionFactory sessionFactory  = null;

  public SessionFactory getSessionFactory() {
      return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
  }
}