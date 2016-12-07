package object;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

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
public class GameWin extends GameObject {
	//private BufferedImage waveImg;
	private int count;//remaining time until game exit in seconds
	private Timer time;//timer to remove one from count
	/**
	 * creates game win object to end game 3
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public GameWin(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		count = 4;
		time = new Timer(1000, listener);//call listener every second
		time.start();
		Game.gameover = true;//removes key listener in game class
		Game3Timer.clock1.stop();
		
		
		
		
	}

	ActionListener listener = new ActionListener() {
		@Override
		//called by time every second
		public void actionPerformed(ActionEvent e) {
			count--;
		}
	};
	@Override
	/**
	 * continuously called to remove game 3 objects and count down timer.
	 */
	public void tick(LinkedList<GameObject> object) {
		//removes game3 related objects
		for(Iterator<GameObject> it = object.iterator();it.hasNext();){
			GameObject temp = it.next();
			if(temp.getId() == ObjectId.wclock || temp.getId() == ObjectId.person || temp.getId() == ObjectId.waves)
				it.remove();
		}
		//exit game when count is 0 and stop timer
		if(count == 0){
			time.stop();
			System.exit(1);
		}
		
	}

	@Override
	//print game win message
	/**
	 * prints game win method
	 */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Congratulations!",(int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3));
		g.drawString("You have successfully protected the estuary!!",(int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3 + 20));
		g.drawString("Returning to menu in: " + count, (int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/3+40));
		

	}
	

	@Override
	//bounds dont exist
	/**
	 * gets this objects bounds
	 * @return Rectangle of null
	 */
	public Rectangle getBounds() {
		return null;
	}
	
}