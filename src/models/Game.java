package models;

import java.io.Serializable;
import java.util.Stack;

public class Game implements Serializable {
	// VARIABLES
	//private Board board;
	//private Player[] players;
	private boolean isFinalTurn;
	private int indexOfCurrentPlayer;
	//private Stack<Cell> stack;
	
	// CONSTRUCTORS
	// Default Constructor
	public Game() {
		
	}
	
	// Main Constructor
	public Game(Board board, Player[] players, boolean isFinalTurn, int indexOfCurrentPlayer, Stack<Cell> stack) {
		
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
	public void useActionToken() {
		
	}
	
	// END OF TURN ACTIONS
	// Ends the current players turn and executes all necessary actions.
	public void endTurn() {
		
	}
	
	// Initializes next players turn.
	public void nextPlayerTurn() {
		
	}
	
	// Ask user how many fame points he earned and return the integer provided.
	public int getFamePointCountFromUser() {
		return 0;
	}
	
	// Checks to see whether current player placed the last three piece tile. 
	public boolean ifIPlacedLastThreePieceTile() {
		return true;
	}
	
	// SERIALIZABLE
	// Inherited from the serializable interface. This method turns 
	// the game object into a string so it can be saved to a file.
	public String serialize() {
		return "";
	}
	
	// The polymorphic method loadObject is inherited from the serializable interface.
	// This method returns the Game
	public Game loadObject(String serial) {
		return new Game();
	}
}