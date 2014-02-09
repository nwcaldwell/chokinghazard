package models;

import helpers.Json;

import java.util.LinkedList;

public abstract class Tile implements Serializable<Tile> {

    private String imageSource;
    protected Space[][] spaces;
    
    public Tile(Space[][] spaces)
    {
    	this.spaces = spaces;
    }
    
    public abstract void rotate();

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Space[][] getSpaces() {
        return spaces;
    }

    public void setSpaces(Space[][] spaces) {
        this.spaces = spaces;
    }
    
	/**
	* serialize the Tile and return it as a String
	*/
    public String serialize() {
        return Json.jsonPair(this.getClass().getSimpleName(), Json.jsonObject(Json.jsonMembers(
            Json.jsonPair("string", Json.jsonValue(imageSource)),
            Json.jsonPair("spaces", Json.serializeArray(spaces))
        )));
    }

    public Tile loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
