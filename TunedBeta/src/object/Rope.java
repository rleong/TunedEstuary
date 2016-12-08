package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
/**
 * 
 * @author justin said
 *
 */
public class Rope extends GameObject {
	boolean falling = true;
	/**
	 * creates a rope object spawned from person for the critter to collect
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Rope(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		setVelY(1);
	}

	@Override
	//called continuously to update x and y and to call collision
	/**
	 * continuously called to check collision and update position
	 */
	public void tick(ArrayList<GameObject> object) {
			x += velX;
			y += velY;

		collision(game.handler.object);
		if (falling) {
			y += 1;
		}

	}

	@Override
	//print rope object
	/**
	 * prints rope object
	 */
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 32, 32);
		g.setColor(Color.WHITE);
		g.drawString("Rope", (int)x-4, (int)y-10);

	}
	//check for collisions with other objects
	/**
	 * checks for collision with other objects, then stops it's y value from incrementing
	 * @param ArrayList<GameObject> object with all game objects
	 */
	private void collision(ArrayList<GameObject> object) {
		//iterate through objects list
//		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		for (int i=0;i<object.size();i++) {
			temp = object.get(i);
			//if it collides with the land surface, then stop falling
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
		}
	}
	

	@Override
	//returns object bounds
	/**
	 * gets object bounds
	 * @return new Rectangle with bounds for collision
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x - 16, (int) y - 16, 16, 16);
	}
	/**
	 * gets object bounds falling
	 * @return new Rectangle with falling bounds for collision
	 */
	//returns falling bounds
	
	public Rectangle getBoundsFall() {
		return new Rectangle((int) x - 16, (int) y - 16, 32, 32);
	}
	//returns bottom bounds
	/**
	 * gets object bottom bounds
	 * @return new Rectangle with bottombounds for collision
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}
	 /**
	  * for test to access collision
	  * @param test
	  * @return ArrayList<GameObject> test after calling collision
	  */
	public ArrayList<GameObject> testCollision(ArrayList<GameObject>test){
		collision(test);
		return test;
	}
}


