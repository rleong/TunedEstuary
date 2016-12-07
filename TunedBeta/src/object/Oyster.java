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

public class Oyster extends GameObject {

	boolean falling = true;
	//initializer
	public Oyster(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		setVelY(1);
	}

	@Override
	//called continuously to update x and y and to call collision
	public void tick(LinkedList<GameObject> object) {
			x += velX;
			y += velY;

		collision(game.handler.object);
		if (falling) {
			y += 1;
		}
	}

	@Override
	//print oyster object
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, 32, 32);
		g.setColor(Color.WHITE);
		g.drawString("Oyster", (int)x-4, (int)y-10);

	}
	//check for collisions with other objects
	private void collision(LinkedList<GameObject> object) {
		//iterate through objects list
		for (GameObject temp:object) {
			//if it collides with sand, then stop falling
			if (temp.getId() == ObjectId.sand) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
		}
	}
	

	@Override
	//returns object bounds
	public Rectangle getBounds() {
		return new Rectangle((int) x - 16, (int) y - 16, 16, 16);
	}
	//returns falling bounds
	public Rectangle getBoundsFall() {
		return new Rectangle((int) x - 16, (int) y - 16, 32, 32);
	}
	//returns bottom bounds
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}
}