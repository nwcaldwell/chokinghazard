package controllers;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;

import models.Game;
import views.GamePanel;

public class GameManager{
	private Game currentGame;
	private GamePanel gamePanel;
	
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
		currentGame = new Game();
		gamePanel = new GamePanel(numPlayers, this);
	}
	
	// Loads a game from the specified textfile and assigns it 
	// to the current game.
	public void loadGame(File fileName) {
		if(fileName == null){
			//the user actually didn't select a file, so create a new game
		}
		else{
			//parse the file and create a currentGame from it
		}
	}

	// Saves the current game to a text file in the format specified
	// by serializable.
	public boolean saveGame() {
		
		return true;
	}
	
	// Quits the current game. Asks the user if they want to save first.
	public boolean quitGame() {
		if(currentGame == null){
			return false;
		}
		
		int selectedSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to save this game before quitting?", "Quit Game", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(selectedSaveGame == JOptionPane.YES_OPTION){
			saveGame();
			return true;
		}
		else if(selectedSaveGame == JOptionPane.NO_OPTION){
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
			//how do you even do that?
			return true;
		}
		else if(selectedSaveGame == JOptionPane.NO_OPTION){
			return true;
		}
		return false;
	}

	public int getNumberOfPlayers() {
		//return currentGame.getNumberOfPlayers;
		return 4;
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
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
				//complete turn
				System.out.println("end turn");
				currentGame.getCurrentPlayer.endTurn();
				break;
			case 9:
				//tab through developers
				System.out.println("next developer");
				break;
			case 10:
				//pressed enter
				System.out.println("pressed enter");
				break;
			case 32:
				//pressed the space bar
				System.out.println("rotate");
				break;
			case (98):
				//2 button numerical key - move developer down
				//currentGame
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
				
			//------FOR TESING PURPOSES ONLY, SINCE NO ONE HAS A NUMERICAL KEYPAD
			case 90:
				//move left
				System.out.println("Move left");
				break;
			case 83:
				//move up
				System.out.println("Move up");
				break;
			case 67:
				//move right
				System.out.println("Move right");
				break;
			case 88:
				//move down
				System.out.println("Move down");
				break;
			//---------------------------------
				
			case 68:
				//pressed D, add new developer onto board
				System.out.println("add new developer");
				currentGame.getCurrentPlayer().moveDeveloperOntoBoard();
				break;
			case 73:
				//pressed I, add new Irrigation tile
				System.out.println("place Irrigation Tile");
				currentGame.getCurrentPlayer().placeIrrigationTile();
				break;
			case 80:
				//pressed P, new palace tile, need to ask for value of Tile
				System.out.println("Place Palace Tile");
				break;
			case 82:
				//pressed R, place rice tile
				System.out.println("Place Palace Tile");
				break;
			case 84:
				//pressed T, use action token
				System.out.println("Use Extra Action Token");
				break;
			case 86:
				//pressed V, place Village
				System.out.println("Place Village Tile");
				break;
			case 37:
				//pressed left arrow, move the viewport left
				break;
			case 38:
				//pressed up arrow, move the viewport up
				break;
			case 39:
				//pressed the right arrow, move the viewport right
				break;
			case 40:
				//pressed the down arrow, move the viewport down
				break;
		}
	}

}
