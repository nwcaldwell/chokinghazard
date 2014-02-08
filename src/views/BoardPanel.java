package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	BufferedImage board;

	public BoardPanel(){
		setBackground(Color.black);
		getBackgroundImage();
		Dimension size = new Dimension(board.getWidth(), board.getHeight());
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
	private void getBackgroundImage(){
		try{
			System.out.println(System.getProperty("user.dir"));
			board = ImageIO.read(new File("src/resources/board.jpg"));
			repaint();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
	}
}
