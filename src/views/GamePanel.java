package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import controllers.GameManager;

public class GamePanel extends JPanel {
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 860;
	GameManager gm;
	BoardPanel board;
	PlayerPanel[] players;
	int numPlayers;
	
	public GamePanel(int numberOfPlayers, GameManager gameManager){
		super(new BorderLayout());
		
		numPlayers = numberOfPlayers;
		gm = gameManager;
		players = new PlayerPanel[numPlayers];
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		
		initPanels();
		
	}
	
	private void initPanels(){
		JPanel topContent = new JPanel();
		topContent.setPreferredSize(new Dimension(125, 900));
		add(topContent, BorderLayout.NORTH);
		
		board = new BoardPanel();
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setPreferredSize(new Dimension(170, 350));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setPreferredSize(new Dimension(170, 350));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < numPlayers; i++){
			players[i] = new PlayerPanel(gm, i);
			if(i % 2 == 0)
				rightColumn.add(players[i]);
			else
				leftColumn.add(players[i]);
		}
	}

}
