package models;

import helpers.JsonObject;

import java.util.LinkedList;

class ThreeSpaceTile extends Tile {
	String imageSource = "src/resources/threeTile.png";
	
	public ThreeSpaceTile(Space space1,Space space2,Space space3, LinkedList<Space> spaces) {
	    super(spaces);
	    spaces.add(space1);
	    spaces.add(space2);
	    spaces.add(space3);
	}
    
    public void rotate() {
	    spaces.addLast(spaces.removeFirst());
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public ThreeSpaceTile loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}

