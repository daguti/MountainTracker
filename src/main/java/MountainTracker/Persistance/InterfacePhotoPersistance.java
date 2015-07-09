/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.Album;
import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface InterfacePhotoPersistance {
  public Photo getPhotoById(int refImage);
  public List<Photo> getAllPhotos();
  public List<Photo> getPhotosByDate(Date fecha);
  public List<Photo> getPhotosByUsername(User user);
  public List<Photo> getPhotosByNew(int newId);
  public void storePhotos(List<Photo> imgList);
  public void storePhoto(Photo photo);
  public void storeAlbum(Album album);
  public Album getAlbumById(int id);
  public List<Album> getUserAlbums(User user);
  public List<Album> getAllAlbums();
  public List<Photo> getAlbumPhotos(Album album);
}
