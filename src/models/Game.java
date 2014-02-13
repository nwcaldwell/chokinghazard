package models;

import helpers.Json;
import helpers.JsonObject;
import models.Serializable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JOptionPane;

import views.GamePanel;

public class Game implements Serializable <Game>  {
	

	// VARIABLES
	private GamePanel gamePanel;
	private Board board;
	private int numPlayers;
	private Player[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalTurn;
	private Stack<Cell> stack;
	private int irrigationTiles; 
    private int threeSpaceTiles;
    private int[] palaceTiles;
    int x = 50;
	int y = 50;
	private Object currentComponent;
	private int tabbing = -1;
	
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
		this.irrigationTiles = 16;
		this.threeSpaceTiles = 56;
		this.palaceTiles = new int[]{6, 7, 8, 9, 10};
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
	
	public int getIrrigationTiles() {
        return irrigationTiles;
    }
	
	public void setIrrigationTiles(int nums){
		this.irrigationTiles = nums;
	}

    public int getThreeSpaceTiles() {
        return threeSpaceTiles;
    }
    
    public void setThreeSpaceTiles(int nums) {
        this.threeSpaceTiles = nums;
    }
    
    public int[] getPalaceTiles() {
        return palaceTiles;
    }
    public void setPalaceTiles(int[] nums){
    	this.palaceTiles = nums;
    }
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	
	public Object getCurrentComponent() {
		return currentComponent;
	}
	
	public void setCurrentComponent(Object currentComponent) {
		this.currentComponent = currentComponent;
	}
	// ACTION TOKEN
	// Increases number of action points available for that players 
	// turn by one. Changes the ifActionTokenUsed boolean to true
	public boolean useActionToken() {
		boolean alreadyUsed = players[indexOfCurrentPlayer].isIfActionTokenUsed();
		if (!alreadyUsed) {
			// moving this inside the conditional for efficiency's sake...might not need everytime -brett
			int numActionTokensAvailable = players[indexOfCurrentPlayer].getActionTokens();
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
			if(famePointsToAdd >= 0){  		//should be >=0 to detect positive values, yell at me if I'm wrong for fixing this -brett
				players[indexOfCurrentPlayer].addFamePoints(famePointsToAdd);
				famePointsToAdd = players[indexOfCurrentPlayer].getFamePoints();
				gamePanel.getPlayerPanels()[indexOfCurrentPlayer].setFamePoints(famePointsToAdd);
			}
			
			//advance to the next player
			indexOfCurrentPlayer++;
			indexOfCurrentPlayer %= numPlayers;
			gamePanel.nextTurn();
			players[indexOfCurrentPlayer].startTurn();
			resetInteraction();
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
		return threeSpaceTiles == 0;
	}
	
	public void tabThroughDevelopers(){
		if(tabbing < 0){
			tabbing = 0;
		}
		//tab through the user's developers on board linked list
		LinkedList<Developer> devsOnBoard = players[indexOfCurrentPlayer].getDevsOnBoard();
		
		//don't want a tab index that is greater than the size of the thing. 
		tabbing %= devsOnBoard.size();
		
		//current component now is equal to the tabbed-to developer
		//because if the player presses enter, we want the method to know what's going on
		Developer highlightedDeveloper = players[indexOfCurrentPlayer].getDeveloperOnBoardAtIndex(tabbing);
		x = highlightedDeveloper.getCurrentCellX() * 50;
		x = highlightedDeveloper.getCurrentCellY() * 50;
		gamePanel.highlightDeveloper(highlightedDeveloper, x, y);
		
	}
	
	public void rotateCurrentComponent(){
		String type = currentComponent.getClass().toString();
		
		if(type.equals("class models.TwoSpaceTile")){
			gamePanel.rotateTile((Tile)currentComponent, x, y);
		}
		else if(type.equals("class models.ThreeSpaceTile")){
			gamePanel.rotateTile((Tile)currentComponent, x, y);
		}
		
	}
	
	public void addDeveloperToBoard(){
		
		if(players[indexOfCurrentPlayer].getDevsOffBoard() > 0){
			//create a new Developer
			currentComponent = new Developer(players[indexOfCurrentPlayer], board.getCellAtPixel(x, y));
		
			//place the developer in the GUI
			gamePanel.moveDeveloperOntoBoard(x, y);
		}
		else{
			//player doesn't have any developers to place
		}
		
	}
	
	public void moveComponentAroundBoard(int xChange, int yChange){
		if(currentComponent != null){
			x += xChange;
			y += yChange;
			if(x < 0)
				x = 0;
			else if(x > 650)
				x = 650;
			
			if(y < 0)
				y = 0;
			else if(y > 650)
				y = 650;
			
			
			//reflects the changes in the GUI
			String type = currentComponent.getClass().toString();
			if(type.equals("class models.TwoSpaceTile")){
				gamePanel.moveTile((Tile)currentComponent, x, y);
			}
			else if(type.equals("class models.ThreeSpaceTile")){
				gamePanel.moveTile((Tile)currentComponent, x, y);
			}
			else if(type.equals("class models.OneSpaceTile")){
				gamePanel.moveTile((Tile)currentComponent, x, y);
			}
			else if(type.equals("class models.Developer")){
				//TODO this needs to change, or at least the gamepanel thing needs to change
				gamePanel.moveDeveloperOntoBoard(x, y);
			}
		}
	}
	
	public void placeComponent(){
		Cell[][] currentCell = {
			{board.getCellAtPixel(x, y), board.getCellAtPixel(x, y+1)},
			{board.getCellAtPixel(x+1, y),board.getCellAtPixel(x+1, y+1)}
		};
		String type = currentComponent.toString();
		System.out.println(type);
		
		if(type.equals("DEVELOPER")){
			//check to see if the developer is being selected rather than being placed
			if(tabbing < 0){
				//developer is being placed
				//set it as on the board if not already in the player model
				if(!((Developer)currentComponent).isPlacedOnBoard()){
					players[indexOfCurrentPlayer].addDevOnBoard((Developer)currentComponent);
					
				}
				
				((Developer)currentComponent).setCurrentCell(board.getCellAtPixel(x, y));
				gamePanel.placeDeveloper(indexOfCurrentPlayer, x, y);
			}
			else{
				//developer is being selected to be moved around
				tabbing = -1;
				currentComponent = players[indexOfCurrentPlayer].getDeveloperOnBoardAtIndex(tabbing);
			}
				
		}
		else if(board.placeTile(currentCell, (Tile)currentComponent)){
			switch(type){
			case"THREE SPACE TILE":
				//decrement it from the global stash
				--threeSpaceTiles;
				gamePanel.setThreePieceTiles(threeSpaceTiles);
				players[indexOfCurrentPlayer].setIfPlacedLandTile(true);
				break;
			case "TWO SPACE TILE":
				//decrement it from the user's stash
				players[indexOfCurrentPlayer].useTwoSpaceTile();
				players[indexOfCurrentPlayer].setIfPlacedLandTile(true);
				break;
			case "IRRIGATION":
				//decrement it from the global stash
				--irrigationTiles;
				gamePanel.setIrrigationTiles(irrigationTiles);
				break;
			case "VILLAGE":
				//decrement it from the user's stash
				players[indexOfCurrentPlayer].useVillageTile();
				players[indexOfCurrentPlayer].setIfPlacedLandTile(true);
				break;
			case "RICE":
				//decrement it from the user's stash
				players[indexOfCurrentPlayer].useRiceTile();
				players[indexOfCurrentPlayer].setIfPlacedLandTile(true);
				break;
			case "PALACE":
				int value = ((PalaceSpace)((Tile)currentComponent).getSpaces()[0][0]).getValue();
				palaceTiles[value/2-1]--; 
				if(value == 2) gamePanel.setTwoPalaceTiles(palaceTiles[value/2-1]);
				else if(value == 4) gamePanel.setFourPalaceTiles(palaceTiles[value/2-1]);
				else if(value == 6) gamePanel.setSixPalaceTiles(palaceTiles[value/2-1]);
				else if(value == 8) gamePanel.setEightPalaceTiles(palaceTiles[value/2-1]);
				else if(value == 10) gamePanel.setTenPalaceTiles(palaceTiles[value/2-1]);
				
				//need to somehow do checks for which palace tile to place
				break;
			}	
			gamePanel.placeTile((Tile)currentComponent, x, y);
		}
		resetInteraction();
	}
	
	public void selectTwoSpaceTile(){
		if(players[indexOfCurrentPlayer].getTwoSpaceTiles() == 0){
			showNotEnoughTiles();
		}
		else{
			currentComponent = new TwoSpaceTile(new Space[2][2]);
			gamePanel.moveTile((Tile)currentComponent, x, y);
			
		}
	}
	
	public void selectThreeSpaceTile(){
		if(this.threeSpaceTiles == 0){
			showNotEnoughTiles();
		}
		else{
			currentComponent = new ThreeSpaceTile(new Space[2][2]);
			gamePanel.moveTile((Tile)currentComponent, x, y);
		}
	}
	
	public void selectIrrigationTile(){
		//if(players[indexOfCurrentPlayer].getActionPoints() == 0){
		//	showNotEnoughActionPoints();
		//}
		 if(this.irrigationTiles == 0){
			showNotEnoughTiles();
		}
		else{
			currentComponent = new OneSpaceTile(new IrrigationSpace(), new Space[2][2]);
			gamePanel.moveTile((Tile)currentComponent, x, y);
		}
			
	}
	
	public void selectPalaceTile(){
		String palace = null;
		boolean isInt = false;
		boolean validRange = false;
		int value = 0;
		
		while (!isInt || !validRange)
		{
			palace = JOptionPane.showInputDialog(null,"What Palace value would you like to place?\n 2, 4, 6, 8, or 10");
			if (palace == null)
				break;
			try 
			{
				value = Integer.parseInt(palace);
				isInt = true;
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null,"Not a valid entry!");
			}
			
			if (isInt && !palace.equals(""))
			{
				if (value > 0 && value <  11 && value%2==0) //check if 2,4,6,8,or 10
					validRange = true;
				else
				{
					JOptionPane.showMessageDialog(null,"Not a valid entry!");
					isInt = false;
				}
					
			}
		}
		if (isInt && validRange)	//since we're breaking out of the loop above on escape, have to check valid input
			placePalace(palace);
	}
	
	public void selectRiceTile(){
		if(players[indexOfCurrentPlayer].getRiceTiles() == 0){
			showNotEnoughTiles();
		}
		else{
			currentComponent = new OneSpaceTile(new RiceSpace(), new Space[2][2]);
			gamePanel.moveTile((Tile)currentComponent, x, y);
		}
			
	}
	
	public void selectVillageTile(){
		if(players[indexOfCurrentPlayer].getVillageTiles() == 0){
			showNotEnoughTiles();
		}
		else{
			currentComponent = new OneSpaceTile(new VillageSpace(), new Space[2][2]);
			gamePanel.moveTile((Tile)currentComponent, x, y);
		}
			
	}
	
	public void selectPalaceToUpgrade(){
		//ask the user what value of tile they would like to place
		String upgrade = JOptionPane.showInputDialog("What value of Palace would you like to upgrade to?\n 2, 4, 6, 8, or 10");
		placePalace(upgrade);
	}
	
	private void placePalace(String input){
		if(input != null){
			int value = Integer.parseInt(input);
			if(value < 11 && value % 2 == 0){
				if(this.palaceTiles[(value/2-1)] > 0){
					currentComponent = new OneSpaceTile(new PalaceSpace(value), new Space[2][2]);
					System.out.println(((Tile)currentComponent).getImageSource());
					gamePanel.moveTile((Tile)currentComponent, x, y);
				}
				else
					JOptionPane.showMessageDialog(null, "Not enough "+value+" Palace tiles!");
			}
			else
				JOptionPane.showMessageDialog(null,  "Please enter a number, 2, 4, 6, 8, or 10.");
		}
	}
	
	public void cancelAction(){
		resetInteraction();
		gamePanel.cancelActions();
	}
	
	private void resetInteraction(){
		currentComponent = null;
		x = 50;
		y = 50;
	}
	
	private void showNotEnoughTiles(){
		JOptionPane.showMessageDialog(null, "No more tiles of this type, try another");
	}
	private void showNotEnoughActionPoints(){
		JOptionPane.showMessageDialog(null, "Not enough Action Points!");
	}
	
	public void initPlayers(){
		//ask the players for their name's and color preferences
		Color[] colors = {new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 0, 0), new Color(255, 255, 0)};
		for(int i = 0; i < numPlayers; ++i){
			String name = "";
			boolean okName = false; //to check if valid name was input
			while (!okName)
			{
				name = JOptionPane.showInputDialog("What is player "+(i+1)+"'s name?");
				if (name.equals(""))	//Not valid!
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
				}
				else //valid name
				{
					okName = true; //Acceptable input, proceed to next step
				}
			}
			players[i] = new Player(colors[i], name);
		}
		setPlayerNamesInView();
	}
	
	public void setPlayerNamesInView(){
		gamePanel.setPlayerPanels(players);
		gamePanel.updateCurrentPlayerView();
	}
		
	public String serialize() {
		 /*This creates a string that represents a Game object for saving and loading
		  *
		  * Did not save the GamePanel, int x, int y, currentComponent, or tabCount
		  * 
		  * May not need to save stack
		  */
		return Json.jsonPair("Game", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("board", board.serialize()),
				Json.jsonPair("numPlayers", Json.jsonValue(numPlayers + "")),
				Json.jsonPair("players", Json.serializeArray(players)),
				Json.jsonPair("indexOfCurrentPlayer", Json.jsonValue(indexOfCurrentPlayer + "")),
				Json.jsonPair("isFinalTurn", Json.jsonValue(isFinalTurn + "")),
				Json.jsonPair("irrigationTiles", Json.jsonValue(irrigationTiles + "")),
				Json.jsonPair("threeSpaceTiles", Json.jsonValue(threeSpaceTiles + "")),
				Json.jsonPair("palaceTiles", Json.serializeArray(palaceTiles))
				)));
		}
	
	// The polymorphic method loadObject is inherited from the serializable interface.
	// This method returns the Game

	public Game loadObject(JsonObject json) {
		if(json.getObject("board") == null)
			json = json.getJsonObject("Game");
		board = (new Board()).loadObject(json.getJsonObject("board"));
		numPlayers = Integer.parseInt(json.getString("numPlayers"));
		
		//Players I have to go through the JsonObject array and call loadJsonObject on each one
		players = new Player[numPlayers];
		Object[] tempPlayers = (Object[]) json.getObject("players");
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(null, null).loadObject((JsonObject) tempPlayers[i]); //will have to update with change in Player constructor
		}
		
		indexOfCurrentPlayer = Integer.parseInt(json.getString("indexOfCurrentPlayer"));
		isFinalTurn = Boolean.parseBoolean(json.getString("isFinalTurn"));
		irrigationTiles = Integer.parseInt(json.getString("irrigationTiles"));
		threeSpaceTiles = Integer.parseInt(json.getString("threeSpaceTiles"));
		
		//Go through each in the String array and parse them and add them to the palaceTiles array
		Object[] tempPalaceTiles = new Object[5];
		palaceTiles = new int[5];
		tempPalaceTiles = (Object[]) json.getObject("palaceTiles");
		for(int i = 0; i < 5; i++){
			palaceTiles[i] = Integer.parseInt((String) tempPalaceTiles[i]);
		}
		
		for(int i = 0; i < players.length; i++){
			LinkedList<Developer> devs = players[i].getDevsOnBoard();
			for(int j = 0; j < devs.size(); j++){
				if(devs.get(j) != null) {
					devs.get(j).setCurrentCell(board.getCellAtLocation(devs.get(j).getCurrentCellX(),devs.get(j).getCurrentCellY()));
					board.loadCellsDevelopers(devs.get(j));
				}
			}
		}
		
		return this;
	}	
	


	
	public String toString() {
		String ret = ""; 
		for(Player player : players) 
			ret += player.toString() + " ";
		return ret + " " + board == null ? "null" : board.toString() + " " + numPlayers + " " + indexOfCurrentPlayer + " " + isFinalTurn 
				+ " " + stack == null ? "null" : stack.toString() + " " + irrigationTiles + " " + threeSpaceTiles + " " + palaceTiles == null ? "null" : Arrays.toString(palaceTiles)
				+ " " + x + " " + y + " " + (currentComponent == null ? "NULL" : currentComponent.toString()) + " " + tabbing;
	}
}