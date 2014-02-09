package controllers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;

import models.Developer;
import models.Game;
import views.BoardPanel;
import views.GamePanel;

public class GameManager{
	private Game currentGame;
	private GamePanel gamePanel;
	private int x = 0;
	private int y = 0;
	private Object currentComponent;
	private int currentPlayerDeveloperIndex;
	
	// CONSTRUCTORS
	// Default constructor
	public GameManager() {
		
	}
	
	// Main constructor
	public GameManager(Game currentGame) {
	
	}

	// Creates new game using the appropriate number of players.
	// Sets game variable equal to the new game.
	public void createNewGame(int numPlayers) {
		currentGame = new Game(numPlayers);
		gamePanel = new GamePanel(numPlayers, this);
		
		askForPlayerNames();
	}
	
	// Loads a game from the specified textfile and assigns it 
	// to the current game.
	public void loadGame(File fileName) {
		//parse the file and create a currentGame from it
		//TODO
	}

	// Saves the current game to a text file in the format specified
	// by serializable.
	public boolean saveGame() {
		//TODO
		return true;
	}
	
	// Quits the current game. Asks the user if they want to save first.
	public boolean quitGame() {
		//returning true quits the game
		if(currentGame == null){
			return true;
		}
		
		int selectedSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to save this game before quitting?", "Quit Game", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(selectedSaveGame == JOptionPane.YES_OPTION){
			saveGame();
			return true;
		}
		else if(selectedSaveGame == JOptionPane.NO_OPTION){
			//makes the game frame quit
			return true;
		}
		
		return false;
	}
	
	// Deletes the current game. Asks the user if they are sure before proceeding.
	public boolean deleteGame() {
		if(currentGame == null)
			return false;
		
		int selectedSaveGame = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this game?", "Delete Game", JOptionPane.YES_NO_CANCEL_OPTION);
		if(selectedSaveGame == JOptionPane.YES_OPTION){
			currentGame = null;
			//delete the file
			//TODO
			return true;
		}
		else if(selectedSaveGame == JOptionPane.NO_OPTION){
			return true;
		}
		return false;
	}
	
	public int getNumberOfPlayers() {
		return currentGame.getNumberOfPlayers();
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	
	private void askForPlayerNames(){
		//ask the players for their name's and color preferences
		Color[] colors = {new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 0, 0), new Color(255, 255, 0)};
		for(int i = 0; i < getNumberOfPlayers(); ++i){
			String name = JOptionPane.showInputDialog("What is player "+(i+1)+"'s name?");
			gamePanel.getPlayerPanels()[i].setPlayerName(name);
			gamePanel.getPlayerPanels()[i].setPlayerColor(colors[i]);
		}
		gamePanel.updateCurrentPlayerView();
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		//set up the key input events
		//1 selects one piece tile
		switch(e.getKeyCode()){
			case 27:
				//cancel action
				System.out.println("cancel action");
				break;
			case 88:
				//end turn
				System.out.println("end turn");
				nextTurn();
				break;
			case 9:
				//tab through developers
				System.out.println("next developer");
				tabThroughDevelopers();
				break;
			case 10:
				//pressed enter
				System.out.println("pressed enter");
				placeComponent();
				break;
			case 32:
				//pressed the space bar
				System.out.println("rotate");
				rotateCurrentComponent();
				break;
			
			case (98):
				//2 button numerical key - move developer down
				
				break;
			case 100:
				//4 button numerical key - move developer left
				break;
			case 102:
				//6 button numerical key - move developer right
				break;
			case 104:
				//8 button numerical key - move developer up
				break;
				
			case 68:
				//pressed D, add new developer onto board
				System.out.println("add new developer");
				addDeveloperToBoard();
				break;
			case 73:
				//pressed I, add new Irrigation tile
				System.out.println("place Irrigation Tile");
				selectIrrigationTile();
				break;
			case 80:
				//pressed P, new palace tile, need to ask for value of Tile
				System.out.println("Place Palace Tile");
				selectPalaceTile();
				break;
			case 82:
				//pressed R, place rice tile
				System.out.println("Place Palace Tile");
				selectRiceTile();
				break;
			case 84:
				//pressed T, use action token
				System.out.println("Use Extra Action Token");
				useActionToken();
				break;
			case 86:
				//pressed V, place Village
				System.out.println("Place Village Tile");
				selectVillageTile();
				break;
		/*
		 * using these arrow keys for movement of a developer for testing purposes*/
			case 37:
				//pressed left arrow, move the viewport left
				moveDeveloperAroundBoard(-50, 0);
				break;
			case 38:
				//pressed up arrow, move the viewport up
				moveDeveloperAroundBoard(0, -50);
				break;
			case 39:
				//pressed the right arrow, move the viewport right
				moveDeveloperAroundBoard(50, 0);
				break;
			case 40:
				//pressed the down arrow, move the viewport down
				moveDeveloperAroundBoard(0, 50);
				break;
		}
	}

	private void nextTurn(){
		currentPlayerDeveloperIndex = 0;
		gamePanel.nextTurn();
		currentGame.nextTurn();
	}
	
	private void tabThroughDevelopers(){
		System.out.println("tabbing");
		Developer[] dev = currentGame.getCurrentPlayer().getDevelopers();
		for(int i = currentPlayerDeveloperIndex; i < dev.length; ++i){
			if(dev[i].isPlacedOnBoard()){
				//light this developer up
				gamePanel.highlightDeveloper(dev[i]);
				break;
			}
			else
				continue;
		}
	}
	
	private void rotateCurrentComponent(){
		//check if currentComponent is a tile
		//if so, call the rotate method
		//else do nothing
	}
	
	private void addDeveloperToBoard(){
		gamePanel.moveDeveloperOntoBoard(0, 0);
		//TODO change this to a real developer
		currentComponent = new Developer(currentGame.getCurrentPlayer());
		//get the index of the first developer not on the board in the players.developer[]
		//get that developer's cell position, and set to isOnBoard or whatever the boolean is
		//currentGame.moveDeveloperOntoBoard();
	}
	
	private void moveDeveloperAroundBoard(int xChange, int yChange){
		//get that current developer and push the location to the stack
		x += xChange;
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
		//if ok, push the location to the developer path
		//and reflect the change in the gui
		gamePanel.moveDeveloperOntoBoard(x, y);
		//if it's not ok, dont let the user go there and dont push the location
	}
	
	private void placeComponent(){
		//if currentComponent is developer, place it
		gamePanel.placeDeveloper((Developer)currentComponent, x, y);
		//TODO figure out something for this: reset the x&y's back
		x = 0; y = 0;
	}
	
	private void selectIrrigationTile(){
		
	}
	
	private void selectPalaceTile(){
		
	}
	
	private void selectRiceTile(){
		
	}
	
	private void selectVillageTile(){
		
	}
	
	private void useActionToken(){
		//check if the game can actually use an action token
		boolean useToken = currentGame.useActionToken();
		if(useToken){
			gamePanel.useActionToken(currentGame.getCurrentPlayer().getActionTokens());
		}
	}

}
