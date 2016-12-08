package object;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
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
public class Person extends GameObject {
	int speed = 1;
	int grav = 2;
	boolean colliding = false;
	int direction;
	int dropcount;
	Random rand = new Random();
	/**
	 * creates a person object to spawn rope, trash, or wood objects for critter to collect
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Person(double x, double y, ObjectId id, Game game, int direction) {
		super(x, y, id, game);
		velX = speed;
		velY = grav;
		this.direction = direction;
		this.dropcount = -100;
	}
	//function for the person object to drop either a rope, wood, or trash randomly by adding it to the handler
	//uses static methods in game class
	/**
	 * adds an item to the handler randomly as a drop item
	 */
	public void drop() {
			int drop = rand.nextInt(4);
			if(drop == 0){
				game.dropWood((int)x, (int)y);
			}
			else if(drop == 1){
				game.dropRope((int)x, (int)y);
			}
			else{
				game.dropTrash((int)x, (int)y);
			}
		}


	@Override
	//continuously called, switch x direction based off of collision with barrier
	/**
	 * continuously called to check collision and update position
	 */
	public void tick(ArrayList<GameObject> object) {
		y += velY;
		//increment x position based on direction
		if (colliding) {
			if (direction == 0) {
				x += velX;
			} 
			if (direction == 1){
				x -= velX;
			}
			
		}
		//if object reaches a certain point on the screen, switch it's direction
		if(x >= (game.dm.getWidth()/2 + game.dm.getWidth()/4)){
			if (direction == 0) {
				direction = 1;
			} 
			else if (direction == 1){
				direction = 0;
			}
		}

		if (x <= 0 || x >= 1400 - 32) {
			velX *= -1;
		}
		//if dropcount is 600, call drop() to drop a random item, and reset count
		if (dropcount == 700){
			drop();
			dropcount = 0;
		}
		collision(object);
		dropcount++;//increment dropcount each pass
	}

	@Override
	/**
	 * prints person object
	 */
	public void render(Graphics g) {
		//g.drawImage(birdImg, (int)x, (int)y, 32, 32, null);
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
		//g.fillRect((int) x, (int) y, (int) ((getHealth() / 20) * 32), 2);
	}
	//checks for collisions between this object and any other object in handler
	/**
	 * checks for collision with other objects, then stops it's y value from incrementing
	 * @param ArrayList<GameObject> object with all game objects
	 */
	private void collision(ArrayList<GameObject> object) {
		//iterate through object list
		Iterator<GameObject> it = object.iterator();
		for(;it.hasNext();){
			GameObject temp = it.next();
			//if it collides with the land surface, stop falling by setting VelY to 0
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
					colliding = true;
				}
			}
			if(temp.getId() == ObjectId.wall){
				if(getBoundsWall().intersects(temp.getBounds())){
					if (direction == 0) {
						direction = 1;
					} 
					else if (direction == 1){
						direction =0;
					}
				}
			}
		}
	}
	@Override
	/**
	 * gets object bounds
	 * @return new Rectangle with bounds for collision
	 */
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}
	/**
	 * gets object bounds falling
	 * @return new Rectangle with falling bounds for collision
	 */
	public Rectangle getBoundsWall() {

		return new Rectangle((int) x-4, (int) y-100, 15, 200);
	}

	
	//returns person bottom bounds
	/**
	 * gets object bottom bounds
	 * @return new Rectangle with bottombounds for collision
	 */
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}
	//test collision
	/**
	  * for test to access collision
	  * @param test
	  * @return ArrayList<GameObject> test after calling collision
	  */
	public ArrayList<GameObject> testCollision(ArrayList<GameObject>test){
		collision(test);
		return test;
	}
	/**
	 * gets the specific person object's direction
	 * @return int direction(1 for left, 0 for right)
	 */
	public int getDirection(){
		return direction;
	}

}
