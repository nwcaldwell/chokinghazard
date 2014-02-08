package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	}
	
	private void addMenu(){
		//this method adds a menu bar to the JFrame (this class)
		JMenu file = new JMenu("File");
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable(){
					public void run(){
						int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players?"));
						gm.createNewGame(numPlayers);
						setContentPane(gm.getGamePanel());
						pack();
						validate();
					}
				}).start();
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Load Game");
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//loads a game
				//starts a new thread
				new Thread(new Runnable(){
					public void run(){
						//TODO: this will actually be different, there's such thing called JOptionChooser or something
						String nameOfGame = JOptionPane.showInputDialog("What's the name of the Game");
						gm.loadGame(nameOfGame);
						setContentPane(gm.getGamePanel());
						pack();
						validate();
					}
				}).start();
			}
		});
		file.add(loadGame);
		
		JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	//check if there's a saved game already to overwrite, 
            	//if not, save game as
            	//TODO
            	gm.saveGame();
            }
        }); 
        file.add(saveGame);
        
        JMenuItem deleteGame = new JMenuItem("Delete Game");
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	//check if there's a saved game already to overwrite, 
            	//if not, save game as
            	//TODO
            	gm.deleteGame();
            }
        }); 
        file.add(deleteGame);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	gm.quitGame();
                System.exit(0);
            }
        });
        file.add(exit);
        
        JMenu help = new JMenu("Help");
        
        
        //add the menu bar to the JFrame
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
	}

}
