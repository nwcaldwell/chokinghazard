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
	// for testing
	 	
	public static void main(String args[]){

		GameManager practice = new GameManager(new Game(2));
		practice.saveGame();
	}
	
	

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
	
	// Loads a game from the specified textfile and assigns it 
	// to the current game.
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
	
	public boolean loadGame() {
		//parse the file and create a currentGame from it.
		boolean loadSuccess = false;
		String fileName = "";
		
		while(!loadSuccess ){
			
			try{
				int confirmSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to load a past game?", "Confirm Load Game", JOptionPane.YES_NO_OPTION);
				if(confirmSaveGame == JOptionPane.NO_OPTION ){
					return false;
				}
			}catch(NullPointerException e){
				return false;
			}
		
			if(!getFileName(fileName, "Name of file to load?")){
				return false;
			}
			
			
			StringBuilder alpha = new StringBuilder();
			
			File loadFile = new File(fileName + ".txt");
			try{
				if(loadFile.exists()){
					Scanner input = new Scanner(loadFile);
					while(input.hasNextLine()){
						alpha.append(input.nextLine());
					}
					input.close();
				}
				else{
					JOptionPane.showMessageDialog(null, "File " + fileName + " could not be found.");
					loadSuccess = false;
				}
			}catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "File " + fileName + " could not be read. Check permissions.");
				loadSuccess = false;
			}
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
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File cannot be written. Please check permissions.");
			return false;
		}finally{
			try{
				writer.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(null, "File cannot be closed. Please do something else.");
			}
		}
		return true;
	}
	
	
	
	// Quits the current game. Asks the user if they want to save first.
	public boolean quitGame() {
		//returning true quits the game
		if(currentGame == null){
			return true;
		}
		
		int selectedSaveGame = JOptionPane.showConfirmDialog(null, "Would you like to save this game before quitting?", "Quit Game", JOptionPane.YES_NO_OPTION);
		
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
		
		int selectedSaveGame = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this game?", "Delete Game", JOptionPane.YES_NO_OPTION);
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
