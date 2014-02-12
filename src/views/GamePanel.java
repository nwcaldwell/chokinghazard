package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Developer;
import models.Game;
import models.Player;
import models.Tile;

public class GamePanel extends JPanel{
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 840;
	private JLabel threePieceTiles, irrigationTiles, twoPalaceTiles, fourPalaceTiles, sixPalaceTiles, eightPalaceTiles, tenPalaceTiles; 
	Game gm;
	BoardPanel board;
	PlayerPanel[] players;
	int currentPlayer = 0;
	
	public GamePanel(int numberOfPlayers, Game gameController){
		super(new BorderLayout());
		
		gm = gameController;
		players = new PlayerPanel[numberOfPlayers];
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels(numberOfPlayers);
	}
	
	private void initPanels(int numberOfPlayers){
		JPanel topContent = new JPanel();
		topContent.setLayout(new FlowLayout());
		topContent.setPreferredSize(new Dimension(900, 110));
		add(topContent, BorderLayout.NORTH);
		
		threePieceTiles = newJLabel("56", "src/resources/layout/layout_threeTile.png"); 
		threePieceTiles.setPreferredSize(new Dimension(70, 90));
        topContent.add(threePieceTiles);
        
        irrigationTiles = newJLabel("10", "src/resources/layout/layout_oneTile_irrigation.png"); 
        topContent.add(irrigationTiles);
        
        twoPalaceTiles = newJLabel("6", "src/resources/layout/layout_oneTile_2.png"); 
        topContent.add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel("7", "src/resources/layout/layout_oneTile_4.png"); 
        topContent.add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel("8", "src/resources/layout/layout_oneTile_6.png"); 
        topContent.add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel("9", "src/resources/layout/layout_oneTile_8.png");
        topContent.add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel("10", "src/resources/layout/layout_oneTile_10.png"); 
        topContent.add(tenPalaceTiles);
        
        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon("src/resources/layout/actionSummaryCard.png"));
        actionSummaryCard.setPreferredSize(new Dimension(473, 77));
        actionSummaryCard.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        topContent.add(actionSummaryCard);
		
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
			players[i] = new PlayerPanel(i);
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
	
	private JLabel newJLabel(String info, String src){
		JLabel label= new JLabel(info);
		label.setIcon(new ImageIcon(src));
		label.setFont(new Font("Lucida Grande", 0, 14));
		label.setPreferredSize(new Dimension(40, 90));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		return label;
	}
	
	public void updateCurrentPlayerView(){
		players[currentPlayer].setCurrentPlayer();
	}
	
	public void nextTurn(){
		//set currentPlayer's background color back to default
		players[currentPlayer].setNotCurrentPlayer();
		
		//increment current player to advance to next player
		currentPlayer = (currentPlayer + 1) % players.length;
		
		//update the new current player
		updateCurrentPlayerView();
	}
	
	public void highlightDeveloper(Developer dev, int x, int y){
		this.board.highlightDeveloper(x, y);
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
	
	public void placeDeveloper(int player, int x, int y){
		this.board.placeDeveloper(player, x, y);
	}
	
	public void useActionToken(int tokens){
		this.players[currentPlayer].setNumActionTokens(tokens);
	}
	
	public void moveTile(Tile tile, int xLoc, int yLoc){
		this.board.moveTile(tile, xLoc, yLoc);
	}
	
	public void placeTile(Tile tile, int xLoc, int yLoc){
		this.board.placeTile(tile, xLoc, yLoc);
	}
	
	public void rotateTile(Tile tile, int xLoc, int yLoc){
		this.board.rotate(tile, xLoc, yLoc);
	}
	
	public void cancelActions(){
		this.board.cancel();
	}
	
	public void setThreePieceTiles(int num){
		this.threePieceTiles.setText(""+num);
	}
	public void setIrrigationTiles(int num){
		this.irrigationTiles.setText(""+num);
	}
	public void setTwoPalaceTiles(int num){
		this.twoPalaceTiles.setText(""+num);
	}
	public void setFourPalaceTiles(int num){
		this.fourPalaceTiles.setText(""+num);
	}
	public void setSixPalaceTiles(int num){
		this.sixPalaceTiles.setText(""+num);
	}
	public void setEightPalaceTiles(int num){
		this.eightPalaceTiles.setText(""+num);
	}
	public void setTenPalaceTiles(int num){
		this.tenPalaceTiles.setText(""+num);
	}
	public void setPlayerPanels(Player[] playerModels){
		for(int i = 0; i < playerModels.length; ++i){
			this.players[i].setPlayerName(playerModels[i].getPlayerName());
			this.players[i].setPlayerColor(playerModels[i].getPlayerColor());
		}
	}

}
