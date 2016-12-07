package object;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
import window.Window;

public class Person extends GameObject {
	int speed = 1;
	int grav = 2;
	boolean colliding = false;
	int direction;
	int dropcount;
	Random rand = new Random();

	public Person(double x, double y, ObjectId id, Game game, int direction) {
		super(x, y, id, game);
		velX = speed;
		velY = grav;
		this.direction = direction;
		this.dropcount = -100;
	}
	//function for the person object to drop either a rope, wood, or trash randomly by adding it to the handler
	//uses static methods in game class
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
	public void tick(LinkedList<GameObject> object) {
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
	public void render(Graphics g) {
		//g.drawImage(birdImg, (int)x, (int)y, 32, 32, null);
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
		//g.fillRect((int) x, (int) y, (int) ((getHealth() / 20) * 32), 2);
	}
	//checks for collisions between this object and any other object in handler
	private void collision(LinkedList<GameObject> object) {
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
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}
	public Rectangle getBoundsWall() {

		return new Rectangle((int) x-4, (int) y-100, 15, 200);
	}

	
	//returns person bottom bounds
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}
	//test collision
	public LinkedList<GameObject> testCollision(LinkedList<GameObject>test){
		collision(test);
		return test;
	}
	public int getDirection(){
		return direction;
	}

}
