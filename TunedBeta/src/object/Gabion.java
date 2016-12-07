package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Gabion extends GameObject{
	public int hp;
	Handler handler;
	int stage = 0;
	Images images;
	Timer buildStage;
	
	public Gabion(double x, double y, ObjectId id,Game game, Images images) {
		super(x, y, id,game);
		this.images=images;
		hp = 3;
		buildStage = new Timer(1000, listener);
		buildStage.start();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		//if the object is a gabion with no health remove it
		if(this.getHp() == 0)
			game.handler.removeObject(this);
		
	}
	
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(stage == 2)
				buildStage.stop();
			else{
				buildStage.restart();
				stage++;
			}			
		}
	};
	
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
		g.drawImage(images.getGabion(stage), (int) x - 16, (int) y - 32, game);
		g.setColor(Color.red);
		g.fillRect((int)x - 16, (int)y - 42, (int)(hp*64/3), 2);
		
	}

	@Override
	//get gabion object bounds
	public Rectangle getBounds() {
		return new Rectangle((int)x - 16,(int)y - 32,64,64);
		
	}
	public LinkedList<GameObject> testCollision(LinkedList<GameObject>test){
		collision(test);
		return test;
	}

}

