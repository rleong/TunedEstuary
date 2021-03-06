package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Runoff extends GameObject {
	//Attribute
	public int type;
	Dimension dm;
	Game game;
	public boolean lock=false;
	Images images;

	/**
	 * Constructor that creates run-off in game 2. The run-off will be trying to collide
	 * with the water, polluting the water if it touches it. Each run-off has different attributes
	 * depending on its type.
	 * 
	 * @param x - x position of run-off
	 * @param y - y position of run-off
	 * @param dm - dimensions on the game
	 * @param id - object id to be read by the handler
	 * @param type - type of run-off
	 * @param game - game it is in
	 */
	public Runoff(double x, double y, Dimension dm, ObjectId id, int type, Game game, Images images) {
		super(x, y-64, id, game);
		if (type == 0 || type == 1) {
			setVelX(1.2);
		} else if (type == 3) {
			setVelX(.5);
		}
		else if(type==2){
			setVelX(2);
		}
		this.type = type;
		this.dm = dm;
		this.game = game;
		this.images = images;
	}

	/**
	 * Method that changes multiple variables per call
	 * 	- Adds gravity to the y velocity causing the object to fall
	 * 	- Adds x velocity to the x position causing it to move in the x direction
	 * 	- Adds y velocity to the y position causing it to move in the y direction
	 * 	- Calls the collision function
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		if (falling)
			velY += gravity;
		x += velX;
		y += velY;
		collision(game.handler.objectsList);
	}

	/**
	 * Method that displays images of run-off depending on the run-off type 
	 */
	@Override
	public void pngSelector(Graphics g) {
		g.setColor(Color.WHITE);
		switch (type) {
		case 0:
			g.drawImage(images.getUButton(), (int) x, (int) y-32, game);
			g.drawImage(images.getWaste(3, 0), (int) x, (int) y, game);
			g.drawString("Chemical Runoff", (int) x-22, (int) y+48);
			break;
		case 1:
			g.drawImage(images.getIButton(), (int) x, (int) y-32, game);
			g.drawImage(images.getWaste(3, 1), (int) x, (int) y, game);
			g.drawString("Sediment Runoff", (int) x-22, (int) y+48);
			break;
		case 2:
			g.drawImage(images.getOButton(), (int) x, (int) y-32, game);
			g.drawImage(images.getWaste(3, 2), (int) x, (int) y, game);
			g.drawString("Solid Waste Runoff", (int) x-36, (int) y+48);
			break;
		case 3:
			g.drawImage(images.getPButton(), (int) x, (int) y-100, game);
			g.drawImage(images.getBossWaste(), (int) x, (int) y, game);
			g.drawString("Mixed Runoff", (int) x, (int) y+148);
			break;
		}

	}

	/**
	 * Returns boundaries of the run-off depending on its type.
	 * Used to check if the object is colliding with another object
	 */
	@Override
	public Rectangle getBounds() {
		if (type == 3) {
			return new Rectangle((int) x, (int) y, 75, 132);
		} else {
			return new Rectangle((int) x, (int) y, 32, 32);
		}
	}

	/**
	 * Method that checks if certain object intersect run-off and do 
	 * certain actions when so
	 * 	- When run-off touches the land, make run-off stop falling and stop moving in the y position
	 * 	- When run-off touches the water, remove the waste from the game and increase the water count
	 * 
	 * @param object - list of game objects
	 */
	private void collision(ArrayList<GameObject> object) {
		for (int i = 0; i < game.handler.objectsList.size(); i++) {
			GameObject temp = game.handler.objectsList.get(i);
			if (temp.getId() == ObjectId.landSurface) {
				if (getBounds().intersects(temp.getBounds())) {
					falling = false;
					if(type==1){
						setVelY(-3);
						falling=true;
					}
					else{
						setVelY(0);
					}
					if (type == 3) {
						setY(temp.getY() - 100);
					} else {
						setY(temp.getY() - 32);
					}
				} else {
					falling = true;
				}
			}
			if (temp.getId() == ObjectId.sand) {
				if (getBounds().intersects(temp.getBounds())) {
					falling = false;
					
					setY(temp.getY() - 32);
				} else {
					falling = true;
				}
			}
			if (temp.getId() == ObjectId.seaLevel) {

				if (getBounds().intersects(temp.getBounds())) {
					if(!lock){
					game.waterCondition += 1;
					if(game.waterCondition >= 5){
						game.waterCondition = 5;
					}
					game.nWaste-=1.005;
					object.remove(this);
					lock=true;
					}
					
				}
			}
		}

	}
}
