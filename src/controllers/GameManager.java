package controllers;

import java.awt.Color;

import views.GamePanel;
import models.Game;

public class GameManager {
	private Game currentGame;
	private GamePanel gamePanel;
	private int numPlayers;
	
	// CONSTRUCTORS
	// Default constructor
	public GameManager() {
		
	}
	
	// Main constructor
	public GameManager(Game currentGame) {
	
	}

	// Creates new game using the appropriate number of players.
	// Sets game variable equal to the new game.
	public void createNewGame(int players) {
		numPlayers = players;
		currentGame = new Game();
		gamePanel = new GamePanel(numPlayers, this);
	}
	
	// Loads a game from the specified textfile and assigns it 
	// to the current game.
	public void loadGame(String filename) {
		
	}

	// Saves the current game to a text file in the format specified
	// by serializable.
	public boolean saveGame() {
		
		return true;
	}
	
	// Quits the current game. Asks the user if they want to save first.
	public boolean quitGame() {
		
		return true;
	}
	
	// Deletes the current game. Asks the user if they are sure before proceeding.
	public boolean deleteGame() {
		
		return true;
	}

	public int getNumberOfPlayers() {
		return numPlayers;
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}

}
