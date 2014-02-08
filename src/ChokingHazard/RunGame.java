package ChokingHazard;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import views.GameFrame;

public class RunGame {
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 880;

	/**
	 * @param args
	 * this is the main class that initializes the GUI
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});

	}
	
	private static void createAndShowGUI(){
		JFrame frame = new GameFrame(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
