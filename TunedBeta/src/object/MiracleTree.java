package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class MiracleTree extends GameObject{
	//Attributes
	public int hp;
	private long timer0=System.currentTimeMillis();
	private long timer1=System.currentTimeMillis();
	private long timer2=System.currentTimeMillis();
	public boolean lock;
	int stage = 0;
	Images images;

	/**
	 * Constructor that constructs a miracle tree object. This object can be used to stop the boss run-off in game 2
	 * 
	 * @param x - x position of miracle plant 
	 * @param y - y position of miracle plant 
	 * @param id - object id 
	 * @param game - game object 
	 * @param images - image of miracle plant
	 */
	public MiracleTree(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		hp=20;
		this.images = images;
	}
	
	/**
	 * Method that sets timers 
	 */
	public void setTimer(){
		timer0=System.currentTimeMillis();
		timer1=System.currentTimeMillis();
		timer2=System.currentTimeMillis();
	}
	/**
	 * Method that gets timers 
	 */
	public long getTimer0(){
		return timer0;
	}
	/**
	 * Method that gets timers 
	 */
	public long getTimer1(){
		return timer1;
	}
	/**
	 * Method that gets timers 
	 */
	public long getTimer2(){
		return timer2;
	}

	/**
	 * Method that drops compost and remove the miracle plant from the game
	 */
	public void dead(){
//		dropSeed();
		dropCompost();
		game.handler.object.remove(this);
		
	}
	/**
	 * Method that drops compost 
	 */
	public void dropCompost() {
	// TODO Auto-generated method stub
		game.handler.addObject(new Compost(x, y, ObjectId.compost2, game, 1, images));
		
	}
//	public void dropSeed(){
//		game.handler.addObject(new Seed(x,y,ObjectId.seed, game, type, images));
//		
//	}
	

	/**
	 * Method that changes variables per call
	 * 	- Add x and y velocity to x position to make the miracle plant move int the x and y position
	 * 	- if time 0 is greater 5 seconds increment stage to show decay
	 * 	- if timer1 is greater than 20 seconds remove miracle plant form the game
	 * 	- if not in bounds timer reset
	 * 	- if timer 2 greater than 15 seconds then drop compost  
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		
		x+=velX;
		y+=velY;
//		if(hp<=0){
//			velY+=gravity*10;
//		}
		
		if (System.currentTimeMillis() - timer0 > 5000) {
			timer0+=5000;
			if(stage < 2)
				stage++;
		}
		if(System.currentTimeMillis()-timer1>20000){
			game.handler.object.remove(this);
		}
		if(!lock){
			timer2=System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-timer2>15000){
			timer2+=15000;
			dead();
			
		}
	}


	/**
	 * Method that displays the miracle plant image
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(images.getWaterStarGrass(stage),(int)x-16, (int)y+32, game);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 5);
		g.setColor(Color.BLACK);
		g.drawString("Water Stargrass", (int) x - 24, (int) y+108); 
	}

	/**
	 * Method that returns the bounds of the miracle plant
	 * Used to tell if the plant collides with other objects 
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 96);
	}
}
