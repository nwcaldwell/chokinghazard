package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.JOptionPane;

import views.GamePanel;

public class Game implements Serializable {
	// VARIABLES
	private GamePanel gamePanel;
	private Board board;
	private int numPlayers;
	private Player[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalTurn;
	private Stack<Cell> stack;

	
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
		this.gamePanel = new GamePanel(numPlayers, this);
		
		//initialize the players and their views
		initPlayers();
	}
	
	// GET/SET METHODS
	public Player getCurrentPlayer() {
		return players[indexOfCurrentPlayer];
	}
	public GamePanel getGamePanel(){
		return this.gamePanel;
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
		//TODO this goes in the Board
		return true;
	}
	
	// Using the stack in the createDeveloperPath method, 
	// we use this method to move the developer to its new location.
	public boolean moveDeveloperAroundBoard(Developer developer) {
		//TODO this goes in the Board
		return true;
	}
	
	// Move a player that's already on the board off of the board.
	// Add developer back into array of available developers.
	public boolean moveDeveloperOffBoard(Developer developer) {
		//TODO this goes in the Board
		return true;
	}
	
	// PALACES
	// Upgrades the palace assuming checkIfICanUpgradePalace returns true.
	public void upgradePalace(Space palaceSpace, Cell cell) {
		boolean canUpgrade = checkIfICanUpgradePalace(palaceSpace, cell);
		if (canUpgrade) {
			cell.setSpace(palaceSpace);
		}
		
		// TODO do we need a postcondition other than simply telling the user that they can't upgrade? -brett
		else 
			JOptionPane.showMessageDialog(null, "Cannot upgrade palace!");
		 
	}
	
	// Checks all of the logic needed to make sure the user can legally 
	// upgrade the palace. Calls checkForNumberOfVillages method.
	private boolean checkIfICanUpgradePalace(Space palaceSpace, Cell cell) {
		//TODO this goes in the Board
		return true;
	}
	
	// Returns the number of village Spaces surrounding the given Cell. Called
	// by checkIfICanUpgradePalace to make sure number of surrounding villages 
	// is greater than or equal to the palace number.
	private int checkForNumberOfVillages(Cell cell) {
		//TODO this goes in the Board
		return 0;
	}
	
	// Returns an integer array with the city ranks for each player. The indices in
	// the array correspond to the indices for the players in the main player array. 
	private int[] findCityRanks(Cell cell) {
		//TODO this goes in the Board
		return new int[0];
	}
	
	// IRRIGATION TILES
	// Similarly to the method above, returns an integer array 
	// for the players surrounding an irrigation tile.
	private int[] findIrrigationRanks(Cell cell) {
		//TODO this goes in the Board
		return new int[0];
	}
	
	// TILE PLACEMENT AND HELPER METHODS
	// Main method for placing a tile on the board, 
	// uses several helper methods below.
	public void placeTile(Cell cell, Tile tile) {
		//TODO this goes in the Board
	}
	
	// Helper method for placeTile. Checks whether Tile can be placed
	// in the Cell selected. This method also calls several helper methods.
	private boolean checkValidTilePlacement(Cell cell, Tile tile) {
		//TODO this goes in the Board
		return true;
	}
	
	// Helper method for checkValidTilePlacement. Checks to make sure you're
	// not placing a three tile on a three tile, two tile on a two tile, a 
	// smaller tile on a larger tile, etc.
	private boolean checkTilesBelow(Tile tile, Cell cell) {
		//TODO this goes in the Board
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
				gamePanel.useActionToken(getCurrentPlayer().getActionTokens());
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
			//get the current fame points to add and update the view
			int famePointsToAdd = getFamePointCountFromUser();
			players[indexOfCurrentPlayer].addFamePoints(famePointsToAdd);
			famePointsToAdd = players[indexOfCurrentPlayer].getFamePoints();
			gamePanel.getPlayerPanels()[indexOfCurrentPlayer].setFamePoints(famePointsToAdd);
			
			//advance to the next player
			indexOfCurrentPlayer++;
			indexOfCurrentPlayer %= numPlayers;
			gamePanel.nextTurn();
			players[indexOfCurrentPlayer].startTurn();
		}	
	}
	
	// Ask user how many fame points he earned and return the integer provided.
	private int getFamePointCountFromUser() {
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
	
	public void tabThroughDevelopers(){
		System.out.println("tabbing");
		//TODO
		//get the current player's set of developers on the board
		//and traverse through them. 
		//need a variable for current developer. 
		//Because if a developer is selected, then need a way to select that developer
	}
	
	public void rotateCurrentComponent(){
		//check if currentComponent is a tile
		//if so, call the rotate method
		//else do nothing
	}
	
	public void addDeveloperToBoard(){
		//TODO need to check the board if there's any tiles on the outermost-inner java layer
		gamePanel.moveDeveloperOntoBoard(50, 50);
		//TODO change this to a real developer
		//currentComponent = new Developer(currentGame.getCurrentPlayer());
		//get the index of the first developer not on the board in the players.developer[]
		//get that developer's cell position, and set to isOnBoard or whatever the boolean is
		//currentGame.moveDeveloperOntoBoard();
	}
	
	public void moveDeveloperAroundBoard(int xChange, int yChange){
		//TODO get that current developer and push the location to the stack
		int x = 0;
		x += xChange;
		
		int y = 0;
		y += yChange;
		if(x < 0)
			x += 700;
		else if(x > 700)
			x = 0;
		
		if(y < 0)
			y += 700;
		else if(y > 700)
			y = 0;
		
		//check if the new location is valid and that there are no developers or irrigation tiles on it
		//board.f
		//if ok, push the location to the developer path
		//and reflect the change in the gui
		gamePanel.moveDeveloperOntoBoard(x, y);
		//if it's not ok, dont let the user go there and dont push the location
	}
	
	public void placeComponent(){
		//if currentComponent is developer, place it
		//gamePanel.placeDeveloper((Developer)currentComponent, x, y);
		//TODO figure out something for this: reset the x&y's back
		//x = 0; y = 0;
	}
	
	public void selectIrrigationTile(){
		
	}
	
	public void selectPalaceTile(){
		
	}
	
	public void selectRiceTile(){
		
	}
	
	public void selectVillageTile(){
		
	}
	
	private void initPlayers(){
		//ask the players for their name's and color preferences
		Color[] colors = {new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 0, 0), new Color(255, 255, 0)};
		for(int i = 0; i < numPlayers; ++i){
			String name = JOptionPane.showInputDialog("What is Player "+(i+1)+"'s name?");
			players[i] = new Player(colors[i]);
			gamePanel.getPlayerPanels()[i].setPlayerName(name);
			gamePanel.getPlayerPanels()[i].setPlayerColor(colors[i]);
		}
		gamePanel.updateCurrentPlayerView();
	}
	
	// SERIALIZABLE
	// Inherited from the serializable interface. This method turns 
	// the game object into a string so it can be saved to a file.
	/*
	 * 	private GamePanel gamePanel;
	private Board board;
	private int numPlayers;
	private Player[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalTurn;
	private Stack<Cell> stack;
	 * 
	 * 
	 * 
	 */
	
	//Mauricio, (if you could) please look at the board and players arrays 
	//and see if these are implemented correctly.
	//Also, the last paragraph in my comment
	public String serialize() {
		 /*This creates a string that represents a Game object for saving and loading
		   *board will be turned into a jsonPair using board.serialize()
		   * 
		   * numPlayers is stored
		   * Player[] players is stored
		   * indexOfCurrentPlayed is stored
		   * 
		   * isFinalTurn is stored. toString() for a boolean either returns "True" or "False"
		   * so it needs to be convered to "true" and "false" so that loading is easier.
		   * 
		   * stack, even though it is an attribute of Game, would not be serialized
		   * This stack is used to help the Game method make a path for a developer
		   * When a game is reloaded, a player shoud be forced to create the path over again
		   * This does not truly change the game or how it works!
		   */
		return Json.jsonPair("Game", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("board", board.serialize()),
				Json.jsonPair("numPlayers", Json.jsonValue(numPlayers + "")),
				Json.jsonPair("Players", Json.serializeArray(players)),
				Json.jsonPair("indexOfCurrentPlayer", Json.jsonValue(indexOfCurrentPlayer + "")),
				Json.jsonPair("isFinalTurn", Json.jsonValue(isFinalTurn.toString().toLower()))
				)));
		}
	
	// The polymorphic method loadObject is inherited from the serializable interface.
	// This method returns the Game
	public Game loadObject(String serial) {
		return new Game();
	}
}