package models;

import helpers.Json;
import helpers.JsonObject;

public class PalaceSpace extends Space{
    private int value; 
    private String imageSource;

    // {2, 4, 6, 8, 10}
    public PalaceSpace(int value) {
        this.value = value;
        this.type = Space.SpaceType.PALACE;
        setImageSource();
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }    

    public int getValue() {
        return value;
    }
    
    private void setImageSource(){
    	imageSource = "src/resources/oneTile_"+value+".png";
    }
    
    public String getImageSource(){
    	return imageSource;
    }
    
    public String toString(){
    	return this.type.toString();
    }

    public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("type", Space.SpaceType.PALACE.toString()),
			Json.jsonPair("value", Json.jsonValue(value + ""))
		));
    }

    public PalaceSpace loadObject(JsonObject json) {
        this.value = Integer.parseInt(json.getString("value"));
        setImageSource();
        return this;
    }
}
