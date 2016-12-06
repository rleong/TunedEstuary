package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Boat extends GameObject {

	private int direction = 1;
	private double initialY = 0;
	private WasteBin trashBin;
	private WasteBin recyclebin;
	private Inventory counter;
	private double boundary1;
	private double boundary2;
	private int rand1;
	boolean waves;
	public static boolean game3;
	public static boolean game1;

	// Random Droppings
	int amount = 0;
	Random rand = new Random();
	int oysterSpawn;
	// Swing Timer
	Timer clock;
	Timer clock2;

	public Boat(double x, double y, ObjectId id, Game game, WasteBin trashBin, WasteBin recyclebin,
			Inventory counter, double boundary1, double boundary2) {
		super(x, y, id, game);
		initialY = y;
		this.trashBin = trashBin;
		this.recyclebin = recyclebin;
		this.counter = counter;
		this.boundary1 = boundary1;
		this.boundary2 = boundary2;
		clock = new Timer(2000, listener);
		clock2 = new Timer(2000, listener2);
		clock.start();
	}
	
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			spawnWaste();
			clock.restart();
		}
	};
	//called by clock2 to spawn oyster
		ActionListener listener2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spawnOyster();
				clock2.restart();
			}
		};

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += direction;
		y += .25 * Math.sin(x / 25);
		rand1 = rand.nextInt(200)+100;//sets new random value
		if(game3 == true){
			clock2.start();
			clock.stop();
			game3 = false;
		}
		collide();
		
		//spawns oyster if the count is equal to the random value
				if(oysterSpawn == rand1){
					spawnOyster();
					oysterSpawn = 0;//resets oyster spawn count
				}
				oysterSpawn++;//increments oyster spawn count
				
	}

	public void collide() {
		if (x + 128 >= boundary2 || x <= boundary1) {
			direction *= -1;
			y = initialY;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int) x, (int) y, 128, 48);
	}

	@Override
	public Rectangle getBounds() {

		return null;
	}
	
	public void removeBoat(){
		clock.stop();
	}

	public void spawnWaste() {
		amount = rand.nextInt(4);
		//System.out.println(amount);
		switch (amount) {
		case 0:
			//Nothing
			break;
		case 1:
			//1 Trash
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 0));
			break;
		case 2:
			//1 Recycle
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 1));
			break;
		case 3:
			//1 Compost
			game.handler.addObject(new Waste(x, y, ObjectId.waste, game, trashBin, recyclebin, counter, 2));
			break;
		default:
			System.out.println("Something went wrong!");
			break;
		}
	}
	//spawns an oyster
		public void spawnOyster() {
			//amount = rand.nextInt(2);
			//System.out.println(amount);
			//switch (amount) {
			//case 0:
				//Nothing
				//break;
			//case 1:
				//1 Oyster
			game.handler.addObject(new Oyster(x, y, ObjectId.oyster, game));
				//break;
		
			//}
		
		}
		public void Game3(){
			game3 = true;
		}

}
