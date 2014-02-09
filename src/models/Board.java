package models;

import helpers.Json;

import java.util.Stack;
import java.util.*;

public class Board implements Serializable<Board> {
    private Cell[][] map; 
    private Stack<OneSpaceTile> irrigationTiles; 
    private Stack<ThreeSpaceTile> threeSpaceTiles;
    private Stack<OneSpaceTile>[] palaceTiles;
    private Cell[] outsideInnerCells;
    
    public Board() {
    	// Written by Nathan since I needed to integrate some functionality into Game.
    	// Let me know if you make any changes or have any questions.
    	this.map = new Cell[14][14];
    	this.outsideInnerCells = new Cell[44];
    	//outside inner cells are the boarder around inner java.
    	//These will be populated once a user places a tile onto it, for developer movement
    	int k = 0;
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
    			
    			//outsideInnerCells
    			if((x == 1 || x == 12) && (y != 0 && y != 13)){
    				outsideInnerCells[k] = map[x][y];
    				k++;
    			}
    			else if((y == 1 || y == 12) && (x != 0 && x != 13)){
    				outsideInnerCells[k] = map[x][y];
    				k++;
    			}
    		}
    	}
    	
    }
    
    // DEVELOPER MOVEMENT
 	// Move player from off the board to on the board. Returns true if
 	// successful and false otherwise. Deducts the appropriate number of action
 	// points depending on whether the player came from the mountains or the lowlands.
 	public boolean moveDeveloperOntoBoard(Developer developer, Cell cell) {
 		return true;
 	}
 	
 	// Uses the stack variable to create the developer path as the
 	// user highlights the desired cells. If the user decides to undo the 
 	// last highlighted Cell, the top Cell is popped off of the Stack.
 	public boolean createDeveloperPath(Developer developer) {
 		//TODO
 		return true;
 	}
 	
 	// Using the stack in the createDeveloperPath method, 
 	// we use this method to move the developer to its new location.
 	public boolean moveDeveloperAroundBoard(Developer developer) {
 		//TODO
 		return true;
 	}
 	
 	// Move a player that's already on the board off of the board.
 	// Add developer back into array of available developers.
 	public boolean moveDeveloperOffBoard(Developer developer) {
 		//TODO
 		return true;
 	}
 	
 	// PALACES
 	// Upgrades the palace assuming checkIfICanUpgradePalace returns true.
 	public void upgradePalace(Space palaceSpace, Cell cell) {
 		boolean canUpgrade = checkIfICanUpgradePalace(palaceSpace, cell);
 		if (canUpgrade) {
 			cell.setSpace(palaceSpace);
 		}
 		
 		
 		// TODO else can't upgrade palace, show dialog box
 		 
 	}
 	
 	// Checks all of the logic needed to make sure the user can legally 
 	// upgrade the palace. Calls checkForNumberOfVillages method.
 	private boolean checkIfICanUpgradePalace(Space palaceSpace, Cell cell) {
 		//TODO
 		return true;
 	}
 	
 	// Returns the number of village Spaces surrounding the given Cell. Called
 	// by checkIfICanUpgradePalace to make sure number of surrounding villages 
 	// is greater than or equal to the palace number.
 	private int checkForNumberOfVillages(Cell cell) {
 		//TODO
 		return 0;
 	}
 	
 	// Returns an integer array with the city ranks for each player. The indices in
 	// the array correspond to the indices for the players in the main player array. 
 	private int[] findCityRanks(Cell cell) {
 		//TODO
 		return new int[0];
 	}
 	
 	// IRRIGATION TILES
 	// Similarly to the method above, returns an integer array 
 	// for the players surrounding an irrigation tile.
 	private int[] findIrrigationRanks(Cell cell) {
 		//TODO
 		return new int[0];
 	}
 	
 	// TILE PLACEMENT AND HELPER METHODS
 	// Main method for placing a tile on the board, 
 	// uses several helper methods below.
 	public void placeTile(Cell cell, Tile tile) {
 		//TODO
 	}
 	
 	// Helper method for placeTile. Checks whether Tile can be placed
 	// in the Cell selected. This method also calls several helper methods.
 	private boolean checkValidTilePlacement(Cell cell, Tile tile) {
 		//TODO
 		return true;
 	}
 	
 	// Helper method for checkValidTilePlacement. Checks to make sure you're
 	// not placing a three tile on a three tile, two tile on a two tile, a 
 	// smaller tile on a larger tile, etc.
 	private boolean checkTilesBelow(Tile tile, Cell cell) {
 		//TODO
 		return true;
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

    public Cell[] getOutsideInnerCells(){
    	return outsideInnerCells;
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
