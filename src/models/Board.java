package models;

import helpers.Json;
import helpers.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import models.Space.SpaceType;

public class Board implements Serializable<Board> {
	private Cell[][] map;
	private Cell[] outsideInnerCells;
	private Stack<Cell> path;
	private int decrementedActionPoints;

	public Board() {
		// Written by Nathan since I needed to integrate some functionality into
		// Game.
		// Let me know if you make any changes or have any questions.
		this.map = new Cell[14][14];
		this.outsideInnerCells = new Cell[44];
		// outside inner cells are the boarder around inner java.
		// These will be populated once a user places a tile onto it, for
		// developer movement
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

				// outsideInnerCells
				if ((x == 1 || x == 12) && (y != 0 && y != 13)) {
					outsideInnerCells[k] = map[x][y];
					k++;
				} else if ((y == 1 || y == 12) && (x != 0 && x != 13)) {
					outsideInnerCells[k] = map[x][y];
					k++;
				}
			}
		}
		
		this.path = new Stack<Cell>();
		decrementedActionPoints = 0;

	}

	// DEVELOPER MOVEMENT
	// Move player from off the board to on the board. Returns true if
	// successful and false otherwise. Deducts the appropriate number of action
	// points depending on whether the player came from the mountains or the
	// lowlands.

	public boolean moveDeveloperOntoBoard(Player player, Cell cell) {
		// it needs to be in outside square
		// has to be rice and village
		if (cell.getSpace().getType() == SpaceType.RICE
				&& cell.getSpace().getType() == SpaceType.VILLAGE) {
			if (cell.getFromMountains() && player.getActionPoints() == 2
					&& player.isIfPlacedLandTile()) {
				
				Developer dev =new Developer(player, cell);
				player.addDevOnBoard(dev);
				cell.setDeveloper(dev);

				return true;
			}

			else if (cell.getFromMountains() && player.getActionPoints() >= 2) {
				
				Developer dev =new Developer(player, cell);
				player.addDevOnBoard(dev);
				cell.setDeveloper(dev);

				return true;
			}

			else if (cell.getFromLowlands() && player.getActionPoints() == 1
					&& player.isIfPlacedLandTile()) {
				
				Developer dev =new Developer(player, cell);
				player.addDevOnBoard(dev);
				cell.setDeveloper(dev);

				return true;
			}

			else if (cell.getFromLowlands() && player.getActionPoints() >= 1) {
				
				
				Developer dev =new Developer(player, cell);
				player.addDevOnBoard(dev);
				cell.setDeveloper(dev);


				return true;
			}
			
		}
		
		return false;
		

	}
	
	// Move a player that's already on the board off of the board.
		// Add developer back into array of available developers.
	public boolean moveDeveloperOffBoard(Developer developer) {

		developer.getOwner().removeOffBoard(developer);
		
		Cell cell = developer.getCurrentCell();
		
		cell.removeDeveloper(developer);
		
		return true;
	}

	// Uses the stack variable to create the developer path as the
	// user highlights the desired cells. If the user decides to undo the
	// last highlighted Cell, the top Cell is popped off of the Stack.
	public boolean createDeveloperPath(Developer developer, Cell cell) {


		try {
			if (cell.getSpace().getType() == SpaceType.RICE
					&& cell.getSpace().getType() == SpaceType.VILLAGE) {
				if (cell.getSpace().getType() == developer.getCurrentCell()
						.getSpace().getType()) {

					path.push(cell);
					return true;
				} else if (developer.getOwner().getActionPoints() > 0) {

					decrementedActionPoints++;

					developer.getOwner().decrementActionPoints(1);

					path.push(cell);
					return true;

				} else
					return false;

			} else {
				return false;
			}
		} catch (NullPointerException e) {


		}
		
		return false;

	}
	
	public void deleteDeveloperPath(Developer developer) {

		developer.getOwner().incrementActionPoints(decrementedActionPoints); // restores
																				// the
																				// ActionPoints
		decrementedActionPoints = 0; // it makes decrementedActionPoints to be
										// use again

		for (int i = 0; i < path.size(); i++) { // push until u get to the last
												// cell
			path.pop();
		}

	}

	// Using the stack in the createDeveloperPath method,
	// we use this method to move the developer to its new location.
	public void moveDeveloperAroundBoard(Developer developer) {
		
		for(int i = 0; i < path.size(); i++){				//push until u get to the last cell
			path.pop();
		}
		
		developer.setCurrentCell(path.pop());
		
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
	// upgrade the palace. Calls checkForNumberOfVillages method as well as findCityRanks.
	private boolean checkIfICanUpgradePalace(Space palaceSpace, Cell cell) {
		// TODO
		return true;
	}

	// Returns the number of village Spaces surrounding the given Cell. Called
	// by checkIfICanUpgradePalace to make sure number of surrounding villages
	// is greater than or equal to the palace number.
	private int checkForNumberOfVillages(Cell cell) 
   {
		setConnectedCells(cell);
		return cell.getConnectedCells().size();
	}

	// Returns an integer array with the city ranks for each player. The indices
	// in the array correspond to the indices for the players in the main player
	// array. For example, if players 1, 2, 3, and 4 have the 3rd, 4th, 1st, and 2nd highest 
	// ranking developers respectively, return the array [3, 4, 1, and 2]. remember players array is indexed starting at 0
	private int[] findCityRanks(Cell cell) {
		setConnectedCells(cell);
      
      HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
      int max = 0;
      
      for(Cell c : cell.getConnectedCells())
      {
         if(c.hasDeveloper())
         {
            Player p = c.getDeveloper().getOwner();
            int rank = c.getElevation();
            if(max < rank)
            {
               max = rank;
            }
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }
      }
      //we now have each player mapped to their rank or not mapped if they don't have a developer 
      //on the city.
      
      
      ArrayList<Player> maxRank = new ArrayList<Player>();
      for(Player p : scores.keySet())
      {
         if(scores.get(p) == max)
            maxRank.add(p);
      }
      
		return new int[0];
	}

	// IRRIGATION TILES
	// Similarly to the method above, returns an integer array
	// for the players surrounding an irrigation tile. For example, if players 1, 2, 3, and 4 have the 3rd, 4th, 1st, and 2nd highest 
	// ranking developers respectively, return the array [3, 4, 1, and 2]. remember players array is indexed starting at 0
	
	private int[] findIrrigationRanks(Cell cell) {
        
         int x = cell.getX();
         int y = cell.getY();
         int max = 0;
         
         HashMap<Player, Integer> scores = new HashMap<Player, Integer>();

         
		   if (y < 14 && map[y + 1][x].hasDeveloper())
			{
            Cell c = map[y + 1][x];
            Player p = c.getDeveloper().getOwner();
            int rank = c.getElevation();
            if(max < rank)
            {
               max = rank;
            }
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }
			if (y > 0 && map[y - 1][x].hasDeveloper())
			{
            Cell c = map[y - 1][x];
				Player p = cell.getDeveloper().getOwner();
            int rank = cell.getElevation();
            if(max < rank)
            {
               max = rank;
            }
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }
			if (x < 14 && map[y][x + 1].hasDeveloper())
			{
            Cell c = map[y][x+1];
            Player p = c.getDeveloper().getOwner();
            int rank = c.getElevation();
            if(max < rank)
            {
               max = rank;
            }
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }
			if (x > 0 && map[y][x - 1].hasDeveloper())
			{
            Cell c = map[y][x-1];
            Player p = cell.getDeveloper().getOwner();
            int rank = cell.getElevation();
            if(max < rank)
            {
               max = rank;
            }
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }

      ArrayList<Player> maxRank = new ArrayList<Player>();
      for(Player p : scores.keySet())
      {
         if(scores.get(p) == max)
            maxRank.add(p);
      }
      
		return new int[0];
	}

	// TILE PLACEMENT AND HELPER METHODS
	// Main method for placing a tile on the board,
	// uses several helper methods below.
	// Returns true if successful

	public boolean placeTile(Cell[][] cells, Tile tile) {

		if (checkValidTilePlacement(cells, tile)) {
			Space[][] spacesArray = tile.getSpaces();
		
			for (int i = 0; i < spacesArray.length; i++) {
				for (int j = 0; j < spacesArray[0].length; j++) {
					if (spacesArray[i][j] != null) {
						cells[i][j].setSpace(spacesArray[i][j]);
						cells[i][j].setElevation(cells[i][j].getElevation() + 1);
					}
				}
			}
			
			return true;
		}
		
		else {
			return false;
		}
	}

	// Helper method for placeTile. Checks whether Tile can be placed
	// in the Cell selected. This method also calls several helper methods.

	private boolean checkValidTilePlacement(Cell[][] cells, Tile tile) {
		if (checkTilesBelow(cells, tile)) {
			// TODO check other factors in valid tile placement not related to cells below
			// This could be connecting cities. Don't worry about this Jose -Nathan
			
			return true;
		}
		
		return false;

	}
	
	private boolean checkPalacePlacement(Cell[][] cells, Tile tile){
		
		Space[][] spaces = tile.getSpaces();
		
		for(int i = 0; i < spaces.length ; i++) 
			for(int j = 0; j < spaces[i].length; j++)
				if(spaces[i][j] != null)
					if(cells[i][j] != null && cells[i][j].getSpace().getType() == SpaceType.PALACE)
						return false;
		
		return true;
	}
	
	private boolean checkIrrigationPlacement(Cell[][] cells, Tile tile){
		
		Space[][] spaces = tile.getSpaces();
		
		for(int i = 0; i < spaces.length ; i++) 
			for(int j = 0; j < spaces[i].length; j++)
				if(spaces[i][j] != null)
					if(cells[i][j] != null && cells[i][j].getSpace().getType() == SpaceType.IRRIGATION)
						return false;
		
		return true;
	}

	// Helper method for checkValidTilePlacement. Checks to make sure you're
	// not placing a three tile on a three tile, two tile on a two tile, a
	// smaller tile on a larger tile, etc.
	private boolean checkTilesBelow(Cell[][] cells, Tile tile) {

		Space[][] spaces = tile.getSpaces();
		
		HashSet<Cell> ref = new HashSet<Cell>(); 
		
		int numSpacesOnTile;
		
		switch(tile.toString()){
		case"THREE SPACE TILE":
			numSpacesOnTile = 3;
			break;
		case "TWO SPACE TILE":
			numSpacesOnTile = 2;
			break;
		case "VILLAGE":
			numSpacesOnTile = 1;
			break;
		case "RICE":
			numSpacesOnTile = 1;
			break;
		case "PALACE":
			numSpacesOnTile = 1;
			break;
		case "IRRIGATION":
			numSpacesOnTile = 1;
			break;
		default: 
				return false;
		
		}	
		
		
		for(int i = 0; i < spaces.length ; i++) 
			for(int j = 0; j < spaces[i].length; j++)
				if (spaces[i][j] != null)
					if (cells[i][j].getSpace() != null)
						if(ref == null)
							if (cells[i][j].getConnectedCells().size() == numSpacesOnTile)
								ref = cells[i][j].getConnectedCells();
							else
								return true;
						else
							if(!ref.equals(cells[i][j].getConnectedCells()))
								return true;
					else
						return true;
		
		int height = -1;
		for(int i = 0; i < spaces.length; i++)
			for(int j = 0; j < spaces[i].length; i++)
				if(spaces[i][j] != null)
					if(height == -1)
						height = cells[i][j].getElevation();
					else
						if(height != cells[i][j].getElevation())
							return false;

		return true;
			

//		boolean heightBoolean = true;
//		boolean connectedCellsBoolean = false;
//		int height = -1;
		
//		for(int i = 0; i < spaces.length ; i++) 
//			for(int j = 0; j < spaces[i].length; j++) 
//				if (spaces[i][j] != null) {
//					if (cells[i][j].getSpace() != null) {
//						if (height == -1) {
//							height = cells[i][j].getElevation();
//						}
//						
//						else {
//							if (height != cells[i][j].getElevation()) {
//								heightBoolean = false;
//							}
//						}
//						
//						if(ref == null)
//							if (cells[i][j].getConnectedCells().size() == numSpacesOnTile) {
//								ref = cells[i][j].getConnectedCells();
//							}
//						
//							else {
//								connectedCellsBoolean = true;
//							}
//						
//						else {
//							if(!ref.equals(cells[i][j].getConnectedCells())) {
//								connectedCellsBoolean = true;
//							}
//						}
//						
//					else {
//						connectedCellsBoolean = true;
//					}
//		
//		return false;
		

	}

	public Cell[][] getMap() {
		return map;
	}
	
	public Cell getCellAtLocation(int x, int y){
		return map[x][y];
	}
	public Cell getCellAtPixel(int x, int y){
		return map[((x%700)/50)][((y%700)/50)];
	}

	// Given an X and Y, this method sets the connectedCells set of the
	// cell at the given coordinates
	public void setConnectedCells(Cell root) {
      
		ArrayList<Cell> connected = new ArrayList<Cell>();
      int x = root.getX();
      int y = root.getY();
      
		connected.add(root);

		int i = 0;
		while (i < connected.size()) {
			Cell temp = connected.get(i);
			HashSet<Cell> adjacent = new HashSet<Cell>();
			if (y < 14
					&& map[y + 1][x].getSpace().getClass() == root.getSpace()
							.getClass())
				adjacent.add(map[y + 1][x]);
			if (y > 0
					&& map[y - 1][x].getSpace().getClass() == root.getSpace()
							.getClass())
				adjacent.add(map[y - 1][x]);
			if (x < 14
					&& map[y][x + 1].getSpace().getClass() == root.getSpace()
							.getClass())
				adjacent.add(map[y][x + 1]);
			if (x > 0
					&& map[y][x - 1].getSpace().getClass() == root.getSpace()
							.getClass())
				adjacent.add(map[y][x - 1]);

			Iterator it = adjacent.iterator();
			while (it.hasNext()) {
				Cell next = (Cell) it.next();
				if (!connected.contains(next))
					connected.add(next);
			}
			i++;
		}

		HashSet<Cell> connectedSet = new HashSet<Cell>();
		for (Cell c : connected)
			connectedSet.add(c);

		root.setConnectedCells(connectedSet);

	}

	public Cell[] getOutsideInnerCells() {
		return outsideInnerCells;
	}

	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("map", Json.serializeArray(map)),
				Json.jsonPair("outsideInnerCells", Json.serializeArray(outsideInnerCells))
		));
    }

    public Board loadObject(JsonObject json) {
    	Cell[][] map = new Cell[14][14];
    	Object[] rows = (Object[]) json.getObject("map");
    	for(int x = 0; x < 14; ++x) {
        	Object[] fields = (Object[]) rows[x];
    		for(int y = 0; y < 14; ++y) {
    			if(fields[y] == null)
    				continue;
    			map[x][y] = (new Cell(null)).loadObject((JsonObject)fields[y]);
    		}
    	}
    	Cell[] cells = new Cell[44];
    	rows = (Object[]) json.getObject("outsideInnerCells");
		for(int y = 0; y < 44; ++y) {
			if(rows[y] == null)
				continue;
			cells[y] = (new Cell(null)).loadObject((JsonObject)rows[y]);
		}
		this.outsideInnerCells = cells;
		this.map = map;
		return this;
    }
    
    public String toString() {
    	String ret = ""; 
    	for(Cell[] row : map) 
    		for(Cell cell : row) 
    			ret += cell == null ? null : cell.toString() + " ";
    	for(Cell cell : outsideInnerCells)
    		ret += cell.toString() + " "; 
    	return ret + "  " + path.toString() + " " + decrementedActionPoints;
    }
}

