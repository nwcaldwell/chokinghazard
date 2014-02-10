package models;

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
	private Stack<OneSpaceTile> riceTiles;
	private Stack<OneSpaceTile> villageTiles;
	private Stack<TwoSpaceTile> twoSpaceTiles;

	// private final String userName;

	public Player(Color color) {
		this.playerColor = color;
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

	public Stack<OneSpaceTile> getRiceTiles() {
		return riceTiles;
	}

	public void setRiceTiles(Stack<OneSpaceTile> riceTiles) {
		this.riceTiles = riceTiles;
	}

	public Stack<OneSpaceTile> getVillageTiles() {
		return villageTiles;
	}

	public void setVillageTiles(Stack<OneSpaceTile> villageTiles) {
		this.villageTiles = villageTiles;
	}

	public Stack<TwoSpaceTile> getTwoSpaceTiles() {
		return twoSpaceTiles;
	}

	public void setTwoSpaceTiles(Stack<TwoSpaceTile> twoSpaceTiles) {
		this.twoSpaceTiles = twoSpaceTiles;
	}

	public String serialize() {
	  /*This creates a string that represents a Player object for saving and loading
	   * 
	   * currentCell will be saved using it's (x,y) coordinatesm in currentCellX and currentCellY .
	   *  This will make the Cell unique
	   *
	   */
		return Json.jsonPair("Player", Json.jsonObject(Json.jsonMember(
				Json.jsonPair("famePoints", Json.jsonValue(famePoints + "")),
				Json.jsonPair("Color", Json.jsonValue(playerColor.toString())),
				Json.jsonPair("actionPoints", Json.jsonValue(actionPoints + "")),
				Json.jsonPair("actionTokens", Json.jsonValue(actionTokens + "")),
				Json.jsonPair("ifActionTokenUsed", Json.jsonValue(ifActionTokenUsed + ""),
				Json.jsonPair("ifPlacedLandTile", Json.jsonValue(ifPlacedLandTile + ""),
				Json.jsonPair("developers", Json.serializeArray(developers)),
				Json.jsonPair ("palacesUsedInTurn", Json.serializeArray(palacesUsedInTurn)),
				Json.jsonPair("riceTiles", Json.serializeArray(riceTiles)),
				Json.jsonPair("villageTiles", Json.serializeArray(villageTiles)),
				Json.jsonPair("twoSpaceTiles", Json.serializeArray(twoSpaceTiles))
		)));
	}

	public Player loadObject(String serial) {
		// TODO Auto-generated method stub
		return null;
	}
}
