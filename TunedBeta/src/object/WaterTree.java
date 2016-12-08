package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class WaterTree extends GameObject {

	// Attributes
	public boolean canAttack;
	public int hp;
	int type;
	private long timer0 = System.currentTimeMillis();
	private long timer1 = System.currentTimeMillis();
	private long timer2 = System.currentTimeMillis();
	int stage = 0;
	Images images;

	/**
	 * Method to create plants that grow under water. These plants allow you to
	 * get compost that will allow you to plant trees to stop run-off in game 2.
	 * These tree decay over time dropping compost and when they fully die they
	 * will drop a seed which will plant a new tree and repeat the cycle
	 * 
	 * @param x
	 *            - x position of water tree
	 * @param y
	 *            - y position of water tree
	 * @param id
	 *            - object id to be read by the handler
	 * @param type
	 *            - type of tree
	 * @param game
	 *            - game its going to be in
	 * @param dm
	 *            - dimensions of the game
	 * @param images
	 *            - image of water tree
	 */
	public WaterTree(double x, double y, ObjectId id, int type, Game game, Images images) {
		super(x, y, id, game);
		hp = 20;
		canAttack = true;
		this.type = type;
		this.images = images;
	}
	// public void change(){
	// type+=1;
	// type=type%2;
	// }

	/**
	 * Method that drops three compost if the player attacks the tree and calls
	 * the dead function
	 */
	public void chopDown() {
		game.handler.addObject(new Compost(x - 64, y - 128, ObjectId.compost1, game, type, images));
		game.handler.addObject(new Compost(x, y - 128, ObjectId.compost1, game, type, images));
		game.handler.addObject(new Compost(x + 64, y - 128, ObjectId.compost1, game, type, images));
		game.handler.object.remove(this);
		return;
	}

	/**
	 * Method that returns if the player can attack the water tree
	 * 
	 * @return canAttack boolean
	 */
	public boolean getAttack() {
		return canAttack;
	}

	/**
	 * Method drops a seed to grow a new water tree when dead. Also removes
	 * the water tree from the game and makes it so you cannot attack the water tree
	 */
	public void dead() {
		dropSeed();
		canAttack = false;
		game.handler.object.remove(this);

	}

	/**
	 * Method that drops compost which will help plant trees
	 */
	public void dropCompost() {
		// TODO Auto-generated method stub
		game.handler.addObject(new Compost(x, y, ObjectId.compost1, game, type, images));

	}

	/**
	 * Method that drops a seed 
	 */
	public void dropSeed() {
		game.handler.addObject(new Seed(x, y, ObjectId.seed, game, type, images));

	}

	/**
	 * Method that will changes multiple variables per call.
	 * 	- Add velocity x to the x position causing the object to move in the x direction
	 *  - Add velocity y to the y position causing the object to move in the y direction
	 *  - Every 10 seconds the water tree will drop a compost
	 *  - at 23 seconds the water tree will die dropping a seed
	 *  
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {

		x += velX;
		y += velY;
		// if(hp<=0){
		// velY+=gravity*10;
		// }

		if (System.currentTimeMillis() - timer0 > 10000) {
			timer0 += 10000;
			dropCompost();
			stage++;
		}

		if (System.currentTimeMillis() - timer2 > 23000) {
			timer2 += 23000;

			dead();
			stage = 0;
		}
	}

	/**
	 * Method that displays images of the water tree depending on the type
	 */
	@Override
	public void render(Graphics g) {
		switch (type) {
		case 0:
			g.drawImage(images.getHornwort(stage), (int) x - 16, (int) y + 32, game);
			g.setColor(Color.BLACK);
			g.drawString("Hornwort", (int) x - 8, (int) y + 108);
			break;
		case 1:
			g.setColor(Color.white);
			g.fillRect((int) x, (int) y, 32, 32);
			g.setColor(Color.BLACK);
			g.fillRect((int) x, (int) y + 32, 32, 64);

			break;
		}

		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 32, 5);
	}

	/**
	 * Returns the boundary of the water tree. Used to check if it is colliding with other objects
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 96);
	}

}
