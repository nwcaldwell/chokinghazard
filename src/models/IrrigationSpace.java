package models;

import helpers.JsonObject;

public class IrrigationSpace extends Space {
	private String imageSource = "src/resources/oneTile_irrigation.png";
    
    public IrrigationSpace() {
        this.type = Space.SpaceType.IRRIGATION;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }
    
    public String getImageSource(){
    	return this.imageSource;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public IrrigationSpace loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}
