package models;

import helpers.Json;

import java.util.Stack;

public class Board implements Serializable<Board> {
    private Cell[][] map; 
    private Stack<OneSpaceTile> irrigationTiles; 
    private Stack<ThreeSpaceTile> threeSpaceTiles;
    private Stack<OneSpaceTile>[] palaceTiles;
    
    public Board() {

    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public Stack<OneSpaceTile> getIrrigationTiles() {
        return irrigationTiles;
    }

    public void setIrrigationTiles(Stack<OneSpaceTile> irrigationTiles) {
        this.irrigationTiles = irrigationTiles;
    }

    public Stack<ThreeSpaceTile> getThreeSpaceTiles() {
        return threeSpaceTiles;
    }

    public void setThreeSpaceTiles(Stack<ThreeSpaceTile> threeSpaceTiles) {
        this.threeSpaceTiles = threeSpaceTiles;
    }

    public Stack<OneSpaceTile>[] getPalaceTiles() {
        return palaceTiles;
    }

    public void setPalaceTiles(Stack<OneSpaceTile>[] palaceTiles) {
        this.palaceTiles = palaceTiles;
    }

    public String serialize() {
		return Json.jsonPair("Board", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("map", Json.serializeArray(map)),
				Json.jsonPair("irrigationTiles", Json.serializeArray(irrigationTiles)),
				Json.jsonPair("threeSpaceTiles", Json.serializeArray(threeSpaceTiles)),
				Json.jsonPair("palaceTiles", Json.serializeArray(palaceTiles))
		)));
    }

    public Board loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
