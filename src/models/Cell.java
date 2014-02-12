package models;

import helpers.Json;
import helpers.JsonObject;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

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
    
    public void setDeveloper(Developer developer){
    	this.developer = developer;
    }
    
    public void removeDeveloper(Developer developer){
    	developer = null;
    }
    
    public Developer getDeveloper(){
    	
    	return developer;
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
    
    
    
    public boolean hasDeveloper()
    {
         return developer != null;
    }

    public String serialize() {
    	LinkedList<String> connections = new LinkedList<String>(); 
    	if(connectedCells != null) {
    		for(Cell cell : connectedCells) {
    			connections.add(Json.jsonObject(Json.jsonMembers(Json.jsonPair("x", cell.x + ""), Json.jsonPair("y", cell.y + ""))));
    		}
    	}
		return Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("space", (space == null ? null : space.serialize())),
				Json.jsonPair("elevation", Json.jsonValue(elevation + "")),
				Json.jsonPair("x", Json.jsonValue(x + "")),
				Json.jsonPair("y", Json.jsonValue(y + "")),
    			Json.jsonPair("connectedCells", connections.size() > 0 ? null : Json.jsonArray(Json.jsonElements(connections.toArray(new String[1])))),
    			Json.jsonPair("fromLowLands", Json.jsonValue(fromLowlands + "")),
    			Json.jsonPair("fromMountains", Json.jsonValue(fromMountains + ""))
		));
    }
    
    public Cell loadObject(JsonObject json) {
    	if(json == null) return null;
    	if(json.getObject("space") != null) {
    		String type = json.getJsonObject("space").getString("type");
    		if(type.equals("RICE"))
    			this.space = (new RiceSpace()).loadObject(json.getJsonObject("space"));
    		if(type.equals("VILLAGE"))
    			this.space = (new RiceSpace()).loadObject(json.getJsonObject("space"));
    		if(type.equals("IRRIGATION"))
    			this.space = (new RiceSpace()).loadObject(json.getJsonObject("space"));
    		if(type.equals("PALACE"))
    			this.space = (new RiceSpace()).loadObject(json.getJsonObject("space"));
    	}
    	this.elevation = Integer.parseInt(json.getString("elevation"));
    	this.x = Integer.parseInt(json.getString("x"));
    	this.y = Integer.parseInt(json.getString("y"));
    	if(json.getObject("player") != null) {
    		this.developerPlayer = (Player)json.getObject("player");
    	}
    	if(json.getObject("list") != null) {
    		@SuppressWarnings("unchecked")
			ArrayList<Cell> tempCells = ((ArrayList<Cell>)json.getObject("list"));
    		for(Cell cell : tempCells) 
    			connectedCells.add(cell);
    	}
    	this.connectedCells = null;
    	this.fromMountains = Boolean.parseBoolean(json.getString("fromMountains"));
    	this.fromLowlands = Boolean.parseBoolean(json.getString("fromLowLands"));
    	return this;
    }
    
    public void loadDeveloper(Developer developer) {
    	if(developer.getCurrentCell() == this) {
    		this.developer = developer;
    		this.developerPlayer = developer.getOwner();
    	}
    }
    
    public String toString() { 
    	return space + " " + elevation + " "
    			+ x + " " + y + " " + connectedCells + " " + fromLowlands + " " + fromMountains;
    }
}


