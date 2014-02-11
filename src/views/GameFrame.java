package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controllers.GameManager;


public class GameFrame extends JFrame {
	//this class is the frame for the game
	private final int WIDTH, HEIGHT;
	GameManager gm;
	
	public GameFrame(int width, int height){
		WIDTH = width;
		HEIGHT = height;
		setTitle("Java - by Choking Hazard");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		gm = new GameManager();
		addMenu();
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(gm != null)
					gm.keyReleased(e);
			}
		});
		setFocusTraversalKeysEnabled(false);
	}
	
	private void addMenu(){
		//this method adds a menu bar to the JFrame (this class)
		final JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Brett ***/ 
				String playerCount = JOptionPane.showInputDialog("How many players? 2-4");
				boolean canProceed = false; //goes true when we have acceptable input
				boolean isInt = true; //For successful parse
				int inputPlayers = -1;
				while (!canProceed)
				{	
					try 
					{
						inputPlayers = Integer.parseInt(playerCount); //try to parse
						isInt = true;;
					}
					catch (NumberFormatException ex) //catch exception if they entered blank or a non-numeric value
					{
						isInt = false;  //if we catch an exception, set to false, go back to above loop
						
					}
					if (isInt) //successful entry passes this
					{
						final int numPlayers = inputPlayers;
						if(numPlayers >= 2 && numPlayers <= 4)	//check that it is in the appropriate range
						{
							new Thread(new Runnable(){
								public void run(){
									gm.createNewGame(numPlayers);
									setContentPane(gm.getGamePanel());
									pack();
									validate();
								}
							}).start();
							
							canProceed = true; //good to go 
						} //end if
						else
						{
							JOptionPane.showMessageDialog(null, "Sorry, you need to have 2 - 4 players, try again");
							playerCount = JOptionPane.showInputDialog("How many players? 2-4");
						} //end else
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please enter a number of players");
						playerCount = JOptionPane.showInputDialog("How many players? 2-4");
					}
				} //end while
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Open Game");
		loadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//loads a game
				final File file = getFile();
				if(file != null){
					//starts a new thread
					new Thread(new Runnable(){
						public void run(){
							gm.loadGame(file);
							setContentPane(gm.getGamePanel());
							pack();
							validate();
						}
					}).start();
				}
			}
		});
		file.add(loadGame);
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	gm.saveGame();
            }
        }); 
        file.add(saveGame);
    /*    
        JMenuItem deleteGame = new JMenuItem("Delete Game");
        deleteGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	if(gm.deleteGame()){
            		setContentPane(new JPanel());
            		pack();
            		validate();
            	}
            }
        }); 
        file.add(deleteGame);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	boolean quit = gm.quitGame();
            	if(quit)
            		System.exit(0);
            }
        });
        file.add(exit);
     */   
        JMenu help = new JMenu("Help");
        
        JMenuItem controls = new JMenuItem("Game Controls");
        controls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        controls.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	//display a frame that shows a keyboard mapping of the game controls
            	
            }
        });
        help.add(controls);
        
        
        //add the menu bar to the JFrame
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
	}
	
	private File getFile(){
		//this method opens a JFileChooser Dialog so the user
		//may select which file they would like to load
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		File file = null;
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		return file;
	}

}
