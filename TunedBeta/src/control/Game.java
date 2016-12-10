package control;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.Timer;

import framework.GameObject;
import framework.KeyInput;
import framework.ObjectId;
import gfx.Images;
import object.Barrier;
import object.Boat;
import object.Critter;
import object.Estuary;
import object.Game3Instructions;
import object.Game3Timer;
import object.GameOver;
import object.GameWin;
import object.GuardianFish;
import object.Habitat;
import object.Inventory;
import object.Person;
import object.RofFactory;
import object.Rope;
import object.SchoolFish;
import object.Trash;
import object.Tutorial;
import object.WasteBin;
import object.WaveClock;
import object.Waves;
import object.Wood;
import window.Camera;
import window.Handler;
import window.Window;

public class Game extends Canvas implements Runnable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6771508490304664935L;

	static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Dimension dm = new Dimension(tk.getScreenSize());
	private boolean running = false;
	public static long startTime;
	private Thread thread;
	public int count = 0;
	boolean pause = false;
	Timer clock;
	public int nRof = 0;
	public int g2stage = 0;
	Barrier barrier;
	private Random rand = new Random();
	// Object
	public Handler handler;
	public Handler handler2;
	RofFactory factory;
	WasteBin trashBin;
	WasteBin recyclebin;
	Inventory inventory;
	double[] dmBoundaries;
	Critter critter;
	Images images = new Images();
	SchoolFish school;
	SchoolFish school2;
	SchoolFish school3;
	GuardianFish gfish;
	Game3Timer g1t;
	Habitat habitat;
	int gmeval = 1;
	Game3Instructions game3inst;
	// Game Conditions
	public static boolean gameover = false;
	public static boolean game1 = false;
	public static boolean game2 = false;
	public static boolean game3 = false;
	Timer gameTime;
	Timer game3Time;
	public static Boat test;
	// Game2 var

	public double nWaste = 0;
	public double nW1 = 0;
	public double nW2 = 0;
	public double nW3 = 0;
	public double nW4 = 0;

	public int currency = 25;
	KeyInput k;
	// Camera
	Camera cam;
	
	//tutorial
	public boolean tutorial= true;
	public Tutorial tutor;
	boolean lock=false;

	/**
	 * Method that initializes the objects that are going to be in our game
	 */
	private void init() {
		// Default Objects
		startTime = System.currentTimeMillis();
		handler = new Handler(this);
		handler.setImages(images);
		handler2 = new Handler(this);
		dmBoundaries = handler.spawnLocations(dm);

		cam = new Camera(0, 0, dm);
		g1t = new Game3Timer((int) (dm.getWidth() - dm.getWidth() / 4), (int) (dm.getHeight() / 8), ObjectId.game3timer,
				this, 1);
		habitat = new Habitat(dmBoundaries[2] + 16, dmBoundaries[1] - 96 - 64, ObjectId.habitat, this, dm, images);
		barrier = new Barrier(dm.getWidth() * 5 / 6 - 64, dm.getHeight() * 3 / 5 - 64, ObjectId.barrier, this, images);

		// 0 1 2 3 4
		// Width, Height, Water Start Width, Water Bottom Height, Water Surface
		// Height
		handler.creatSurface(dm);
		factory = new RofFactory(0, dm.getHeight() * 3 / 5 - 32, ObjectId.RofFactory, this, images);
		school = new SchoolFish(dm.getWidth(), dm.getHeight() * 4 / 5, ObjectId.school, this, images);
		school2 = new SchoolFish(dm.getWidth() * 1.2, dm.getHeight() * 3.6 / 5, ObjectId.school, this, images);
		school3 = new SchoolFish(dm.getWidth() * 1.5, dm.getHeight() * 3.2 / 5, ObjectId.school, this, images);
		gfish = new GuardianFish(dm.getWidth() * 1.517, dm.getHeight(), ObjectId.guardian, this, images);

		trashBin = new WasteBin(dm.getWidth() * .84 - 128, dm.getHeight() * 3 / 5 - 64, ObjectId.wasteBin, 0, images,
				this, true);
		recyclebin = new WasteBin(dm.getWidth() * .84 - 192, dm.getHeight() * 3 / 5 - 64, ObjectId.wasteBin, 1, images,
				this, false);

		inventory = new Inventory(10, 10, ObjectId.inventory, this, dm);
		critter = new Critter(32, dm.getHeight() * 3 / 5 - 32, ObjectId.critter, true, true, dm, inventory, this,
				images, trashBin, recyclebin);

		// Game 1 Objects 
		handler.addObject(new Boat(dmBoundaries[2], dmBoundaries[4] - 60, ObjectId.boat, this, trashBin, recyclebin,
				inventory, dmBoundaries[2], dm.getWidth() * 3 / 2, false, images));
		handler.addObject(trashBin);
		handler.addObject(recyclebin);
		handler.addObject(inventory);
		handler.addObject(habitat);
		handler.addObject(g1t);

		// Critter
		handler2.addObject(critter);
		inventory.setCritter(critter);
		k = new KeyInput(handler, handler2, this);
		this.addKeyListener(new KeyInput(handler, handler2, this));
		tutor = new Tutorial(0, 0, ObjectId.tutorial, this, trashBin, recyclebin, inventory, images, critter);
		handler.addObject(tutor);
		
		
		game3inst = new Game3Instructions(1, 1, ObjectId.instr3, this);

	}

	/**
	 * Method that starts the game by running a new thread
	 */
	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Method that calls the tick(changes variables) and render(uploads images)
	 * for objects in the game 60 times every second
	 */
	@Override
	public void run() {

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		// int temp = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				
				// System.out.println(temp++);
				tick();
				
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;

			}

		}

	}

	/**
	 * Method that changes variables in our game per call.
	 */
	private void tick() {
		handler.tick();
		handler2.tick();
		for (int i = 0; i < handler2.object.size(); i++) {
			if (handler2.object.get(i).getId() == ObjectId.critter) {
				cam.tick(handler2.object.get(i));
			}
		}
		// Lose conditions
		for (int j = 0; j < handler.object.size(); j++) {
			if (handler.object.get(j).getId() == ObjectId.habitat) {
				Habitat temp = (Habitat) handler.object.get(j);
				if (temp.getHealth() <= 0.0) {
					gameover = true;
					handler.addObject(new GameOver(1, 1, ObjectId.gameover, this));
				}

			}
			if (handler.object.get(j).getId() == ObjectId.school) {
				SchoolFish temp = (SchoolFish) handler.object.get(j);
				if (temp.isDead() == true) {
					gameover = true;
					handler.addObject(new GameOver(1, 1, ObjectId.gameover, this));
				}

			}
		}

		if (game2 == true && gmeval == 1) {
			gmeval = 2;
			handler.removeGame1();

			// Game 2 Objects
			handler.addObject(factory);
			handler.addObject(school);
			handler.addObject(school2);
			handler.addObject(school3);
			handler.addObject(gfish);
			factory.iniTree();

		}
		// check game 2 win condition
		if (g2stage > 7) {
			handler.addObject(new GameWin(1,1,ObjectId.gamewin,this));
			game3 = true;
		}
		// start game 3
		if (game3 == true && gmeval == 2) {
			gmeval++;
			handler.removeGame2();
			handler.addObject(game3inst);
			game3Time = new Timer(5000, game3TimeListener);
			game3Time.start();

			// Game 3 Objects
			// spawns 3 people
			game3Create();

		}

		if (gameover == true) {
			handler.removeObject(game3inst);
			this.removeKeyListener(k);
		}

	}

	/**
	 * Method that displays images of our game objects to the screen
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// Draw here

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < getWidth(); i += 64) {
			g.drawImage(images.getSkyTiles(0), i, 0, this);
		}
		for (int i = 0; i < getWidth(); i += 64) {
			g.drawImage(images.getSkyTiles(1), i, 64, this);
		}
		for (int i = 0; i < getWidth(); i += 64) {
			g.drawImage(images.getSkyTiles(2), i, 128, this);
		}
		for (int j = 192; j < getHeight(); j += 64) {
			for (int i = 0; i < getWidth(); i += 64) {
				g.drawImage(images.getSkyTiles(3), i, j, this);
			}
		}
		
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		handler2.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());
		//
		g.dispose();
		bs.show();
	}

	/**
	 * Method that gets the count
	 * 
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Method that sets the count
	 * 
	 * @param count
	 *            - count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Method that gets the number of run-off
	 * 
	 * @return number of run-off
	 */
	public int getnRof() {
		return nRof;
	}

	/**
	 * Method that set the number of run-off
	 * 
	 * @param nRof
	 *            - number of run-off to be
	 */
	public void setnRof(int nRof) {
		this.nRof = nRof;
	}

	/**
	 * Method that returns if pause is true or false
	 * 
	 * @return boolean if paused
	 */
	public boolean isPause() {
		return pause;
	}

	/**
	 * Method that adds wave objects to the handler and spawns them
	 */
	public void summonWave() {
		System.out.println("Wave");
		handler.addObject(
				new Waves(dm.getWidth() * 3, dm.getHeight() / 2 + (dm.getHeight() / 15), ObjectId.waves, this));
		handler.addObject(
				new Waves(dm.getWidth() * 3 + 50, dm.getHeight() / 2 + (dm.getHeight() / 15), ObjectId.waves, this));
	}

	/**
	 * Method that drops rope at a certain x and y position
	 * 
	 * @param x - x position to be 
	 * @param y - y position to be
	 */
	public void dropRope(int x, int y) {
		handler.addObject(new Rope(x, y, ObjectId.rope, this));
	}

	/**
	 * Method that drops wood from the person class
	 * @param x
	 * @param y
	 */
	public void dropWood(int x, int y) {
		handler.addObject(new Wood(x, y, ObjectId.wood, this));
	}

	/**
	 * Method that drops trash from the person class
	 * @param x
	 * @param y
	 */
	public void dropTrash(int x, int y) {
		handler.addObject(new Trash(x, y, ObjectId.ptrash, this));
	}

	/**
	 * Method that stops the player from moving for a set time if they are constructing something
	 * 
	 * @param duration - time player is stopped 
	 */
	public void setPause(int duration) {
		clock = new Timer(duration, listener);
		pause = true;
		critter.setVelX(0);
		critter.setVelY(0);
		clock.start();
	}

	/**
	 * Method that does a certain action at a certain time
	 *	- make pause false
	 *	- end animation
	 *	- stop the clock 
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pause = false;
			critter.endAnimation();
			clock.stop();
		}
	};

	Game3Timer g3t = new Game3Timer((int) (dm.getWidth() - dm.getWidth() / 4), (int) (dm.getHeight() / 8),
			ObjectId.game3timer, this, 3);
	Estuary estuary = new Estuary(0, dm.getHeight() * 3 / 5 - 60, ObjectId.estuary, this, dm);
	WaveClock wclock = new WaveClock(1, 1, ObjectId.wclock, this);

	/*
	 * Boat b2 = new Boat(dmBoundaries[2], dmBoundaries[4] - 40, ObjectId.boat,
	 * this, trashBin, recyclebin, inventory, dmBoundaries[2], dm.getWidth() * 3
	 * / 2, true); Person p1 = new Person(rand.nextInt(900), (int)(dm.height/2 +
	 * (dm.getHeight()/15)), ObjectId.person,this, rand.nextInt(2)); Person p2 =
	 * new Person(rand.nextInt(900), (int)(dm.height/2 + (dm.getHeight()/15)),
	 * ObjectId.person,this, rand.nextInt(2)); Person p3 = new
	 * Person(rand.nextInt(900), (int)(dm.height/2 + (dm.getHeight()/15)),
	 * ObjectId.person,this, rand.nextInt(2));
	 */
	public void game3Create() {
		handler.addObject(g3t);
		handler.addObject(estuary);
		// handler.addObject(game3inst);
		handler.addObject(barrier);
		handler.addObject(wclock);
		handler.addObject(new Boat(dmBoundaries[2], dmBoundaries[4] - 40, ObjectId.boat, this, trashBin, recyclebin,
				inventory, dmBoundaries[2], dm.getWidth() * 3 / 2, true, images));
		for (int i = 0; i < 3; i++) {
			handler.addObject(new Person(rand.nextInt(900), (int) (dm.height / 2 + (dm.getHeight() / 15)),
					ObjectId.person, this, rand.nextInt(2)));
		}
	}

	/**
	 * Method that does executes code when certain actions are performed.
	 * This method is used to change our games when a certain condition 
	 * happens or end when game when a certain condition is hit 
	 */
	ActionListener game3TimeListener = new ActionListener() {
		private boolean tutorial;

		@Override

		public void actionPerformed(ActionEvent e) {
			
			if (game1) {
				game1 = false;
				game2 = true;

				// Remove Game 1 Objects
				handler.removeGame1();
				

				// Game 2 Objects
				handler.addObject(factory);
				handler.addObject(school);
				handler.addObject(school2);
				handler.addObject(school3);
				handler.addObject(gfish);
				factory.iniTree();

			} else if (game2) {
				game2 = false;

				// Remove Game 2 Objects
				handler.removeGame2();

				// add game 3

//			} else if (game3) {
//				game3 = false;
//				gameTime.stop();

			} else {
				gameTime.stop();

			}
//			gameTime.restart();
//			game3Create();
		}
	};

	/**
	 * Main class that starts the game
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Game game = new Game();
		new Window(dm, "Estuary", game);
	}

}
