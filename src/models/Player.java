package models;

import helpers.Json;
import helpers.JsonObject;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Stack;

public class Player implements Serializable<Player> {

	private int famePoints;
	private String name;
	private Color playerColor;
	private int actionPoints;
	private int actionTokens;
	private boolean ifActionTokenUsed;
	private boolean ifPlacedLandTile;
	private int devOffBoard;
	private LinkedList<Developer> devsOnBoard;
	public Cell[] palacesUsedInTurn;
	private int riceTiles;
	private int villageTiles;
	private int twoSpaceTiles;

	// private final String userName;
	public Player(Color color, String name) {

		this.playerColor = color;
		this.name = name;
		this.famePoints = 0;
		this.actionTokens = 3;
		this.riceTiles = 3;
		this.villageTiles = 3;
		this.twoSpaceTiles = 5;
		// this.userName = userName;
		devsOnBoard = new LinkedList<Developer>();
		devOffBoard = 12;
		palacesUsedInTurn = new Cell[7];
		
	}
	
	
//this is use to start a new turn and it resets the ActionTokenUsed, the placed land tile, and the action points to 6;
	public void startTurn() {
		ifActionTokenUsed = false;
		ifPlacedLandTile = false;
		actionPoints = 6;
	}
	
//use to play developer on the board..we have a linkedlist and we add the developers that we want to place on the board. We also decrease the deOffBoard
// by one
	public void addDevOnBoard(Developer dev){
		
	 	devsOnBoard.push(dev);
	 	
	 	devOffBoard--;
		
	}

//use to remove developerOffBoard...we remove the developer off the linkedlist and increase the devOffBoard by one
	public void removeOffBoard(Developer dev){
		devsOnBoard.remove(dev);
		devOffBoard++;
	}

//use to decrementActionPoints
	public void decrementActionPoints(int actionPoints) {
		this.actionPoints -= actionPoints;
	}
	
	public void incrementActionPoints(int actionPoints) {
		this.actionPoints += actionPoints;
	}

	public void useActionToken() {
		actionPoints++;
		actionTokens--;
		ifActionTokenUsed = true;
	}

	public void addFamePoints(int fame) {
		this.famePoints += fame;
	}

	// --------Getters and Setters--------//

	//Meghan wrote to be able to set the currentCell for loading the game. Is called in Game's loadObject(...)
	public LinkedList<Developer> getDevsOnBoard(){
		return devsOnBoard;
	}
	
	
	public int getFamePoints() {
		return famePoints;
	}

	/**
	 * Updates instance fame points to {@link famePoints}.
	 * <p>
	 * Succeeds and returns true if {@link famePoints} is >= 0.
	 * <p>
	 * Fails and returns false if {@link famePoints} is < 0.
	 * 
	 * @param famePoints
	 *            The value to update fame points to.
	 * @return Whether or not the update succeeded.
	 */
	// public Boolean setFamePoints(int famePoints) {
	// //
	// if (famePoints < 0)
	// return false;
	// else {
	// this.famePoints = famePoints;
	// return true;
	// }
	//
	// }

	/**
	 * Returns the Final color object of the player.
	 * 
	 * @return The color of the player.
	 * @see Color
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * Returns the number of action points the player has.
	 * 
	 * @return The number of action points the player has.
	 */
	public int getActionPoints() {
		return actionPoints;
	}

	public int getActionTokens() {
		// TODO implement { 0..3 } constraint
		return actionTokens;
	}

	// public void setActionTokens(int actionTokens) {
	// this.actionTokens = actionTokens;
	// }

	public boolean isIfActionTokenUsed() {
		return ifActionTokenUsed;
	}

	// public void setIfActionTokenUsed(boolean ifActionTokenUsed) {
	// this.ifActionTokenUsed = ifActionTokenUsed;
	// }

	public boolean isIfPlacedLandTile() {
		return ifPlacedLandTile;
	}

	public void setIfPlacedLandTile(boolean ifPlacedLandTile) {
		this.ifPlacedLandTile = ifPlacedLandTile;
	}


	/*
	public void setDevelopers(Developer[] developers) {
		this.developers = developers;
	}*/

	public Cell[] getPalacesUsedInTurn() {
		return palacesUsedInTurn;
	}

	public void setPalacesUsedInTurn(Cell[] palacesUsedInTurn) {
		this.palacesUsedInTurn = palacesUsedInTurn;
	}

	public int getRiceTiles() {
		return riceTiles;
	}

	public void setRiceTiles(int riceTiles) {
		this.riceTiles = riceTiles;
	}

	public int getVillageTiles() {
		return villageTiles;
	}

	public void setVillageTiles(int villageTiles) {
		this.villageTiles = villageTiles;
	}

	public int getTwoSpaceTiles() {
		return twoSpaceTiles;
	}

	public void setTwoSpaceTiles(int twoSpaceTiles) {
		this.twoSpaceTiles = twoSpaceTiles;
	}
	
	public String getPlayerName(){
		return this.name;
	}
	
	public void setPlayerName(String newName){
		this.name = newName;
	}
	
	public void useTwoSpaceTile(){
		//decrement the number of action points the user has
		this.actionPoints--;
		//decrement the number of two space tiles that the use has
		this.twoSpaceTiles--;
	}
	public void useVillageTile(){
		//decrement the number of action points the user has
		this.actionPoints--;
		//decrement the number of village tiles
		this.villageTiles--;
	}
	public void useRiceTile(){
		//decrement the number of action points the user has
		this.actionPoints--;
		//decrement the number of rice tiles
		this.riceTiles--;
	}

	public String serialize() {
	  //This creates a string that represents a Player object for saving and loading
		return Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("name", Json.jsonValue(name)),
				Json.jsonPair("famePoints", Json.jsonValue(famePoints + "")),
				Json.jsonPair("rgb", Json.jsonValue(playerColor.getRGB() + "")),
				Json.jsonPair("actionPoints", Json.jsonValue(actionPoints + "")),
				Json.jsonPair("actionTokens", Json.jsonValue(actionTokens + "")),
				Json.jsonPair("ifActionTokenUsed", Json.jsonValue(ifActionTokenUsed + "")),
				Json.jsonPair("ifPlacedLandTile", Json.jsonValue(ifPlacedLandTile + "")),
				Json.jsonPair("devOffBoard", Json.jsonValue(devOffBoard + "")),
				Json.jsonPair("devsOnBoard", Json.serializeArray(devsOnBoard)),
				Json.jsonPair ("palacesUsedInTurn", Json.serializeArray(palacesUsedInTurn)),
				Json.jsonPair("riceTiles", Json.jsonValue(riceTiles + "")),
				Json.jsonPair("villageTiles", Json.jsonValue(villageTiles + "")),
				Json.jsonPair("twoSpaceTiles", Json.jsonValue(twoSpaceTiles + ""))
		));
	}

	@Override
	public Player loadObject(JsonObject json) {
		name = json.getString("name");
		famePoints = Integer.parseInt(json.getString("famePoints"));
		
		//figure out color
		playerColor = new Color(Integer.parseInt(json.getString("rgb")));
		
		actionPoints = Integer.parseInt(json.getString("actionPoints"));
		actionTokens = Integer.parseInt(json.getString("actionTokens"));
		ifActionTokenUsed = Boolean.parseBoolean(json.getString("ifActionTokenUsed"));
		ifPlacedLandTile = Boolean.parseBoolean(json.getString("ifPlacedLandTile"));
		devOffBoard = Integer.parseInt(json.getString("devOffBoard"));
		
		JsonObject[] tempPalacesUsedInTurn = json.getJsonObjectArray("palacesUsedInTurn");
		palacesUsedInTurn = new Cell[tempPalacesUsedInTurn.length];
		for(int i = 0; i < tempPalacesUsedInTurn.length; i++){
			palacesUsedInTurn[i] = new Cell(null).loadObject(tempPalacesUsedInTurn[i]);
		}
		
		riceTiles = Integer.parseInt(json.getString("riceTiles"));
		villageTiles = Integer.parseInt(json.getString("villageTiles"));
		twoSpaceTiles = Integer.parseInt(json.getString("twoSpaceTiles"));
		
		//make methods that set up the Player and the Current Cell.....
		//figure out developer
		JsonObject[] tempDevelopers = json.getJsonObjectArray("devsOnBoard");
		for(int i = 0; i < tempDevelopers.length; i++){
			devsOnBoard.add(new Developer(this, null).loadObject(tempDevelopers[i]));
		}
		
		
		return this;
	}
	
	public String toString() { 
		String ret = "";
		for(Cell cell : palacesUsedInTurn)
			ret += cell; 
		return ret + " " + famePoints + " " + name + " " + playerColor + " " + actionPoints + " " + actionTokens + " " + ifActionTokenUsed
				+ " " + ifPlacedLandTile + " " + devOffBoard + " " + devsOnBoard.toString() + " " + riceTiles
				+ " " + villageTiles + " " + twoSpaceTiles;
	}
}
