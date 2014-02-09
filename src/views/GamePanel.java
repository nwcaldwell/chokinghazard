package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Developer;

import controllers.GameManager;

public class GamePanel extends JPanel{
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 840;
	private JLabel threePieceTiles, irrigationTiles; 
	GameManager gm;
	BoardPanel board;
	PlayerPanel[] players;
	int currentPlayer = 0;
	
	public GamePanel(int numberOfPlayers, GameManager gameManager){
		super(new BorderLayout());
		
		gm = gameManager;
		players = new PlayerPanel[numberOfPlayers];
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels(numberOfPlayers);
	}
	
	private void initPanels(int numberOfPlayers){
		JPanel topContent = new JPanel();
		topContent.setLayout(new FlowLayout());
		topContent.setPreferredSize(new Dimension(900, 90));
		add(topContent, BorderLayout.NORTH);
		
		threePieceTiles = new JLabel("56");
		threePieceTiles.setFont(new Font("Lucida Grande", 0, 14));
		threePieceTiles.setIcon(new ImageIcon("src/resources/layout/layout_threeTile.png")); 
		threePieceTiles.setPreferredSize(new Dimension(100, 100));
		threePieceTiles.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        topContent.add(threePieceTiles);
        
        irrigationTiles = new JLabel("10");
        irrigationTiles.setFont(new Font("Lucida Grande", 0, 14));
        irrigationTiles.setIcon(new ImageIcon("src/resources/layout/layout_oneTile_irrigation.png")); 
        irrigationTiles.setPreferredSize(new Dimension(100, 100));
        irrigationTiles.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        topContent.add(irrigationTiles);
		
		board = new BoardPanel();
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setLayout(new BorderLayout());
		leftColumn.setPreferredSize(new Dimension(175, 700));
		leftColumn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BorderLayout());
		rightColumn.setPreferredSize(new Dimension(175, 700));
		rightColumn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < numberOfPlayers; i++){
			players[i] = new PlayerPanel(gm);
			switch(i){
				case 0:
					leftColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 1:
					rightColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 2:
					leftColumn.add(players[i], BorderLayout.SOUTH);
					break;
				case 3:
					rightColumn.add(players[i], BorderLayout.SOUTH);
					break;
			}
		}
	}
	
	public void updateCurrentPlayerView(){
		players[currentPlayer].setBackgroundCurrentPlayer();
	}
	
	public void nextTurn(){
		//set currentPlayer's background color back to default
		players[currentPlayer].setBackgroundNotCurrentPlayer();
		
		//increment current player to advance to next player
		currentPlayer = (currentPlayer + 1) % players.length;
		//update the new current player
		updateCurrentPlayerView();
	}
	
	public void highlightDeveloper(Developer dev){
		this.board.highlightDeveloper(dev.getCurrentCell().getX(), dev.getCurrentCell().getY());
	}
	
	public PlayerPanel[] getPlayerPanels(){
		return this.players;
	}
	
	public PlayerPanel getCurrentPlayer(){
		return this.players[currentPlayer];
	}
	
	public void moveDeveloperOntoBoard(int x, int y){
		this.board.trackDeveloperPath(x, y);
	}
	
	public void placeDeveloper(Developer dev, int x, int y){
		this.board.placeDeveloper(dev, x, y);
	}
	
	public void useActionToken(int tokens){
		this.players[currentPlayer].setNumActionTokens(tokens);
	}

}
