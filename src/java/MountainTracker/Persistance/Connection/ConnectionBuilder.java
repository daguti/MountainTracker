/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance.Connection;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ESa10969
 */
public class ConnectionBuilder {
  public SessionFactory sf;
  public Session session;
  /**
    * Retrieve Connection to MySQL DB
    */
   public void openSession () {
     //Variable definition
     /*URI dbUri;
     String username;
     String password;
     int port;*/
     Configuration configuration = new Configuration();
     SessionFactory sessionFactory;
     try {
      /*dbUri = new URI(System.getenv("DATABASE_URL"));

      username = dbUri.getUserInfo().split(":")[0];
      password = dbUri.getUserInfo().split(":")[1];
      port = dbUri.getPort();*/
      
      configuration.configure("hibernate.cfg.xml");
      
      /*configuration.setProperty("hibernate.connection.url","jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath());
      configuration.setProperty("hibernate.connection.username", username);
      configuration.setProperty("hibernate.connection.password", password);*/
      
      StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(ssrb.build());
      session = sessionFactory.openSession();
     } catch(/*URISyntaxException*/ Exception ex) {
       Logger.getLogger(this.getClass()).log(Level.ERROR, ex);
     }
   }
   
   public void closeSession() {
     if(session != null)session.close();
     if(sf != null)sf.close();
   }
}
