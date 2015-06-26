/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.DAO;

import MountainTracker.Beans.New;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import MountainTracker.Persistance.Connection.ConnectionBuilder;
import MountainTracker.Persistance.InterfacePhotoPersistance;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ESa10969
 */
public class PhotoPersistanceDAO implements InterfacePhotoPersistance {
  private final ConnectionBuilder con = new ConnectionBuilder();

  public static SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  @Override
  public Photo getPhotoById(int refImage) {
    //Variable definition
    String qryStr = "select a from Photo a where a.imageId = :refPhoto";
    Query qry;
    List<Photo> imaegeList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setInteger("refPhoto", refImage);
      imaegeList = qry.list();

      if(imaegeList != null && imaegeList.size() > 0) return imaegeList.get(0);
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public void storePhotos(List<Photo> imgList) {
    //Variable definition
    Transaction trans = null;
    
    try {
      con.openSession();
      
      trans = con.session.getTransaction();
      trans.begin();
      for(Photo image : imgList) {
        image.setUploadDate(new Date());
        con.session.save(image);
      }
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
  public List<Photo> getAllPhotos() {
    //Variable definition
    String qryStr = "select a from Photo a";
    Query qry;
    List<Photo> imaegeList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      imaegeList = qry.list();

      for(Photo img : imaegeList)Hibernate.initialize(img.getUser());
      return imaegeList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public List<Photo> getPhotosByUsername(User user) {
    //Variable definition
    String qryStr = "select a from Photo a where a.user = :user";
    Query qry;
    List<Photo> imaegeList = null;
    
    try {
      con.openSession();

      qry = con.session.createQuery(qryStr);
      qry.setEntity("user", user);
      imaegeList = qry.list();

      for(Photo img : imaegeList)Hibernate.initialize(img.getUser());
      return imaegeList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }

  @Override
  public List<Photo> getPhotosByNew(int newId) {
    //Variable definition
    String qryStr = "select a from Photo a where a.userNew = :newId";
    Query qry;
    List<Photo> imaegeList = null;
    NewsPersistanceDAO dao = new NewsPersistanceDAO();
    
    try {
      New cliNew = dao.getNewById(newId);
      con.openSession();
      
      qry = con.session.createQuery(qryStr);
      qry.setEntity("newId", cliNew);
      imaegeList = qry.list();

      for(Photo img : imaegeList)Hibernate.initialize(img.getUser());
      return imaegeList;
    } catch(HibernateException ex) {
      Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
    } finally {
      con.closeSession();
    }
    return null;
  }
}