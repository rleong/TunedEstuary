package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Boat extends GameObject {

	// Attributes
	private int direction = 1;
	private double initialY = 0;
	private WasteBin trashBin;
	private WasteBin recyclebin;
	private Inventory counter;
	private double boundary1;
	private double boundary2;
	private int rand1;
	private int count;
	boolean waves;
	public boolean game3;
	public static boolean game1;
	private int stage = 0;

	// Random Droppings
	int amount = 0;
	Random rand = new Random();
	int oysterSpawn;
	// Swing Timer
	Timer clock;
	Timer clock2;

	Images images;

	/**
	 * Constructor that constructs a boat which will drop trash,recycle, and
	 * compost on the habitat.
	 * 
	 * @param x
	 *            - x position of the boat
	 * @param y
	 *            - y position of the boat
	 * @param id
	 *            - object id of object
	 * @param game
	 *            - game object
	 * @param trashBin
	 *            - trash bin object for trash
	 * @param recyclebin
	 *            - recycle bin object for recycle
	 * @param counter
	 *            - compost counter
	 * @param boundary1
	 *            - first boundary where the boat can't go past
	 * @param boundary2
	 *            - second boundary where the boat can't go past
	 * @param g3
	 *            - boolean if game 3
	 * @param images
	 *            - image of the boat
	 */
	public Boat(double x, double y, ObjectId id, Game game, WasteBin trashBin, WasteBin recyclebin, Inventory counter,
			double boundary1, double boundary2, boolean g3, Images images) {
		super(x, y, id, game);
		initialY = y;
		this.trashBin = trashBin;
		this.recyclebin = recyclebin;
		this.counter = counter;
		this.boundary1 = boundary1;
		this.boundary2 = boundary2;
		this.game3 = g3;
		count = 1;
		clock = new Timer(2000, listener);
		clock2 = new Timer(2000, listener2);
		clock.start();
		this.images = images;
	}

	/**
	 * Method that spawns waste at certain time intervals for game 1
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			spawnWaste();
			clock.restart();
		}
	};
	
	/**
	 * Method that spawns oysters at certain time intervals for game 3
	 */
	ActionListener listener2 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			spawnOyster();
			clock2.restart();
		}
	};

	/**
	 * Method that changes variables of the boat per call.
	 * 	- Adds direction to the x position which moves the boat in he x direction
	 * 	- Adds fractions to the y direction to make the boat move up and down like in reality
	 * 	- Starts drops starts clock 2 if you are on game 3
	 * 	- spawns oysters 
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		x += direction;
		y += .25 * Math.sin(x / 25);
		rand1 = rand.nextInt(200) + 100;// sets new random value
		if (game3 == true && count == 1) {
			clock2.start();
			clock.stop();
			count = 0;
		}
		if (game3 == true) {
			if (oysterSpawn == rand1) {
				spawnOyster();
				oysterSpawn = -300;// resets oyster spawn count
			}
			oysterSpawn++;
		}
		collide();
		
		if( direction > 0)
			stage = 1;
		else
			stage = 0;

		// spawns oyster if the count is equal to the random value
		// increments oyster spawn count

	}

	/**
	 * Method that check if the boat is colliding with the bounds
	 * 
	 */
	public void collide() {
		if (x + 128 >= boundary2 || x <= boundary1) {
			direction *= -1;
			y = initialY;
		}
	}

	/**
	 * Method to display images of the boat 
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(images.getTrashBoat(stage), (int) x, (int) y, game);
		g.drawString("Trash Boat", (int) x + 24, (int) y - 24);
	}

	/**
	 * Returns the boundary of the boat
	 */
	@Override
	public Rectangle getBounds() {

		return null;
	}

	/**
	 * Method to remove the boat from the game
	 */
	public void removeBoat() {
		clock.stop();
	}

	/**
	 * Method that will create trash , recycle, or compost into the game
	 */
	public void spawnWaste() {
		amount = rand.nextInt(4);
		// System.out.println(amount);
		if(game.game1){
		switch (amount) {
		case 0:
			// Nothing
			break;
		case 1:
			// 1 Trash
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 0, images));
			break;
		case 2:
			// 1 Recycle
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 1, images));
			break;
		case 3:
			// 1 Compost
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 2, images));
			break;
		default:
			System.out.println("Something went wrong!");
			break;
		}
		}
	}

	/**
	 * Method that creates oysters from the boat into the game
	 */
	public void spawnOyster() {
		// amount = rand.nextInt(2);
		// System.out.println(amount);
		// switch (amount) {
		// case 0:
		// Nothing
		// break;
		// case 1:
		// 1 Oyster
		game.handler.addObject(new Oyster(x, y, ObjectId.oyster, game));
		// break;

		// }

	}

	/**
	 * Method that makes game 3 equal to true
	 */
	public void Game3() {
		game3 = true;
	}

}
