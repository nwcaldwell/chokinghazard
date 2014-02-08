

import java.util.LinkedList;

abstract class Tile implements Serializable<Tile> {
  private String imageSource;
  private LinkedList<Space> spaces;

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

  public Tile loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}
