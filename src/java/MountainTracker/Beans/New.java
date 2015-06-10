/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

import java.sql.Date;

/**
 *
 * @author ESa10969
 */
public class New {
  private int newId;
  private String title;
  private String text;
  private Date writeDate;
  private User author;
  private Route track;

  public New() {
  }

  public New(String title, String text, Date writeDate, User author, Route track) {
    this.title = title;
    this.text = text;
    this.writeDate = writeDate;
    this.author = author;
    this.track = track;
  }

  public int getNewId() {
    return newId;
  }

  public void setNewId(int newId) {
    this.newId = newId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getWriteDate() {
    return writeDate;
  }

  public void setWriteDate(Date writeDate) {
    this.writeDate = writeDate;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Route getTrack() {
    return track;
  }

  public void setTrack(Route track) {
    this.track = track;
  }
  
  
  
}
