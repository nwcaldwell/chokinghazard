package models;

import helpers.Json;

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

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String serialize() {
		return Json.jsonPair("Space", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("SpaceType", Json.jsonValue(type.toString())),
				Json.jsonPair("String", Json.jsonValue(imageSource))
		)));
    }

    public Space loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
