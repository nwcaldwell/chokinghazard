package models;

import java.util.LinkedList;

public abstract class Tile implements Serializable<Tile> {
  private String imageSource;
  protected LinkedList<Space> spaces;
  
  public Tile(LinkedList<Space> spaces)
  {
	  this.spaces = spaces;
  }

  public abstract void rotate();

  public String getImageSource() {
    return imageSource;
  }

  public void setImageSource(String imageSource) {
    this.imageSource = imageSource;
  }

  public LinkedList<Space> getSpaces() {
    return spaces;
  }

  public void setSpaces(LinkedList<Space> spaces) {
    this.spaces = spaces;
  }

  public String serialize() {
    // TODO Auto-generated method stub
    return null;
  }
}