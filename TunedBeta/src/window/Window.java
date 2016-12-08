package window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import control.Game;
import control.Game;

public class Window {
	
	//Attributes
	private JFrame frame;
	private static Dimension dm;
	
	/**
	 * Constructor to construct a window jframe that will used to show our game on the screen
	 * 
	 * @param dm - dimensions
	 * @param title - title of the window 
	 * @param game - game 
	 */
	public Window(Dimension dm, String title, Game game) {
		this.dm = dm;
		game.setPreferredSize(dm);
		game.setMaximumSize(dm);
		game.setMinimumSize(dm);
		
		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		game.start();
	}
	
	/**
	 * Method that will change the window. Every time this function is called
	 * it changes the game by removing the odd objects of the previous game and 
	 * add the objects of the new game.
	 * 
	 * @param w - width
	 * @param h - height 
	 * @param title - title of window 
	 * @param gamec - game number
	 */
	public void changeWindow(int w, int h, String title, Game gamec){
		//Dimension dm = new Dimension(w,h);
		
		gamec.setPreferredSize(new Dimension(w, h));
		gamec.setMaximumSize(new Dimension(w,h));
		gamec.setMinimumSize(new Dimension(w,h));
		
		//frame = new JFrame(title);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(gamec);
		frame.getContentPane().revalidate();
		frame.repaint();
		gamec.start();
		
	}
	
	/**
	 * Method that returns the dimensions
	 * @return
	 */
	public static Dimension getDm(){
		return dm;
	}
}
