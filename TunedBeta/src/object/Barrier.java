package object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Barrier extends GameObject{
	public int hp;
	Handler handler;
	public Barrier(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id,handler);
		hp = 4;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		
	}
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
			if(temp.getId() == ObjectId.barrier){
				if(hp == 0)
					itr.remove();	
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 45, 45);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)(hp*45/4), 2);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,45,45);
		
	}

}

