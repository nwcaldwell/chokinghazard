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

import controllers.GameManager;

public class GamePanel extends JPanel {
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 840;
	private JLabel threePieceTiles, irrigationTiles; 
	GameManager gm;
	BoardPanel board;
	PlayerPanel[] players;
	int numPlayers;
	
	public GamePanel(int numberOfPlayers, GameManager gameManager){
		super(new BorderLayout());
		
		numPlayers = numberOfPlayers;
		gm = gameManager;
		players = new PlayerPanel[numPlayers];
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels();
		
	}
	
	private void initPanels(){
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
		
		for(int i = 0; i < numPlayers; i++){
			players[i] = new PlayerPanel(gm, i);
			switch(i){
				case 0:
					System.out.println("adding top left");
					leftColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 1:
					System.out.println("adding top right");
					rightColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 2:
					System.out.println("adding bottom left");
					leftColumn.add(players[i], BorderLayout.SOUTH);
					break;
				case 3:
					System.out.println("adding bottom right");
					rightColumn.add(players[i], BorderLayout.SOUTH);
					break;
			}
		}
	}

}
