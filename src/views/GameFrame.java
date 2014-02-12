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
				if(gm != null){
					//they're already in a game, so ask if they're sure they want to do this, and ask to save game
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game in the middle of this game?");
					if(response == JOptionPane.YES_OPTION){
						gm.saveGame();
					}
					else{
						//do nothing
					}
				}
				else{
					/** Brett ***/ 
					String playerCount = JOptionPane.showInputDialog("How many players? 2-4", null);
					
					boolean canProceed = false; //goes true when we have acceptable input
					boolean isInt = true; //For successful parse
					int inputPlayers = -1;
					while (!canProceed){	
						try {
							inputPlayers = Integer.parseInt(playerCount); //try to parse
							isInt = true;
						} catch (NumberFormatException ex) {
							//if we catch an exception, set to false, go back to above loop
							isInt = false; 
						}
						if (isInt){
							final int numPlayers = inputPlayers;
							if(numPlayers >= 2 && numPlayers <= 4){
								new Thread(new Runnable(){
									public void run(){
										gm = GameManager.getInstance();
										gm.createNewGame(numPlayers);
										setContentPane(gm.getGamePanel());
										pack();
										validate();
									}
								}).start();
								
								canProceed = true;
							} 
							else {
								JOptionPane.showMessageDialog(null, "Sorry, you need to have 2 - 4 players, try again");
								playerCount = JOptionPane.showInputDialog("How many players? 2-4");
							} //end else
						}
						else{
							JOptionPane.showMessageDialog(null, "Please enter a number of players");
							playerCount = JOptionPane.showInputDialog("How many players? 2-4");
						}
					} //end while
					
				}
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Open Game");
		loadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gm == null){
					//loads a game
					final File file = getFile();
					if(file != null){
						//starts a new thread
						new Thread(new Runnable(){
							public void run(){
								gm = GameManager.getInstance();
								gm.loadGame(file);
								setContentPane(gm.getGamePanel());
								pack();
								validate();
							}
						}).start();
					}
				}
				else{
					//they're already in a game, so ask if they're sure they want to do this, and ask to save game
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game in the middle of this game?");
					if(response == JOptionPane.YES_OPTION){
						gm.saveGame();
					}
					else{
						//do nothing
					}
				}
			}
		});
		file.add(loadGame);
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	if(gm != null){
            		gm.saveGame();
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "You don't have a game started, try creating a new one.");
            	}
            }
        }); 
        file.add(saveGame);
        
        JMenuItem exit = new JMenuItem("Exit");
        saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	boolean quit = gm.quitGame();
            	if(quit)
            		System.exit(0);
            }
        });
        file.add(exit);
 
        JMenu help = new JMenu("Help");
        
        JMenuItem controls = new JMenuItem("Game Controls");
        controls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        controls.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	//display a frame that shows a keyboard mapping of the game controls
            	HelpFrame frame = new HelpFrame();
        		frame.setVisible(true);
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
