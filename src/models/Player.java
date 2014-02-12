package models;

import helpers.Json;
import helpers.JsonObject;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Stack;

public class Player implements Serializable<Player> {

	private int famePoints;
	private final Color playerColor;
	private int actionPoints;
	private int actionTokens;
	private boolean ifActionTokenUsed;
	private boolean ifPlacedLandTile;
	private int devOffBoard;
	private LinkedList<Developer> devsOnBoard;
	private Cell[] palacesUsedInTurn;
	private int riceTiles;
	private int villageTiles;
	private int twoSpaceTiles;

	// private final String userName;

	public Player(Color color) {
		this.playerColor = color;
		this.famePoints = 0;
		this.actionTokens = 3;
		this.riceTiles = 3;
		this.villageTiles = 3;
		this.twoSpaceTiles = 5;
		// this.userName = userName;
		devsOnBoard = new LinkedList<Developer>();
		devOffBoard = 12;
		
	}

	public void startTurn() {
		ifActionTokenUsed = false;
		ifPlacedLandTile = false;
		actionPoints = 6;
	}
	
	public void addDevOnBoard(Developer dev, Cell currentCell){
		
	 	devsOnBoard.push(new Developer(this, currentCell ));
	 	
	 	devOffBoard--;
		
	}
	
	public void removeOffBoard(Developer dev){
		devsOnBoard.remove(dev);
		devOffBoard++;
	}


	public void decrementActionPoints(int actionPoints) {
		this.actionPoints -= actionPoints;
	}
	
	public void incrementActionPoints(int actionPoints) {
		this.actionPoints += actionPoints;
	}

	public void useActionToken() {
		actionPoints++;
		actionTokens--;
	}

	public void addFamePoints(int fame) {
		this.famePoints += fame;
	}

	// --------Getters and Setters--------//

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
	
	public void useTwoSpaceTile(){
		//decrement the number of two space tiles that the use has
		this.twoSpaceTiles--;
	}
	public void useVillageTile(){
		//decrement the number of village tiles
		this.villageTiles--;
	}
	public void useRiceTile(){
		//decrement the number of rice tiles
		this.riceTiles--;
	}

	public String serialize() {
	  /*This creates a string that represents a Player object for saving and loading
	   * 
	   * currentCell will be saved using it's (x,y) coordinatesm in currentCellX and currentCellY .
	   *  This will make the Cell unique
	   *  
	   *  private int famePoints;
	private final Color playerColor;
	private int actionPoints;
	private int actionTokens;
	private boolean ifActionTokenUsed;
	private boolean ifPlacedLandTile;
	private int devOffBoard;
	private LinkedList<Developer> devsOnBoard;
	private Cell[] palacesUsedInTurn;
	private int riceTiles;
	private int villageTiles;
	private int twoSpaceTiles;
	   *
	   */
		return Json.jsonPair("Player", Json.jsonObject(Json.jsonMembers(
				Json.jsonPair("famePoints", Json.jsonValue(famePoints + "")),
				Json.jsonPair("Color", Json.jsonValue(playerColor.toString())),
				Json.jsonPair("actionPoints", Json.jsonValue(actionPoints + "")),
				Json.jsonPair("actionTokens", Json.jsonValue(actionTokens + "")),
				Json.jsonPair("ifActionTokenUsed", Json.jsonValue(ifActionTokenUsed + "")),
				Json.jsonPair("ifPlacedLandTile", Json.jsonValue(ifPlacedLandTile + "")),
				Json.jsonPair("devsOffBoard", Json.jsonValue(devOffBoard + "")),
				Json.jsonPair("devsOnBoard", Json.serializeArray(devsOnBoard.toArray())), //check this later
				Json.jsonPair ("palacesUsedInTurn", Json.serializeArray(palacesUsedInTurn)),
				Json.jsonPair("riceTiles", Json.jsonValue(riceTiles + "")),
				Json.jsonPair("villageTiles", Json.jsonValue(villageTiles + "")),
				Json.jsonPair("twoSpaceTiles", Json.jsonValue(twoSpaceTiles + ""))
		)));
	}

	@Override
	public Player loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return this;
	}
	
	public String toString() { 
		String ret = "";
		for(Cell cell : palacesUsedInTurn)
			ret += cell; 
		return ret + " " + famePoints + " " + playerColor + " " + actionPoints + " " + actionTokens + " " + ifActionTokenUsed
				+ " " + ifPlacedLandTile + " " + devOffBoard + " " + devsOnBoard.toString() + " " + riceTiles
				+ " " + villageTiles + " " + twoSpaceTiles;
	}
}
