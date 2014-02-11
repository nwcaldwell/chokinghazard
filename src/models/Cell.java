package models;

import helpers.Json;
import helpers.JsonObject;

import java.util.HashSet;

public class Cell implements Serializable<Cell> {
    private Space space; 
    private int elevation; 
    private Player developerPlayer;
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
    	Cell cell = new Cell((new Space()).loadObject(json.getObject("space"))); 
    	cell.setElevation(Integer.parseInt(json.getString("elevation")));
    	cell.setX(Integer.parseInt(json.getString("x")));
    	cell.setY(Integer.parseInt(json.getString("y")));
    	cell.setConnectedCells(connectedCells);
    	cell.setFromMountains(Boolean.parseBoolean(json.getString("fromMountains")));
    	cell.setFromLowlands(Boolean.parseBoolean(json.getString("fromLowLands")));
		return cell;
    }

	private void setFromMountains(boolean fromMountains) {
		this.fromMountains = fromMountains;
	}

	private void setFromLowlands(boolean fromLowlands) {
		this.fromLowlands = fromLowlands;
	}
}


