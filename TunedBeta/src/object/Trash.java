package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;

/**
 * 
 * @author justin said
 *
 */

public class Trash extends GameObject {
	boolean falling = true;
	/**
	 * creates a trash object spawned from person for the critter to collect
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Trash(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		setVelY(1);//set falling
	}

	@Override
	//called continuously to update x and y loc
	/**
	 * continuously called to check collision and update position
	 */
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		collision(game.handler.object);
		if (falling) {
			y += 1;
		}
	}

	@Override
	//prints trash object
	/**
	 * prints trash object
	 */
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int) x, (int) y, 32, 32);
		g.setColor(Color.WHITE);
		g.drawString("Trash", (int)x-4, (int)y-10);

	}
	//check for collision between trash and objects
	/**
	 * continuously called to check collision with the land surface, and if it does stop incrementing the y value
	 */
	private void collision(LinkedList<GameObject> object) {
		//iterate through game objects
		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		//if object collides with the land surface, stop it from traveling down
		for (; itr.hasNext();) {
			temp = itr.next();
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
		}
	}
	@Override
	//returns trash bounds
	/**
	 * gets object bounds
	 * @return new Rectangle with bounds for collision
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x - 16, (int) y - 16, 16, 16);
	}
	//gets falling trash bounds
	/**
	 * gets object bounds falling
	 * @return new Rectangle with falling bounds for collision
	 */
	public Rectangle getBoundsFall() {
		return new Rectangle((int) x - 16, (int) y - 16, 32, 32);
	}
	//gets trash bottom bounds
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
	  * @return LinkedList<GameObject> test after calling collision
	  */
	public LinkedList<GameObject> testCollision(LinkedList<GameObject>test){
		collision(test);
		return test;
	}
	
}