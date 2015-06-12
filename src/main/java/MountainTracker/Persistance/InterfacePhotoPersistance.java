/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MountainTracker.Persistance;

import MountainTracker.Beans.Photo;
import MountainTracker.Beans.User;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface InterfacePhotoPersistance {
  public Photo getPhotoById(int refImage);
  public List<Photo> getAllPhotos();
  public List<Photo> getPhotosByUsername(User user);
  public void storePhotos(List<Photo> imgList);
}
