package models;

import helpers.Json;
import helpers.JsonObject;

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
        return Json.jsonPair(this.getClass().getSimpleName(), Json.jsonObject(Json.jsonMembers(
            Json.jsonPair("spaces", Json.jsonValue(spaces[0][0].serialize()))
        )));
    }
    
    public OneSpaceTile loadObject(JsonObject json) {
    	this.spaces = new Space[2][2];
    	spaces[0][0] = (new Space()).loadObject(json.getObject("spaces"));
    	return this;
    }
    
    public String toString(){
    	return this.space.toString();
    }
}