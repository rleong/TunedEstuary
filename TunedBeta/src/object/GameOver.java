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

public class GameOver extends GameObject {
	private int count;//remaining time until game exit in seconds
	private Timer time;//timer to remove one from count
	public GameOver(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id, handler);
		count = 4;
		time = new Timer(1000, listener);//call listener every second
		time.start();
		Game.gameover = true;//removes key listener in game class
		Game3Timer.clock1.stop();//stop game 3 timer	
	}
	ActionListener listener = new ActionListener() {
		@Override
		//called by time every second
		public void actionPerformed(ActionEvent e) {
			count--;
		}
	};
	@Override
	public void tick(LinkedList<GameObject> object) {
		//removes game3 related objects
		for(Iterator<GameObject> it = object.iterator();it.hasNext();){
			GameObject temp = it.next();
			if(temp.getId() == ObjectId.wclock || temp.getId() == ObjectId.person || temp.getId() == ObjectId.waves)
				it.remove();
		}
		//exit game when count is 0 and stop timer
		if(count == -1){
			time.stop();
			System.exit(1);
		}
	}

	@Override
	//prints game loss message
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Game Over",(int) (Window.getDm().getWidth()/2.5),(int) (Window.getDm().getHeight()/3));
		g.drawString("You failed to protect the Estuary",(int) (Window.getDm().getWidth()/2.5),(int) (Window.getDm().getHeight()/3 + 20));
		g.drawString("Returning to menu in: " + count, (int) (Window.getDm().getWidth()/2.5),(int) (Window.getDm().getHeight()/3+40));
	}
	@Override
	//has no bounds
	public Rectangle getBounds() {
		return null;
	}
	
}
