/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

import java.util.Date;

/**
 *
 * @author ESa10969
 */
public class Message {
  int messageRef;
  User userFrom;
  User userTo;
  String subject;
  String text;
  Date sendDate;
  long timeMilis;
  boolean isRead;
  User owner;
  
  public Message() {
  }

  public Message(User userFrom, User userTo, String subject, String text, Date sendDate, User owner) {
    this.userFrom = userFrom;
    this.userTo   = userTo;
    this.subject  = subject;
    this.text     = text;
    this.sendDate = sendDate;
    this.owner    = owner;
  }

  public int getMessageRef() {
    return messageRef;
  }

  public void setMessageRef(int messageRef) {
    this.messageRef = messageRef;
  }

  public User getUserFrom() {
    return userFrom;
  }

  public void setUserFrom(User userFrom) {
    this.userFrom = userFrom;
  }

  public User getUserTo() {
    return userTo;
  }

  public void setUserTo(User userTo) {
    this.userTo = userTo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public long getTimeMilis() {
    return timeMilis;
  }

  public void setTimeMilis(long timeMilis) {
    this.timeMilis = timeMilis;
  }

  public boolean isIsRead() {
    return isRead;
  }

  public void setIsRead(boolean isRead) {
    this.isRead = isRead;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
  
  public static Message CopyUserToAsOwner(Message message) {
    //Variable definition
    Message msg = new Message();
    
    msg.setIsRead(false);
    msg.setOwner(message.getUserTo());
    msg.setSendDate(message.getSendDate());
    msg.setTimeMilis(message.getTimeMilis());
    msg.setSubject(message.getSubject());
    msg.setText(message.getText());
    msg.setUserFrom(message.getUserFrom());
    msg.setUserTo(message.getUserTo());
    
    return msg;
  }
}
