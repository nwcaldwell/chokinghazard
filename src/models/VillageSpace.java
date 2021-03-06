package models;

import helpers.Json;
import helpers.JsonObject;

public class VillageSpace extends Space {
	private String imageSource = "images/oneTile_village.png";
    private int palacex = -1; 
    private int palacey = -1; 

    public VillageSpace() {
        this.type = Space.SpaceType.VILLAGE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }

    public int getPalacey() {
        return palacey;
    }

    public void setPalacey(int palacey) {
        this.palacey = palacey;
    }

    public int getPalacex() {
        return palacex;
    }

    public void setPalacex(int palacex) {
        this.palacex = palacex;
    }
    
    public String getImageSource(){
    	return this.imageSource;
    }
    
    public String toString(){
    	return this.type.toString();
    }

    public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("type", Space.SpaceType.VILLAGE.toString()),
			Json.jsonPair("palacex", Json.jsonValue(palacex + "")),
			Json.jsonPair("palacey", Json.jsonValue(palacey + ""))
		));
    }

    public VillageSpace loadObject(JsonObject json) {
    	this.palacex = Integer.parseInt(json.getString("palacex"));
    	this.palacey = Integer.parseInt(json.getString("palacey"));
    	return this;
    }
}
