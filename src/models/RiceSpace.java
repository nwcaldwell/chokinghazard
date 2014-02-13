package models;

import helpers.Json;
import helpers.JsonObject;

public class RiceSpace extends Space {
	private String imageSource = "src/resources/oneTile_rice.png";

    public RiceSpace() {
        this.type = Space.SpaceType.RICE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    } 
    
    public String getImageSource(){
    	return imageSource;
    }
    
    public String toString(){
    	return this.type.toString();
    }

    public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("type", Space.SpaceType.RICE.toString())
		));
    }

    public RiceSpace loadObject(JsonObject json) {
    	return this;
    }
}
