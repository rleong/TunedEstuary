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

import javax.swing.Timer;

import framework.KeyInput;
import framework.ObjectId;
import gfx.Images;
import object.Boat;
import object.Inventory;
import object.Critter;
import object.GuardianFish;
import object.Habitat;
import object.RofFactory;
import object.SchoolFish;
import object.WasteBin;
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
	private Thread thread;
	public int count = 0;
	boolean pause = false;
	Timer clock;
	public int nRof = 0;
	public int g2stage = 0;
	public int trees = 0;
	// Object
	Handler handler;
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
	// Game Conditions
	boolean gameover = false;
	boolean game1 = true;
	boolean game2 = false;
	boolean game3 = false;
	Timer gameTime;

	// Game2 var

	public double nWaste = 0;
	public double nW1 = 0;
	public double nW2 = 0;
	public double nW3 = 0;
	public double nW4 = 0;

	// Camera
	Camera cam;

	private void init() {
		// Default Objects
		handler = new Handler(this);
		cam = new Camera(0, 0, dm);
		// 0 1 2 3 4
		// Width, Height, Water Start Width, Water Bottom Height, Water Surface
		// Height
		handler.creatSurface(dm);
		dmBoundaries = handler.spawnLocations(dm);
		factory = new RofFactory(0, dm.getHeight() * 3 / 5 - 32, ObjectId.RofFactory, handler, this);
		school = new SchoolFish(dm.getWidth(), dm.getHeight()*4/5, ObjectId.school, handler, this);
		school2 = new SchoolFish(dm.getWidth()*1.2, dm.getHeight()*3.6/5, ObjectId.school, handler, this);
		school3 = new SchoolFish(dm.getWidth()*1.5, dm.getHeight()*3.2/5, ObjectId.school, handler, this);
		gfish= new GuardianFish(dm.getWidth()*1.517, dm.getHeight(), ObjectId.guardian, handler, this);
		
		trashBin = new WasteBin(dm.getWidth() * .84 - 128, dm.getHeight() * 3 / 5 - 64, ObjectId.wasteBin, handler, 0, images, this);
		recyclebin = new WasteBin(dm.getWidth() * .84 - 192, dm.getHeight() * 3 / 5 - 64, ObjectId.wasteBin, handler, 1, images, this);
		
		inventory = new Inventory(10, 10, ObjectId.inventory, handler, dm);
		critter = new Critter(600, dm.getHeight() * 3 / 5 - 32, ObjectId.critter, handler, true, true, dm, inventory,
				this, images);

		// Game 1 Objects
		handler.addObject(new Boat(dmBoundaries[2], dmBoundaries[4] - 40, ObjectId.boat, handler, trashBin, recyclebin,
				inventory, dmBoundaries[2], dm.getWidth() * 3 / 2));
		handler.addObject(trashBin);
		handler.addObject(recyclebin);
		handler.addObject(inventory);
		handler.addObject(new Habitat(dmBoundaries[2] + 16, dmBoundaries[1] - 96 - 64, ObjectId.habitat, handler, dm));

		// Critter
		handler.addObject(critter);
		inventory.setCritter(critter);
		this.addKeyListener(new KeyInput(handler, this));

		// Game Timer
		gameTime = new Timer(5000, gameTimeListener);
		gameTime.start();
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

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

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.critter) {
				cam.tick(handler.object.get(i));
			}
		}
	}

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
		for (int j = 192; j < getHeight(); j+= 64){
			for (int i = 0; i < getWidth() - 64; i += 64) {
				g.drawImage(images.getSkyTiles(3), i, j, this);
			}
		}

		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		//
		g.dispose();
		bs.show();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getnRof() {
		return nRof;
	}

	public void setnRof(int nRof) {
		this.nRof = nRof;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(int duration) {
		clock = new Timer(duration, listener);
		pause = true;
		critter.setVelX(0);
		critter.setVelY(0);
		clock.start();
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pause = false;
			critter.endAnimation();
			clock.stop();
		}
	};

	ActionListener gameTimeListener = new ActionListener() {
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
				game3 = true;

				// Remove Game 2 Objects

				// Game 3 Objects

			} else if (game3) {
				game3 = false;
				gameTime.stop();

			} else {
				gameTime.stop();

			}
			gameTime.restart();
		}
	};

	public static void main(String args[]) {
		Game game = new Game();
		new Window(dm, "Estuary", game);
	}

}
