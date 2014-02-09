package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import models.Game;

public class PlayerPanel extends JPanel {
	private Color playerColor;
	private JLabel playerName, famePoints, actionPointsLeft, numDevelopers, numOneTileRice, numOneTileVillage, numTwoTile, numActionTokens;
	private Game gm;
	
	public PlayerPanel(Game gm){
		setLayout(new FlowLayout());
		this.gm = gm;
		setBackground(Color.white);
        setPreferredSize(new Dimension(170, 335));
        setMinimumSize(new Dimension(170, 335));
        setMaximumSize(new Dimension(170, 335));
        setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
  
        initLayout();
	}

	private void initLayout() {
		JPanel leftPlayerInfo = new JPanel();
		leftPlayerInfo.setPreferredSize(new Dimension(86, 60));
		leftPlayerInfo.setBackground(Color.WHITE);
		this.add(leftPlayerInfo);
		
		playerName = new JLabel("Player");
        playerName.setFont(new Font("Lucida Grande", 0, 18)); 
        playerName.setPreferredSize(new Dimension(80, 22));
        playerName.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        leftPlayerInfo.add(playerName);
        
        actionPointsLeft = new JLabel("6");
        actionPointsLeft.setFont(new Font("Lucida Grande", 0, 48)); 
        actionPointsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        actionPointsLeft.setPreferredSize(new Dimension(60, 60));
        actionPointsLeft.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.add(actionPointsLeft);
        
        famePoints = new JLabel("0");
        famePoints.setFont(new Font("Lucida Grande", 1, 36));
        famePoints.setBackground(Color.RED);
        famePoints.setHorizontalAlignment(SwingConstants.LEFT);
        famePoints.setPreferredSize(new Dimension(80, 32));
        famePoints.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        leftPlayerInfo.add(famePoints);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setForeground(new Color(102, 102, 102));
        jSeparator1.setPreferredSize(new Dimension(158, 15));
        this.add(jSeparator1);
        
        numDevelopers = new JLabel("12");
        numDevelopers.setFont(new Font("Lucida Grande", 0, 14));
        numDevelopers.setIcon(new ImageIcon("src/resources/layout/layout_developer_purple.png")); 
        numDevelopers.setPreferredSize(new Dimension(150, 41));
        numDevelopers.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        this.add(numDevelopers);

        numOneTileRice = new JLabel("3");
        numOneTileRice.setFont(new Font("Lucida Grande", 0, 14)); 
        numOneTileRice.setIcon(new ImageIcon("src/resources/layout/layout_oneTile_rice.png")); 
        numOneTileRice.setPreferredSize(new Dimension(150, 41));
        numOneTileRice.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        this.add(numOneTileRice);
        
        numOneTileVillage = new JLabel("2");
        numOneTileVillage.setFont(new Font("Lucida Grande", 0, 14)); 
        numOneTileVillage.setIcon(new ImageIcon("src/resources/layout/layout_oneTile_village.png")); 
        numOneTileVillage.setPreferredSize(new Dimension(150, 41));
        numOneTileVillage.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        this.add(numOneTileVillage);

        numTwoTile = new JLabel("5");
        numTwoTile.setFont(new Font("Lucida Grande", 0, 14)); 
        numTwoTile.setIcon(new ImageIcon("src/resources/layout/layout_twoTile.png")); 
        numTwoTile.setPreferredSize(new Dimension(150, 41));
        numTwoTile.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        this.add(numTwoTile);
        
        numActionTokens = new JLabel("3");
        numActionTokens.setFont(new Font("Lucida Grande", 0, 14)); 
        numActionTokens.setIcon(new ImageIcon("src/resources/layout/layout_oneTile_irrigation.png")); 
        numActionTokens.setPreferredSize(new Dimension(150, 41));
        numActionTokens.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        this.add(numActionTokens);
		
	}
	
	public void setPlayerName(String playersName){
		this.playerName.setText(playersName);
	}
	
	public void setPlayerColor(Color newColor){
		this.playerColor = newColor;
	}

	public void setFamePoints(int famePoints) {
		this.famePoints.setText(""+famePoints);
	}

	public void setActionPointsLeft(int actionPointsLeft) {
		this.actionPointsLeft.setText(""+actionPointsLeft);
	}

	public void setNumDevelopers(int numDevelopers) {
		this.numDevelopers.setText(""+numDevelopers);
	}

	public void setNumOneTileRice(int numOneTileRice) {
		this.numOneTileRice.setText(""+numOneTileRice);
	}

	public void setNumOneTileVillage(int numOneTileVillage) {
		this.numOneTileVillage.setText(""+numOneTileVillage);
	}

	public void setNumTwoTile(int numTwoTile) {
		this.numTwoTile.setText(""+numTwoTile);
	}

	public void setNumActionTokens(int numActionTokens) {
		this.numActionTokens.setText(""+numActionTokens);
	}
	
	public void setBackgroundCurrentPlayer(){
		this.setBackground(playerColor);
	}
	
	public void setBackgroundNotCurrentPlayer(){
		this.setBackground(Color.WHITE);
	}

}
