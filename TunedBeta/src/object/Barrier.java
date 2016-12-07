package object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
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
public class Barrier extends GameObject{
	public int hp;
	Handler handler;
	
	/**
	 * creates an initial barrier for game 3 to protect the estuary from waves
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Barrier(double x, double y, ObjectId id, Game game) {
		super(x, y, id,game);
		hp = 4;
	}

	@Override
	/**
	 * continuously called to check collision and hp
	 */
	public void tick(LinkedList<GameObject> object) {
		collision(game.handler.object);
		if(this.getHp() == 0){
			game.handler.object.remove(this);
		}
		
	}
	//check for collisions with wave objects
	/**
	 * checks for collisions with different objects(waves)
	 * @param linked list<GameObject>object
	 */
	private void collision(LinkedList<GameObject> object){
		
		GameObject temp;
		for(Iterator<GameObject> itr = object.iterator();itr.hasNext();){
			temp=itr.next();
			if(temp.getId()==ObjectId.waves){
				if(getBounds().intersects(temp.getBounds())){
					hp-=1;
					itr.remove();
				}
			}
		}
	}
	//return hp
	/**
	 * gets hp value
	 * @return int hp value
	 */
	public int getHp(){
		return hp;
	}
	/**
	 * prints the barrier object
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 45, 45);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)(hp*45/4), 2);
		
	}
	/**
	 * gets this objects bounds
	 * @return Rectangle of the objects bounds
	 */
	@Override
	//return sand barrier boundsß
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,45,45);
		
	}
	//test collision
	 /**
	  * for test to access collision
	  * @param test
	  * @return LinkedList<GameObject> test after calling collision
	  */
	public LinkedList<GameObject> testCollision(LinkedList<GameObject> test) {
		collision(test);
		return test;
	}

}

