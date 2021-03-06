package object;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class GameTimer extends GameObject {
	private int mintime;//minute time
	private int sectime;//seconds remaining
	private int mstime;//milliseconds remaining	
	private int g;
	public static Timer clock1;//clock
	Critter critter;
	//initializer to 1:30 seconds in game  
	/**
	 * creates the game 3 timer(if you protect the estuary the whole time you win)
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public GameTimer(double x, double y, ObjectId id, Game game, int g) {
		super(x, y, id, game);
		mintime = 1;
		sectime = 30;
		mstime = 0 ;
		this.g = g;
		clock1 = new Timer(100, clockCountDown);//calls listener l1 every millisecond
	}
	
	public void startTimer(){
		clock1.start();
	}
	
	public void setCritter(Critter critter){
		this.critter = critter;
	}
	
	//called by clock1 every millisecond
	ActionListener clockCountDown = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(sectime <= 0 && mintime > 0){//if seconds is 0 and there at least a minute left
				sectime = 59;//set seconds to 59
				mintime--;//subtract 1 from minutes time
			}
			if(mstime <= 0 && sectime > 0){//is there are no milliseconds and at least 1 seconds left
				mstime = 9;//set milliseconds to 9
				sectime--;//subtract 1 from seconds time
			}
			mstime--;//subtract 1 milliseconds
		}
	};

	@Override
	/**
	 * continuously called to check time remaining
	 */
	public void tick(ArrayList<GameObject> object) {
		//checks when to call win condition
		//if timer runs out stop the clock and call win method
		
		if(g == 3){
			if(mintime == 0 && sectime == 0 && mstime == 0){
				clock1.stop();
				//System.out.println("Win");
				win();
			}

		}
		else if(g == 1){
			if(mintime <= 0 && sectime <= 0 && mstime <= 0){
				g=2;
				game.game2=true;
				mstime = -1;
				clock1.stop();
				
				//System.out.println("Win");
//				win();
			}
			
		}
				
	}

	@Override
	//print time remaining
	public void pngSelector(Graphics g) {
		g.setFont(new Font("Times",20,20));
		g.setColor(Color.black);
		g.drawString("Time Remaining: "+mintime + ":"+ sectime + ":" + mstime, critter.getTimeXLocation(), critter.getTimeYLocation());
		g.setFont(new Font("Calibri", Font.BOLD, 16));
	}
	

	@Override
	//no bounds 
	/**
	 * gets this objects bounds
	 * @return Rectangle of null
	 */
	public Rectangle getBounds() {
		
		return null;
	}
	//add gamewin object to handler
	/**
	 * add gamewin object to handler
	 */
	//win condition
	private void win(){
		if(g == 1){
			game.game2 = true;
		}
		else if(g==3)
			game.setGameWinLose(true);
	}
	
}

