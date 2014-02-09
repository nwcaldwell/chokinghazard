package models;

import java.util.LinkedList;

class TwoSpaceTile extends Tile {
  private String imageSource = "src/resources/twoTile.png";
	
  public TwoSpaceTile(Space space1,Space space2, LinkedList<Space> spaces) {
	  super(spaces);
	  spaces.add(space1);
	  spaces.add(space2);
  }
 
  public void rotate() {
	  spaces.addLast(spaces.removeFirst());
  }
  
  public String getImageSource(){
	  return this.imageSource;
  }

  public String serialize() {
    // TODO Auto-generated method stub
    return null;
  }

  public TwoSpaceTile loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}

