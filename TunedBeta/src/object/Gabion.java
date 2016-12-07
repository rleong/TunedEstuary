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

public class Gabion extends GameObject{
	public int hp;
	Handler handler;
	public Gabion(double x, double y, ObjectId id,Game game) {
		super(x, y, id,game);
		hp = 3;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		//if the object is a gabion with no health remove it
		if(this.getHp() == 0)
			game.handler.removeObject(this);
		
	}
	//check for collision with all game objects
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
	public int getHp(){
		return hp;
	}
	
	@Override
	//print gabion object
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 45, 45);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)(hp*45/3), 2);
		
	}

	@Override
	//get gabion object bounds
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,45,45);
		
	}
	public LinkedList<GameObject> testCollision(LinkedList<GameObject>test){
		collision(test);
		return test;
	}

}

