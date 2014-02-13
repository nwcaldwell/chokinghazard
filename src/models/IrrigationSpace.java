package models;

import helpers.Json;
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
    
    public String toString(){
    	return this.type.toString();
    }

    public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("type", Space.SpaceType.IRRIGATION.toString())
		));
    }

    public IrrigationSpace loadObject(JsonObject json) {
        return this;
    }
}
