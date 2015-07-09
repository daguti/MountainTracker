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
public class Photo {
  private int imageId;
  private byte[] image;
  private String desription;
  private User user;
  private New userNew;
  private Date uploadDate;
  private Set<Album>albumList;
  private Set<User> userProfileList;
  
  public Photo() {
  }

  public Photo(byte[] image) {
      this.image = image;
  }

  public int getImageId() {
      return this.imageId;
  }

  public void setImageId(int imageId) {
      this.imageId = imageId;
  }

  public byte[] getImage() {
      return this.image;
  }

  public void setImage(byte[] image) {
      this.image = image;
  }

  public String getDesription() {
    return desription;
  }

  public void setDesription(String desription) {
    this.desription = desription;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public New getUserNew() {
    return userNew;
  }

  public void setUserNew(New userNew) {
    this.userNew = userNew;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public Set<Album> getAlbumList() {
    return albumList;
  }

  public void setAlbumList(Set<Album> albumList) {
    this.albumList = albumList;
  }

  public Set<User> getUserProfileList() {
    return userProfileList;
  }

  public void setUserProfileList(Set<User> userProfileList) {
    this.userProfileList = userProfileList;
  }

}
