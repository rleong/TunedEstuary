package object;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
import window.Window;
/**
 * 
 * @author justin said
 *
 */
public class GameOver extends GameObject {
	BufferedWriter bw = null;
	FileWriter fw = null;
	private int count;//remaining time until game exit in seconds
	private Timer time;//timer to remove one from count
	/**
	 * creates gameover object to end game 3
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public GameOver(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		count = 4;
		time = new Timer(1000, listener);//call listener every second
		time.start();
		Game.gameover = true;//removes key listener in game class
		GameTimer.clock1.stop();//stop game 3 timer	
		//write();
	}
	ActionListener listener = new ActionListener() {
		@Override
		//called by time every second
		public void actionPerformed(ActionEvent e) {
			count--;
		}
	};
	/**
	 * writes to file
	 */
	public void write(){
		try {

			long content = Game.startTime - System.currentTimeMillis();
			fw = new FileWriter("test\timeElapsed.txt");
			bw = new BufferedWriter(fw);
			bw.write( Long.toString(content));

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * reads from file
	 */
	public String read(){
		String out = "";
		try {
		BufferedReader br = new BufferedReader(new FileReader("test\timeElapsed.txt"));
		try {
			out = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return out;
	}
	@Override
	/**
	 * continuously called to remove game 3 objects and count down timer.
	 */
	public void tick(ArrayList<GameObject> object) {
		//removes game3 related objects
		for(int i=0;i<object.size();i++){
			GameObject temp = object.get(i);
			if(temp.getId() == ObjectId.wclock || temp.getId() == ObjectId.person || temp.getId() == ObjectId.waves)
				object.remove(temp);
		}
		//exit game when count is 0 and stop timer
		if(count == -1){
			time.stop();
			System.exit(1);
		}
	}

	@Override
	//prints game loss message
	/**
	 * prints game loss method
	 */
	public void pngSelector(Graphics g) {
		g.setFont(new Font("Times",20,20));
		g.setColor(Color.black);
		g.drawString("Game Over",(int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3));
		g.drawString("You failed to protect the Estuary",(int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3 + 20));
		g.drawString("Returning to menu in: " + count, (int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3+40));
		g.drawString("Time elapsed in previous game: " + read(), (int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3+60));
	}
	@Override
	//has no bounds
	/**
	 * gets this objects bounds
	 * @return Rectangle of null
	 */
	public Rectangle getBounds() {
		return null;
	}
	
}
