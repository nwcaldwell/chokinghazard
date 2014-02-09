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

import controllers.GameManager;


public class GameFrame extends JFrame {
	//this class implements frame for the game
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
				gm.keyTyped(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				gm.keyPressed(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				gm.keyReleased(e);
			}
		});
	}
	
	private void addMenu(){
		//this method adds a menu bar to the JFrame (this class)
		JMenu file = new JMenu("File");
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players? 2-4"));
				if(numPlayers >= 2 && numPlayers <= 4){
					new Thread(new Runnable(){
						public void run(){
							gm.createNewGame(numPlayers);
							setContentPane(gm.getGamePanel());
							pack();
							validate();
						}
					}).start();
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, you need to have 2 - 4 players, try again");
				}
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Load Game");
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
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	gm.saveGame();
            }
        }); 
        file.add(saveGame);
        
        JMenuItem deleteGame = new JMenuItem("Delete Game");
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	gm.deleteGame();
            }
        }); 
        file.add(deleteGame);
        
        JMenuItem exit = new JMenuItem("Exit");
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
