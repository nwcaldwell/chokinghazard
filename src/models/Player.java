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
    }

    public void startTurn() {

    }

    public void useActionTokens() {

    }
    
    public void addFamePoints(int fame) {

    }

    public int getFamePoints() {
        return famePoints;
    }

    public void setFamePoints(int famePoints) {
	// TODO implement { >= 0 } constraint
        this.famePoints = famePoints;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getActionTokens() {
	// TODO implement { 0..3 } constraint
        return actionTokens;
    }

    public void setActionTokens(int actionTokens) {
        this.actionTokens = actionTokens;
    }

    public boolean isIfActionTokenUsed() {
        return ifActionTokenUsed;
    }

    public void setIfActionTokenUsed(boolean ifActionTokenUsed) {
        this.ifActionTokenUsed = ifActionTokenUsed;
    }

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

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public Player loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
