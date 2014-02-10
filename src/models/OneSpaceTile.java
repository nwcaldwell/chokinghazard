package models;

import helpers.JsonObject;

import java.util.LinkedList;

class OneSpaceTile extends Tile {
	private String imageSource;
	private Space space;
    
    public OneSpaceTile(Space space, Space[][] spaces) {
	    super(spaces);
	    spaces[0][0] = space;
	    this.space = space;
	    imageSource = space.getImageSource();
    }

    public void rotate() {
	    //spaces.addLast(spaces.removeFirst());
    }
    
    public String getImageSource(){
    	return imageSource;
    }

    public String serialize() {
	    // TODO Auto-generated method stub
        return null;
    }

    public OneSpaceTile loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public String toString(){
    	return this.space.toString();
    }
    
}