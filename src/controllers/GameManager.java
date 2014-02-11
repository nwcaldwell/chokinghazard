package controllers;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import models.Game;
import sun.misc.IOUtils;
import views.GamePanel;


public class GameManager{
	
	//Attributes
	private Game currentGame;
	
	
	// CONSTRUCTORS
	// Default constructor
	public GameManager() {
		
	}
	
	// Main constructor
	public GameManager(Game currentGame) {
		this.currentGame = currentGame;
	}
	
	// Creates new game using the appropriate number of players.
	// Sets game variable equal to the new game.
	public void createNewGame(int numPlayers) {
		currentGame = new Game(numPlayers);
	}
	
	// This gets the name of the text file for saving/loading the game
	public boolean getFileName(String name, String message){
		boolean okFileName = false;
		
		while(!okFileName ){
			//File name is invalid if it is blank, or has any of the following
			//  \ / ? % * : | " < > . (space) (empty)
			try{
				//rewrite dialog later
				name = JOptionPane.showInputDialog(message);
				String s = ".*[ /\\\\?%*:|\"<>.].*";
				if (name.matches(s) || name.equals(""))	//means that name doesn't contain any characters in s
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
				}
				else {
					okFileName = true;
				}
			}catch(NullPointerException e){

				okFileName = true;
				return false;
			}
		}
		return true;
	}
	
	public boolean loadGame(File loadFile) {
			StringBuilder alpha = new StringBuilder();
			try{
				Scanner input = new Scanner(loadFile);
				while(input.hasNextLine()){
					alpha.append(input.nextLine());
				}
				input.close();
			}catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "File " + loadFile.getName() + " could not be loaded.");
				return false;
		}
		return true;
	}

	// Saves the current game to a text file in the format specified by serializable.
	//Don't mess with this unless Meghan or Mauricio said so. THANKS
	public boolean saveGame() {
		if(currentGame == null){
			JOptionPane.showMessageDialog(null, "No game to save!");
			return false;
		}
		
		try{
			int confirmSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to save the current game?", "Save Game", JOptionPane.YES_NO_OPTION);
			if(confirmSaveGame == JOptionPane.NO_OPTION ){
				return false;
			}
		}catch(NullPointerException e){
			return false;
		}
		
		String fileName = "";
		boolean nameSuccess = getFileName(fileName, "Name save file as?");
		if(!nameSuccess){
			return false;
		}
		File newFile = new File(fileName);
		
		try{
			if (newFile.exists()){
				int selectedSaveGame = JOptionPane.showConfirmDialog(null, "This file name already has a saved game. Would you like to save anyways?", "Save Game", JOptionPane.YES_NO_OPTION);
				if(selectedSaveGame == JOptionPane.NO_OPTION ){
					return false;
				}
			}
		}catch(NullPointerException e){
			return false;
		}
		
		FileWriter writer = null; 
		try{
			writer = new FileWriter(newFile);
			writer.write(currentGame.serialize());
			writer.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File cannot be written. Please check permissions.");
			return false;
		}
		return true;
	}
	
	// Quits the current game. Asks the user if they want to save first.
	public boolean deleteGame() {
		try{
			int confirmDeleteGame = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the current game?", "Confirm Delete Game", JOptionPane.YES_NO_OPTION);
			
			if(confirmDeleteGame == JOptionPane.YES_OPTION){
					//Will figure out how to get the old game to not be on screen
					//Need to consult the expert
					currentGame = null;
					return true;
				}
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
	}
	
	// Deletes the current game. Asks the user if they are sure before proceeding.
	public boolean quitGame() {
		try{
			int confirmExitGame = JOptionPane.showConfirmDialog(null, "Would you like to save the current game before exiting?", "Confirm Exit Game", JOptionPane.YES_NO_OPTION);
			
			if(confirmExitGame == JOptionPane.YES_OPTION){
				if(saveGame()){
					currentGame = null;
					JOptionPane.showMessageDialog(null, "You have now exited Java. Enjoy the real world!");
					return true;
				}
				return false;
			}
			else if(confirmExitGame == JOptionPane.NO_OPTION){
				currentGame = null;
				JOptionPane.showMessageDialog(null, "You have now exited Java. Enjoy the real world!");
				return true;
			}
			
			else{
				return false;
			}
		}catch(NullPointerException e){
			return false;
		}
	}
	
	public GamePanel getGamePanel(){
		return currentGame.getGamePanel();
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
			case 9:
				//tab through developers
				currentGame.tabThroughDevelopers();
				break;
			case 10:
				//pressed enter
				currentGame.placeComponent();
				break;	
			case 27:
				//cancel action
				currentGame.cancelAction();
				break;
			case 88:
				//end turn
				System.out.println("end turn");
				currentGame.nextTurn();
				break;
			case 32:
				//pressed the space bar
				currentGame.rotateCurrentComponent();
				break;
			
			case (98):
				//2 button numerical key - move developer down
				//don't do anything right now because we're not testing with a numerical key pad
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
				
			case 50:
				currentGame.selectTwoSpaceTile();
				break;
			case 51:
				currentGame.selectThreeSpaceTile();
				break;
			case 68:
				//pressed D, add new developer onto board
				currentGame.addDeveloperToBoard();
				break;
			case 73:
				//pressed I, add new Irrigation tile
				currentGame.selectIrrigationTile();
				break;
			//case 77;
				//pressed M, chooses the menu
			case 80:
				//pressed P, new palace tile, need to ask for value of Tile
				currentGame.selectPalaceTile();
				break;
			case 82:
				//pressed R, place rice tile
				currentGame.selectRiceTile();
				break;
			case 84:
				//pressed T, use action token
				currentGame.useActionToken();
				break;
			case 85:
				//pressed U, upgrade new palace
				currentGame.selectPalaceToUpgrade();
			case 86:
				//pressed V, place Village
				currentGame.selectVillageTile();
				break;
		/*
		 * using these arrow keys for movement of a developer for testing purposes*/
			case 37:
				//pressed left arrow, move the viewport left
				currentGame.moveComponentAroundBoard(-50, 0);
				break;
			case 38:
				//pressed up arrow, move the viewport up
				currentGame.moveComponentAroundBoard(0, -50);
				break;
			case 39:
				//pressed the right arrow, move the viewport right
				currentGame.moveComponentAroundBoard(50, 0);
				break;
			case 40:
				//pressed the down arrow, move the viewport down
				currentGame.moveComponentAroundBoard(0, 50);
				break;
		}
	}

}
