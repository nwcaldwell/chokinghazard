package controllers;

//import helpers.Json;
import helpers.JsonObject;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import models.Game;
import views.GamePanel;

public final class GameManager {
    
    private static GameManager instance;
	//Attributes
	private Game currentGame;
	
	
	// CONSTRUCTORS
	// Default constructor
	private GameManager() {
		
	}
	
	// Main constructor
	public GameManager(Game currentGame) {
	
	}
	
	public static synchronized GameManager getInstance() {
	    if(instance == null)
	        instance = new GameManager();
	    
	    return instance;
	}
	
	// Creates new game using the appropriate number of players.
	// Sets game variable equal to the new game.
	public void createNewGame(int numPlayers) {
			currentGame = new Game(numPlayers);
			//currentGame.initPlayers();
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
			
			String loadString = alpha.toString();
			JsonObject json = new JsonObject(loadString);
			Game loadedGame = new Game();
			loadedGame.loadObject(json);
			currentGame = loadedGame;
	
		return true;
	}

	// Saves the current game to a text file
	public boolean saveGame() {
		//Check if the the GameManager has a currentGame. 
		//If it doesn't have one, there is nothing to save and the method ends
		if(currentGame == null){
			JOptionPane.showMessageDialog(null, "No game to save!");
			return false;
		}
		
		//Prompts the user asking if they would like to save the current game
		//Saying no means that they would not like to, so the method ends
		//NullPointerException occurs when the dialog box is closed by other means, 
		//which we will assume means the user does not want to save the game so the method ends
		try{
			int confirmSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to save the current game?", "Save Game", JOptionPane.YES_NO_OPTION);
			if(confirmSaveGame == JOptionPane.NO_OPTION ){
				return false;
			}
		}catch(NullPointerException e){
			return false;
		}
		
		
		
		//initialization of variables to be used for getting the filename and creating the file
		String fileName = "";
		boolean okFileName = false;
		
		
		//this loop continues to run while the file name is false, or "is not an okay filename"
		//File name is invalid if it is blank, or has any of the following
		//  \ / ? % * : | " < > . (space) (empty)
		while(!okFileName ){

			//Prompts the user for a filename. The user can do many things, including inputing a filename
			//Check with the regular expression string s if the String fileName contains any of those characters
			//if the file is invalid, prompt the user for a valid name and go through the while loop again
			//if the filename is valid, continue out of the loop
			//NullPointerException occurs when either of the dialog boxes are closed by other means, 
			//which we will assume means the user does not want to save the game so the method ends
			try{
				
				fileName = JOptionPane.showInputDialog("Name save file as?");
				String s = ".*[ /\\\\?%*:|\"<>.].*";
				if (fileName.matches(s) || fileName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
				}
				else {
					okFileName = true;
				}
			}catch(NullPointerException e){
				return false;
			}
		}
		
		//Create a new file with the (now valid) fileName recieved from the user
		File newFile = new File(fileName);
		
		//This next block of code is only run if the file already exists.
		//Prompt the user telling them that this file already has a saved game
		//If the user chooses Yes, continue to the next block of code
		//If no, the user doesn't want to save the game and the method ends
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
		
		
		//Create a new FileWriter to write to the file, but will be initialized in the next block of code
		FileWriter writer; 
		
		
		//Attempt to initialize and write to the newFile which was created above
		//Then write the "serialize" string from currentGame, which gives you
		//then attempt to close the writer
		//These actions can cause IOExceptions, which we will inform the user about and then exit the method
		try{
			//System.out.println(fileName);
			writer = new FileWriter(newFile);
			writer.write(currentGame.serialize());
			writer.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File cannot be written. Please check permissions.");
			return false;
		}
		
		//If you have reached here, you have successfully (in theory) saved the game! 
		return true;
	}
	

	public boolean quitGame() {
		
		//Prompts the user asking if they would like to save the current game before exiting the software
		//If they user says yes, call saveGame() and if that works, it returns true and then exits the game
		//Then the currentGame is now null, so the garbage collector can do its job
		//if the player chooses no, the game will be released and they will be free to leave
		//if the player chooses anything else or there is a NullPointerException, meaning the dialog box was exited
		//in an unexpected way, we will assume that the user doesn't want to quit so we will get out of the method.
		
		try{
			int confirmExitGame = JOptionPane.showConfirmDialog(null, "Would you like to save the current game before exiting?", "Confirm Exit Game", JOptionPane.YES_NO_OPTION);

			if(confirmExitGame == JOptionPane.YES_OPTION || confirmExitGame == JOptionPane.NO_OPTION){
				if(confirmExitGame == JOptionPane.YES_OPTION && saveGame()){
					currentGame = null;
				}
					JOptionPane.showMessageDialog(null, "You have now exited Java. Enjoy the real world!");
					return true;
			}
				return false;
		}catch(NullPointerException e){
			return false;
		}
	}
	
	
	public GamePanel getGamePanel(){
		return currentGame.getGamePanel();
	}

	public void keyReleased(KeyEvent e) {
		//set up the key input events
		//1 selects one piece tile
		switch(e.getKeyCode()){
			case 8:
				//delete a developer from the board
				currentGame.removeDeveloper();
			case 9:
				//tab through developers
				currentGame.incrementTab();
				currentGame.tabThroughDevelopers();
				break;
			case 10:
				//pressed enter
				if (currentGame.getCurrentComponent() != null){
					currentGame.placeComponent();
				}
				break;	
			case 27:
				//cancel action
				currentGame.cancelAction();
				break;
			case 88:
				//end turn
				currentGame.nextTurn();
				break;
			case 32:
				//pressed the space bar
				if (currentGame.getCurrentComponent() != null) {
					currentGame.rotateCurrentComponent();
				}
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
				//pressed U, upgrade palace
				currentGame.selectPalaceToUpgrade();
				break;
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
