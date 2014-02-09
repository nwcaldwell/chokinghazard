package models;

import helpers.JsonObject;

import java.util.LinkedList;

class OneSpaceTile extends Tile {
    
    public OneSpaceTile(Space space,LinkedList<Space> spaces) {
	    super(spaces);
	    spaces.add(space);
    }

    public void rotate() {
	    spaces.addLast(spaces.removeFirst());
    }

    public String serialize() {
	    // TODO Auto-generated method stub
        return null;
    }

    public OneSpaceTile loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}