package controllers;

import helpers.Json;
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
		if(currentGame != null){
			currentGame = new Game(numPlayers);
			//currentGame.initPlayers();
		}
		else{
			currentGame = new Game(numPlayers);
		}
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
			Game loadedGame = new Game().loadObject(json.getJsonObject("Game"));
			/*try {FileWriter fw = new FileWriter(new File("outs.txt"));
			fw.write(loadedGame.serialize());
			fw.close(); } catch(Exception e)*/
			currentGame = loadedGame;
	
		return true;
	}

	// Saves the current game to a text file in the format specified by serializable.
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
		boolean okFileName = false;
		
		while(!okFileName ){
			//File name is invalid if it is blank, or has any of the following
			//  \ / ? % * : | " < > . (space) (empty)
			try{
				//rewrite dialog later
				fileName = JOptionPane.showInputDialog("Name save file as?");
				String s = ".*[ /\\\\?%*:|\"<>.].*";
				if (fileName.matches(s) || fileName.equals(""))	//means that name doesn't contain any characters in s
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
		
		FileWriter writer; 
		try{
			System.out.println(fileName);
			writer = new FileWriter(newFile);
			writer.write(currentGame.serialize());
			writer.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File cannot be written. Please check permissions.");
			return false;
		}
		return true;
	}
	

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

	public void keyReleased(KeyEvent e) {
		//set up the key input events
		//1 selects one piece tile
		switch(e.getKeyCode()){
			case 9:
				//tab through developers
				System.out.println("next developer");
				currentGame.tabThroughDevelopers();
				break;
			case 10:
				//pressed enter
				System.out.println("pressed enter");
				if (currentGame.getCurrentComponent() != null) {
					currentGame.placeComponent();
				}
				break;	
			case 27:
				//cancel action
				System.out.println("cancel action");
				currentGame.cancelAction();
				break;
			case 88:
				//end turn
				currentGame.nextTurn();
				break;
			case 32:
				//pressed the space bar
				System.out.println("rotate");
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
				System.out.println("Place Village Tile");
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
