package controllers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.Game;
import models.Player;
import views.GamePanel;

public class GameManager{
	private Game currentGame;
	private GamePanel gamePanel;
	private int numPlayers;
	private Player currentPlayer;
	
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
	
	
	public void keyTyped(KeyEvent e) {
		//System.out.println("In Game Manager, Key typed: "+e.getKeyChar());
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("In Game Manager, Key pressed: "+e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("In Game Manager, Key released: "+e.getKeyCode());
		//set up the key input events
		//1 selects one piece tile
		switch(e.getKeyCode()){
			case 9:
				//tab through developers
				break;
			case 10:
				//pressed enter
				break;
			case 32:
				//pressed the space bar
				break;
			case 37:
				//left arrow
				break;
			case 38:
				//up arrow
				break;
			case 39:
				//right arrow
				break;
			case 40:
				//down arrow
				break;
			case 68:
				//pressed D, add new developer
				break;
			case 73:
				//pressed I, add new Irrigation tile
				break;
			case 80:
				//pressed P, new palace tile, need to ask for value of Tile
				break;
			case 82:
				//pressed R, place rice tile
				break;
			case 84:
				//pressed T, use action token
				break;
			case 86:
				//pressed V, place Village
				break;
		}
	}

}
