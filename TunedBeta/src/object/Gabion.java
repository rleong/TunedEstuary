package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
/**
 * 
 * @author justin said
 *
 */
public class Gabion extends GameObject{
	public int hp;
	Handler handler;
	/**
	 * creates a gabion for game 3 to protect the estuary from waves
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Gabion(double x, double y, ObjectId id,Game game) {
		super(x, y, id,game);
		hp = 3;
	}

	@Override
	/**
	 * continuously called to check collision and hp
	 */
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		//if the object is a gabion with no health remove it
		if(this.getHp() == 0)
			game.handler.removeObject(this);
		
	}
	//check for collision with all game objects
	/**
	 * checks for collisions with different objects(waves)
	 * @param linked list<GameObject>object
	 */
	private void collision(LinkedList<GameObject> object){
		
		GameObject temp;
		//iterate through object list
		for(Iterator<GameObject> itr = object.iterator();itr.hasNext();){
			temp=itr.next();
			//if the object is a wave and it collides with this, remove one health point and the wave
			if(temp.getId()==ObjectId.waves){
				if(getBounds().intersects(temp.getBounds())){
					hp-=1;
					itr.remove();
				}
			}
		}
	}
	//return hp value
	/**
	 * gets hp value
	 * @return int hp value
	 */
	public int getHp(){
		return hp;
	}
	
	@Override
	//print gabion object
	/**
	 * prints the gabion object at that location
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 45, 45);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)(hp*45/3), 2);
		
	}

	@Override
	//get gabion object bounds
	/**
	 * returns rectangle with object bounds for collision
	 * @return rectangle with object bounds
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,45,45);
		
	}
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

