package models;

import java.awt.Color;
import java.util.Stack;

public class Player implements Serializable<Player> {

	private int famePoints;
	private final Color playerColor;
	private int actionPoints;
	private int actionTokens;
	private boolean ifActionTokenUsed;
	private boolean ifPlacedLandTile;
	private Developer[] developers;
	private Cell[] palacesUsedInTurn;
	private Stack<OneSpaceTile> riceTiles;
	private Stack<OneSpaceTile> villageTiles;
	private Stack<TwoSpaceTile> twoSpaceTiles;

	public Player(Color color) {
		this.playerColor = color;
		this.famePoints = 0;
		this.actionTokens = 3;
		this.developers = new Developer[12];
		for(int i = 0; i < developers.length; ++i){
			developers[i] = new Developer(this);
		}
		
		startTurn();
	}

	public void startTurn() {
		ifActionTokenUsed = false;
		ifPlacedLandTile = false;
		actionPoints = 6;
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
//	public boolean setFamePoints(int famePoints) {
//		//
//		if (famePoints < 0)
//			return false;
//		else {
//			this.famePoints = famePoints;
//			return true;
//		}
//
//	}

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

//	public void setActionPoints(int actionPoints) {
//		this.actionPoints = actionPoints;
//	}

	public int getActionTokens() {
		// TODO implement { 0..3 } constraint
		return actionTokens;
	}

//	public void setActionTokens(int actionTokens) {
//		this.actionTokens = actionTokens;
//	}

	public boolean isIfActionTokenUsed() {
		return ifActionTokenUsed;
	}

//	public void setIfActionTokenUsed(boolean ifActionTokenUsed) {
//		this.ifActionTokenUsed = ifActionTokenUsed;
//	}

	public boolean isIfPlacedLandTile() {
		return ifPlacedLandTile;
	}

	public void setIfPlacedLandTile(boolean ifPlacedLandTile) {
		this.ifPlacedLandTile = ifPlacedLandTile;
	}

	public Developer[] getDevelopers() {
		return developers;
	}

	public void setDevelopers(Developer[] developers) {
		this.developers = developers;
	}

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
/*
 * 	private int famePoints;
	private final Color playerColor;
	private int actionPoints;
	private int actionTokens;
	private boolean ifActionTokenUsed;
	private boolean ifPlacedLandTile;
	private Developer[] developers;
	private Cell[] palacesUsedInTurn;
	private Stack<OneSpaceTile> riceTiles;
	private Stack<OneSpaceTile> villageTiles;
	private Stack<TwoSpaceTile> twoSpaceTiles;
 */
	public String serialize() {
		/*/*This creates a string that represents a Player object for saving and loading
	   *
	   * boolean attributes, such as ifActionTokenUsed isPlacedOnBoard is stored. toString() for a boolean either returns "True" or "False"
	   * so it needs to be convered to "true" and "false" so that loading is easier.
	   * 
	   * currentCell will be saved using it's (x,y) coordinatesm in currentCellX and currentCellY .
	   *  This will make the Cell unique
	   * 
	   * The owner should (I hope) be unique by the player's color, so that will be saved this way..
	   * We may not even need to save this, since the Developer should be made with the corresponding
	   * Player who owns it... anyways :P
	   */
		return Json.jsonPair("Player", Json.jsonObject(Json.jsonMember(
				Json.jsonPair("famePoints", Json.jsonValue(famePoints + "")),
				Json.jsonPair("Color", Json.jsonValue(playerColor.toString())),
				Json.jsonPair("actionPoints", Json.jsonValue(actionPoints + "")),
				Json.jsonPair("actionTokens", Json.jsonValue(actionTokens + "")),
				Json.jsonPair("ifActionTokenUsed", Json.jsonValue(ifActionTokenUsed.toString().toLower())),
				Json.jsonPair("ifPlacedLandTile", Json.jsonValue(ifPlacedLandTile.toString().toLower())),
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

