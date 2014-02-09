package models;

import helpers.Json;

import java.util.HashSet;

public class Cell implements Serializable<Cell> {
    private Space space; 
    private int elevation; 
    private Player developerPlayer;
    private int x; 
    private int y; 
    private HashSet<Cell> connectedCells;

    public Cell() {

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

    public String serialize() {
		return Json.jsonPair("Cell", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("space", space.serialize()),
				Json.jsonPair("developerPlayer", developerPlayer.serialize()),
				Json.jsonPair("elevation", Json.jsonValue(elevation + "")),
				Json.jsonPair("x", Json.jsonValue(x + "")),
				Json.jsonPair("y", Json.jsonValue(y + "")),
    			Json.jsonPair("connectedCells", Json.serializeArray(connectedCells.toArray()))
		)));
    }
    
    public Cell loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}


