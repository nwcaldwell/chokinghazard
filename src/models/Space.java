package models;

import helpers.Json;
import helpers.JsonObject;

public class Space implements Serializable<Space> {
    public enum SpaceType {
        RICE, VILLAGE, IRRIGATION, PALACE
    }

    protected SpaceType type;
    private String imageSource;

    public Space() {

    }

    public SpaceType getType() {
        return type;
    }

    public void setType(SpaceType type) {
        this.type = type;
    }
    
    public String getImageSource(){
    	return imageSource;
    }

    public String serialize() {
		return Json.jsonPair("Space", Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("type", Json.jsonValue(type.toString()))
		)));
    }

    public Space loadObject(JsonObject json) {
    	Space space = new Space();
    	space.setType(SpaceType.valueOf((json.getString("SpaceType"))));
    	return space;
    }
}
