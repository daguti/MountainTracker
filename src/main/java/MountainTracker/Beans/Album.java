/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Beans;

import java.util.Set;

/**
 *
 * @author ESa10969
 */
public class Album {
  private int refAlbum;
  private String albumName;
  private Set<Photo>photoList;
  private User owner;
  
  public Album() {
  }

  public Album(int refAlbum, String albumName, Set<Photo> photoList) {
    this.refAlbum = refAlbum;
    this.albumName = albumName;
    this.photoList = photoList;
  }

  public int getRefAlbum() {
    return refAlbum;
  }

  public void setRefAlbum(int refAlbum) {
    this.refAlbum = refAlbum;
  }

  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public Set<Photo> getPhotoList() {
    return photoList;
  }

  public void setPhotoList(Set<Photo> photoList) {
    this.photoList = photoList;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
  
  
}
