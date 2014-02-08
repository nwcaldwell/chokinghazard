package models;

import java.util.Stack;

public class Board implements Serializable<Board> {
    private Cell[][] map; 
    private Stack<OneSpaceTile> irrigationTiles; 
    private Stack<ThreeSpaceTile> threeSpaceTiles;
    private Stack<OneSpaceTile>[] palaceTiles;

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }
    
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

    public Board loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
