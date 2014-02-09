package models;

import helpers.Json;

import java.util.Stack;
import java.util.*;

public class Board implements Serializable<Board> {
    private Cell[][] map; 
    private Stack<OneSpaceTile> irrigationTiles; 
    private Stack<ThreeSpaceTile> threeSpaceTiles;
    private Stack<OneSpaceTile>[] palaceTiles;
    
    public Board() {
    	// Written by Nathan since I needed to integrate some functionality into Game.
    	// Let me know if you make any changes or have any questions.
    	map = new Cell[14][14];
    	for (int x = 0; x < map.length; x++) {
    		for (int y = 0; y < map[0].length; y++) {
    			if (x == 0 || x == 1) {
    				map[x][y] = new Cell(x, y, false, true);
    			}
    			
    			if (x <= 6 && (y <= 1 || y >= 12)) {
    				map[x][y] = new Cell(x, y, false, true);
    			}
    			
    			if (x >= 7 && (y <= 1 || y >= 12)) {
    				map[x][y] = new Cell(x, y, true, false);
    			}
    			
    			if (x == 12 || x == 13) {
    				map[x][y] = new Cell(x, y, true, false);
    			}
    		}
    	}
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
    
    //Given an X and Y, this method sets the connectedCells set of the
    //cell at the given coordinates
    public void SetConnectedCells(int x, int y)
    {
         Cell root = map[x][y];
         ArrayList<Cell> connected = new ArrayList<Cell>();
         connected.add(root);
         
         int i = 0;
         while(i < connected.size())
         {
            Cell temp = connected.get(i);
            HashSet<Cell> adjacent = new HashSet<Cell>();
            if(y < 14 && map[y+1][x].getSpace().getClass() == root.getSpace().getClass())
               adjacent.add(map[y+1][x]);
            if(y > 0 && map[y-1][x].getSpace().getClass() == root.getSpace().getClass())
               adjacent.add(map[y-1][x]);
            if(x < 14 && map[y][x+1].getSpace().getClass() == root.getSpace().getClass())
               adjacent.add(map[y][x+1]);
            if(x > 0 && map[y][x-1].getSpace().getClass() == root.getSpace().getClass())
               adjacent.add(map[y][x-1]);
            
            Iterator it = adjacent.iterator();
            while(it.hasNext())
            {
               Cell next = (Cell)it.next();
               if(!connected.contains(next))
                  connected.add(next);
            }
            i++;
         }
         
         HashSet<Cell> connectedSet = new HashSet<Cell>();
         for(Cell c : connected)
            connectedSet.add(c);
         
         root.setConnectedCells(connectedSet);
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
