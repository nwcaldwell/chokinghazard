package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Game implements Serializable {
	// VARIABLES
	private Board board;
	private Player[] players;
	private boolean isFinalTurn;
	private int indexOfCurrentPlayer;
	private Stack<Cell> stack;
	private int numPlayers;
	
	// CONSTRUCTORS
	//default constructor
	public Game(){
		
	}
	
	// Main Constructor
	public Game(int numPlayers) {
		this.board = new Board();
		this.players = new Player[numPlayers];
		this.numPlayers = numPlayers;
		this.isFinalTurn = false;
		this.indexOfCurrentPlayer = 0;
		this.stack = new Stack<Cell>();
		
		//TODO implement a way to keep track of player colors from views to player models - sydney
		for(int i = 0; i < numPlayers; ++i){
			players[i] = new Player(Color.BLUE);
		}
	}
	
	// GET/SET METHODS
	public Player getCurrentPlayer() {
		return players[indexOfCurrentPlayer];
	}
	public int getNumberOfPlayers(){
		return players.length;
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
		return true;
	}
	
	// Using the stack in the createDeveloperPath method, 
	// we use this method to move the developer to its new location.
	public boolean moveDeveloperAroundBoard(Developer developer) {
		return true;
	}
	
	// Move a player that's already on the board off of the board.
	// Add developer back into array of available developers.
	public boolean moveDeveloperOffBoard(Developer developer) {
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
		return true;
	}
	
	// Returns the number of village Spaces surrounding the given Cell. Called
	// by checkIfICanUpgradePalace to make sure number of surrounding villages 
	// is greater than or equal to the palace number.
	private int checkForNumberOfVillages(Cell cell) {
		return 0;
	}
	
	// Returns an integer array with the city ranks for each player. The indices in
	// the array correspond to the indices for the players in the main player array. 
	private int[] findCityRanks(Cell cell) {
		return new int[0];
	}
	
	// IRRIGATION TILES
	// Similarly to the method above, returns an integer array 
	// for the players surrounding an irrigation tile.
	private int[] findIrrigationRanks(Cell cell) {
		return new int[0];
	}
	
	// TILE PLACEMENT AND HELPER METHODS
	// Main method for placing a tile on the board, 
	// uses several helper methods below.
	public void placeTile(Cell cell, Tile tile) {
		
	}
	
	// Helper method for placeTile. Checks whether Tile can be placed
	// in the Cell selected. This method also calls several helper methods.
	private boolean checkValidTilePlacement(Cell cell, Tile tile) {
		return true;
	}
	
	// Helper method for checkValidTilePlacement. Checks to make sure you're
	// not placing a three tile on a three tile, two tile on a two tile, a 
	// smaller tile on a larger tile, etc.
	private boolean checkTilesBelow(Tile tile, Cell cell) {
		return true;
	}

	// ACTION TOKEN
	// Increases number of action points available for that players 
	// turn by one. Changes the ifActionTokenUsed boolean to true
	public boolean useActionToken() {
		boolean alreadyUsed = players[indexOfCurrentPlayer].isIfActionTokenUsed();
		int numActionTokensAvailable = players[indexOfCurrentPlayer].getActionTokens();
		if (!alreadyUsed) {
			if (numActionTokensAvailable > 0) {
				players[indexOfCurrentPlayer].useActionToken();
				return true;
			}
			
			else {
				JOptionPane.showMessageDialog(null, "You're out of Action Tokens!");
				return false;
			}
		}
		
		else {
			JOptionPane.showMessageDialog(null, "You've already used an Action Token this turn!");
			return false;
		} 
	}
	
	// END OF TURN ACTIONS
	// Ends the current players turn and executes all necessary actions.
	public void nextTurn() {
		if (!players[indexOfCurrentPlayer].isIfPlacedLandTile()) {
			JOptionPane.showMessageDialog(null, "You haven't placed a land tile yet! :(");
			return;
		}
		
		else {
			indexOfCurrentPlayer++;
			indexOfCurrentPlayer %= numPlayers;
			players[indexOfCurrentPlayer].startTurn();
		}	
	}
	
	// Ask user how many fame points he earned and return the integer provided.
	public int getFamePointCountFromUser() {
		boolean valid = false;
		int famePoints = -1;
		while (!valid) {
		    try {
		        famePoints = Integer.parseInt(JOptionPane.showInputDialog("How many fame points did you earn on this turn?"));
		        if (famePoints >= 0) {
		        	valid = true;
		        }
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Not a valid number, please try again.");
		    }
		}
		
		return famePoints;
	}
	
	// Checks to see whether current player placed the last three piece tile. 
	public boolean ifIPlacedLastThreePieceTile() {
		return board.getThreeSpaceTiles().size() == 0;
	}
	
	// SERIALIZABLE
	// Inherited from the serializable interface. This method turns 
	// the game object into a string so it can be saved to a file.
	public String serialize() {
		String serial = "";
		serial += board.serialize();
		for (int i = 0; i < numPlayers; i++) {
			serial += players[i].serialize();
		}
		
		return serial;
	}
	
	// The polymorphic method loadObject is inherited from the serializable interface.
	// This method returns the Game
	public Game loadObject(String serial) {
		return new Game();
	}
}