package models;

import helpers.Json;
import helpers.JsonObject;

import java.util.HashSet;

public class Cell implements Serializable<Cell> {
    private Space space; 
    private int elevation; 
    private Player developerPlayer;
    private Developer developer;
    private int x; 
    private int y; 
    private HashSet<Cell> connectedCells;
    private boolean fromLowlands;
    private boolean fromMountains;

    public Cell(int x, int y, boolean fromLowlands, boolean fromMountains) {
    	this.x = x;
    	this.y = y;
    	this.fromLowlands = fromLowlands;
    	this.fromMountains = fromMountains;
      developer = null;
    }
    
    public Cell(Space space) {
        this.space = space;
    }
    
    public Space getSpace() {
        return space;
    }
    
    public void setSpace(Space space) {
        this.space = space;
    }
    
    public int getElevation() {
        return elevation; 
    }

    public boolean setElevation(int elevation) {
        if(elevation < 0) 
            return false; 
        this.elevation = elevation;
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HashSet<Cell> getConnectedCells() {
        return connectedCells;
    }

    public void setConnectedCells(HashSet<Cell> connectedCells) {
        this.connectedCells = connectedCells;
    }
    
    public boolean getFromMountains() {
    	return fromMountains;
    }
     
    public boolean getFromLowlands() {
    	return fromLowlands;
    }
    
    public void setDeveloper(Developer d)
    {
         developer = d;
    }
    
    public Developer getDeveloper()
    {
         return developer;
    }
    
    public boolean hasDeveloper()
    {
         return developer != null;
    }

    public String serialize() {
		return Json.jsonPair("Cell", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("space", (space == null ? null : space.serialize())),
				//Json.jsonPair("developerPlayer", developerPlayer.serialize()),
				Json.jsonPair("elevation", Json.jsonValue(elevation + "")),
				Json.jsonPair("x", Json.jsonValue(x + "")),
				Json.jsonPair("y", Json.jsonValue(y + "")),
    			Json.jsonPair("connectedCells", (connectedCells == null ? null : Json.serializeArray(connectedCells.toArray()))),
    			Json.jsonPair("fromLowLands", fromLowlands + ""),
    			Json.jsonPair("fromMountains", fromMountains + "")
		)));
    }
    
    public Cell loadObject(JsonObject json) {
    	this.space = (new Space()).loadObject(json.getJsonObject("space")); 
    	this.elevation = Integer.parseInt(json.getString("elevation"));
    	this.x = Integer.parseInt(json.getString("x"));
    	this.y = Integer.parseInt(json.getString("y"));
    	this.connectedCells = null;
    	this.fromMountains = Boolean.parseBoolean(json.getString("fromMountains"));
    	this.fromLowlands = Boolean.parseBoolean(json.getString("fromLowLands"));
    	return this;
    }
}


