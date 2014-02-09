package models;

import helpers.JsonObject;

public class RiceSpace extends Space {
	private String imageSource = "src/resources/oneTile_rice";

    public RiceSpace() {
        this.type = Space.SpaceType.RICE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    } 
    
    public String getImageSource(){
    	return imageSource;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public RiceSpace loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}
