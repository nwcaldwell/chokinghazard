package models;

import helpers.Json;
import helpers.JsonObject;

import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;
import java.util.ArrayList;

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
	private Stack<OneSpaceTile> irrigationTiles; 
    private Stack<ThreeSpaceTile> threeSpaceTiles;
    private ArrayList<Stack<OneSpaceTile>> palaceTiles;
	
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
		this.irrigationTiles = new Stack<OneSpaceTile>();
		this.threeSpaceTiles = new Stack<ThreeSpaceTile>();
		this.palaceTiles = new ArrayList<Stack<OneSpaceTile>>();
		this.gamePanel = new GamePanel(numPlayers, this);
		
		//initialize the players and their views
		initPlayers();
	}
	
	// GET/SET METHODS
	public Player getCurrentPlayer() {
		return players[indexOfCurrentPlayer];
	}

	public int getNumberOfPlayers(){
		return players.length;
	}
	
	public Stack<OneSpaceTile> getIrrigationTiles() {
        return irrigationTiles;
    }

    public Stack<ThreeSpaceTile> getThreeSpaceTiles() {
        return threeSpaceTiles;
    }
    
    public ArrayList<Stack<OneSpaceTile>> getPalaceTiles() {
        return palaceTiles;
    }

 
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
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
			if(famePointsToAdd <= 0){
				players[indexOfCurrentPlayer].addFamePoints(famePointsToAdd);
				famePointsToAdd = players[indexOfCurrentPlayer].getFamePoints();
				gamePanel.getPlayerPanels()[indexOfCurrentPlayer].setFamePoints(famePointsToAdd);
			}
			
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
		return threeSpaceTiles.size() == 0;
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
	
	public void placeTwoSpaceTile(){
		
	}
	
	public void placeThreeSpaceTile(){
		
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
		
	public String serialize() {
		 /*This creates a string that represents a Game object for saving and loading
		  *
		  * Did not save the GamePanel, but I will change this if I should
		  *
		  * board will be turned into a jsonPair using board.serialize()
		  * 
		  * numPlayers is stored
		  * Player[] players is stored
		  * indexOfCurrentPlayed is stored
		  * 
		  * isFinalTurn is stored. toString() for a boolean either returns "True" or "False"
		  * so it needs to be convered to "true" and "false" so that loading is easier.
		  * 
		  * stack, even though it is an attribute of Game, (I dont think) would not be serialized
		  * This stack is used to help the Game method make a path for a developer
		  * When a game is reloaded, a player shoud be forced to create the path over again
		  * This does not truly change the game or how it works!
		  */
		return Json.jsonPair("Game", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("board", board.serialize()),
				Json.jsonPair("numPlayers", Json.jsonValue(numPlayers + "")),
				Json.jsonPair("Players", Json.serializeArray(players)),
				Json.jsonPair("indexOfCurrentPlayer", Json.jsonValue(indexOfCurrentPlayer + "")),
				Json.jsonPair("isFinalTurn", Json.jsonValue(isFinalTurn + "")),
				Json.jsonPair("stack", Json.serializeArray(stack)),
				Json.jsonPair("irrigationTiles", Json.serializeArray(irrigationTiles)),
				Json.jsonPair("threeSpaceTiles", Json.serializeArray(threeSpaceTiles)),
				Json.jsonPair("palaceTiles", Json.serializeArray(palaceTiles))
				)));
		}
	
	// The polymorphic method loadObject is inherited from the serializable interface.
	// This method returns the Game
	public Game loadObject(JsonObject json) {
		return new Game();
	}
}