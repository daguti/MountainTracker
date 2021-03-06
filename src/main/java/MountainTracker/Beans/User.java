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
  private Set<Photo> imageList;
  private Set<Message> receivedMessages;
  private Set<Message> sendedMessages;
  private Set<Message> ownerMessages;
  private Set<Album> albumList;
  private Photo profilePhoto;
  
  public User() {
  }

  public User(int userType, String username, String pass, Date birthday, String name, String surname, 
              String city, String country, String email, Set<Route> routeList, Set<New> newList, Set<New> photoList) {
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
    this.imageList = imageList;
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

  public Set<Photo> getImageList() {
    return imageList;
  }

  public void setImageList(Set<Photo> imageList) {
    this.imageList = imageList;
  }

  public Set<Message> getReceivedMessages() {
    return receivedMessages;
  }

  public void setReceivedMessages(Set<Message> receivedMessages) {
    this.receivedMessages = receivedMessages;
  }

  public Set<Message> getSendedMessages() {
    return sendedMessages;
  }

  public void setSendedMessages(Set<Message> sendedMessages) {
    this.sendedMessages = sendedMessages;
  }

  public Set<Message> getOwnerMessages() {
    return ownerMessages;
  }

  public void setOwnerMessages(Set<Message> ownerMessages) {
    this.ownerMessages = ownerMessages;
  }

  public Set<Album> getAlbumList() {
    return albumList;
  }

  public void setAlbumList(Set<Album> albumList) {
    this.albumList = albumList;
  }

  public Photo getProfilePhoto() {
    return profilePhoto;
  }

  public void setProfilePhoto(Photo profilePhoto) {
    this.profilePhoto = profilePhoto;
  }
}

