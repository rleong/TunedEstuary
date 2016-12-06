package object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Game3Timer extends GameObject {
	private int mintime;//minute time
	private int sectime;//seconds remaining
	private int mstime;//milliseconds remaining
	public static Timer clock1;//clock
	//initializer to 1:30 seconds in game
	public Game3Timer(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		mintime = 1;
		sectime = 30;
		mstime = 0 ;
		clock1 = new Timer(100, l1);//calls listener l1 every millisecond
		clock1.start();
	}
	//called by clock1 every millisecond
	ActionListener l1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(sectime == 0 && mintime > 0){//if seconds is 0 and there at least a minute left
				sectime = 59;//set seconds to 59
				mintime--;//subtract 1 from minutes time
			}
			if(mstime == 0 && sectime > 0){//is there are no milliseconds and at least 1 seconds left
				mstime = 9;//set milliseconds to 9
				sectime--;//subtract 1 from seconds time
			}
			mstime--;//subtract 1 milliseconds
		}
	};

	@Override
	public void tick(LinkedList<GameObject> object) {
		//if timer runs out stop the clock and call win method
		if(mintime == 0 && sectime == 0 && mstime == 0){
			clock1.stop();
			System.out.println("Win");
			win();
		}
		//if 20 seconds are left, set game to hard mode
		if(mintime == 0 && sectime == 20){
			Waves.hard = true;
			WaveClock.hard = true;
		}
				
	}

	@Override
	//print time remaining
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Time Remaining: "+mintime + ":"+ sectime + ":" + mstime, (int)x, (int)y);

	}
	

	@Override
	//no bounds
	public Rectangle getBounds() {
		
		return null;
	}
	//radd gamewin object to handler
	public void win(){
		game.handler.addObject(new GameWin(1,1,ObjectId.gamewin,game));
	}
	
}

