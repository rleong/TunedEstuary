package object;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Estuary extends GameObject{
	//health value
	private int health = 5;
	public Estuary(double x, double y, ObjectId id, Game game, Dimension dm) {
		super(x, y, id,game);
	}

	@Override
	//consistantly called
	public void tick(LinkedList<GameObject> object) {
		collision(object);//check for collisions
		if(health<=0){//call lose() if health is 0
			lose();
		}
	}

	@Override
	// print estuary object
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, (int)60, 60);
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y-8, (int) ((health / 5) * 60), 6);
	}
	//adds gameover object to handler, ends game
	private void lose(){
		game.handler.addObject(new GameOver(1,1,ObjectId.gameover,game));
	}
	//check for collisions
	private void collision(LinkedList<GameObject> object) {
		GameObject temp;
		for(int i = 0;i<object.size();i++){
			temp = object.get(i);
			if (temp.getId() == ObjectId.waves) {
				if(getBounds().intersects(temp.getBounds())){
					health--;
					object.remove(temp);
				}
			}
		}
	}
	public int getHp(){
		return health;
	}

	@Override
	//get object boundaries
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y , 60, 60);
	}
	//test collision
	public LinkedList<GameObject> testCollision(LinkedList<GameObject>test){
		collision(test);
		return test;
	}

}