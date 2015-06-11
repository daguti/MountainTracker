/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author ESa10969
 */
public class User {
  private int userType;
  private String username;
  private String pass;
  private Date birthday;
  private String name;
  private String surname;
  private String city;
  private String country;
  private String email;
  private Set<Route> routeList;
  private Set<New> newList;
  
  public User() {
  }

  public User(int userType, String username, String pass, Date birthday, String name, String surname, String city, String country, String email, Set<Route> routeList, Set<New> newList) {
    this.userType = userType;
    this.username = username;
    this.pass = pass;
    this.birthday = birthday;
    this.name = name;
    this.surname = surname;
    this.city = city;
    this.country = country;
    this.email = email;
    this.routeList = routeList;
    this.newList = newList;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Set<Route> getRouteList() {
    return routeList;
  }

  public void setRouteList(Set<Route> routeList) {
    this.routeList = routeList;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<New> getNewList() {
    return newList;
  }

  public void setNewList(Set<New> newList) {
    this.newList = newList;
  }
}

