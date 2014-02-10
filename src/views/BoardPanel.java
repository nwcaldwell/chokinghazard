package views;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import models.Developer;
import models.Tile;

public class BoardPanel extends JPanel {
	BufferedImage board;
	BufferedImage tileImage;
	BufferedImage developers;
	BufferedImage tempImage;
	Graphics2D g2d;
	int rotationState = 0;

	public BoardPanel(){
		getBackgroundImage();
		Dimension size = new Dimension(board.getWidth(), board.getHeight());
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		
		this.tileImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.developers = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.tempImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	private void getBackgroundImage(){
		try{
			board = ImageIO.read(new File("src/resources/board.jpg"));
			repaint();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	private BufferedImage getImage(String source){
		BufferedImage returnImage = null;
		try{
			returnImage = ImageIO.read(new File(source));
		} catch(IOException e){
			
		}
		return returnImage;
	}
	
	public void placeTile(Tile tile, int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = tileImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+50, yLoc+50);
		g2d.drawImage(getImage(tile.getImageSource()), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
		rotationState = 0;
	}
	
	public void moveTile(Tile tile, int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+50, yLoc+50);
		g2d.drawImage(getImage(tile.getImageSource()), null, xLoc, yLoc);
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void rotate(Tile tile, int xLoc, int yLoc){
		rotationState = (rotationState +1) % 4;
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+50, yLoc+50);
		g2d.drawImage(getImage(tile.getImageSource()), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
	public void trackDeveloperPath(int xLoc, int yLoc){
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke());
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void placeDeveloper(Developer dev, int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = developers.createGraphics();
		
		g2d.setColor(Color.RED);
		g2d.fillRect(xLoc, yLoc, 50, 50);
		//g2d.drawImage(getImage(dev.getImageSource()), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
	public void highlightDeveloper(int x, int y){
		g2d = developers.createGraphics();
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(x, y, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void cancel(){
		clearImage(tempImage);
		rotationState = 0;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		g.drawImage(tileImage, 0, 0, null);
		g.drawImage(tempImage, 0, 0, null);
		g.drawImage(developers, 0, 0, null);
	}
	
	private void clearImage(BufferedImage image){
		g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR)); 
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g2d.dispose();
	}
}
