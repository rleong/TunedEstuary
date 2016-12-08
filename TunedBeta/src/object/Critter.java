package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Critter extends GameObject {

	// Character Main Attributes
	int character;
	int damage;
	Inventory inventory;

	// Character Health
	int health0;
	int health1;
	int health2;
	int flicker = 0;
	boolean invulnerable;
	Timer clockInvincible;

	// Character Physics
	int landCo = 1;
	int waterCo = 2;
	public boolean jump;
	public boolean onLand;
	public boolean inWater;

	// Character Special Power
	final int SPRECHARGE = 300;
	int sp0 = SPRECHARGE;
	int sp1 = SPRECHARGE;
	int sp2 = SPRECHARGE;

	// Location on Screen
	Dimension dm;
	boolean xdir;
	boolean ydir;
	boolean right;

	// Location of Interface
	int healthBarXLocation;
	int healthBarYLocation;
	int nameXLocation;
	int nameYLocation;
	int buildXLocation;
	int buildYLocation;

	// GFX & Animations
	int actionFrameNum = 0;
	int movementFrameNum = 0;
	int movementFrameNum2 = 0;
	int movementFrameNum3 = 0;
	int currentAnimation = 0; // 0 Idle 1 Left Walk 2 Right Walk 3 Swim Up 4
								// Swim Down 5 Swim Left 6 Swim Right 7
								// Attack/Interact
	Images images;

	// Action Booleans
	boolean plantAction = false;
	boolean buildAction = false;

	// Debugging
	boolean debugging = false;
	
	WasteBin wasteBin;
	WasteBin recycleBin;

	/**
	 * Constructor the constructs a critter object. This critter is what the
	 * player controls throughout the game. The critter goes around doing
	 * different events that will help clean nad protect the estuary from harm.
	 * 
	 * @param x
	 *            - x position of critter
	 * @param y
	 *            - y position of critter
	 * @param id
	 *            - object id
	 * @param xdir
	 *            - direction critter is facing (left or right)
	 * @param ydir
	 *            - direction critter is facing (up or down)
	 * @param dm
	 *            - dimension of game
	 * @param inventory
	 *            - inventory containing collected items
	 * @param game
	 *            - game
	 * @param images
	 *            - images of the objects
	 */
	public Critter(double x, double y, ObjectId id, boolean xdir, boolean ydir, Dimension dm, Inventory inventory,
			Game game, Images images, WasteBin wastebin, WasteBin recyclebin) {
		// Basics
		super(x, y, id, game);
		this.xdir = xdir;
		this.ydir = ydir;
		character = 0;
		setDamage();
		health0 = 100;
		health1 = 100;
		health2 = 100;
		this.inventory = inventory;

		// Physics
		jump = false;
		inWater = false;

		// Boundaries of Dimensions
		this.dm = dm;

		// GFX & Animations
		this.images = images;
		
		wasteBin = wastebin;
		recycleBin = recyclebin;
	}

	/**
	 * Method that will change attributes of the critter per call - add x
	 * velocity to x position causing critter to move in the x position - when
	 * not constructing objects allow the character to move - if critter is in a
	 * certain position - if in critter is falling or in water decrease the y
	 * velocity - prevents the character from pressing the key twice to jump
	 * higher - if the critter is hit make the critter flicker - when the
	 * special ability meter is less then full have it recharge - Set health and
	 * special ability meters to follow the character
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {

		// Character Physics
		x += velX;
		if (x < 10) {
			x = 10;
		}
		if (x > dm.getWidth() * 2 - 10) {
			x = dm.getWidth() * 2 - 10;
		}
		if (!game.isPause())
			y += velY;

		if (x > dm.getWidth() * 5 / 6+32) {
			onLand = false;
		}

		if (falling || inWater) {
			velY += gravity;
		}

		if (y < dm.getHeight() * 3 / 5 - 64 && x > dm.getWidth() * 5 / 6 - 32) {
			setY(dm.getHeight() * 3 / 5 - 64);
			setVelY(0);
			jump = true;
		}

		// Character Status
		if (invulnerable) {
			flicker++;
			flicker = flicker % 10;
		} else {
			flicker = 0;
		}

		if (sp0 < SPRECHARGE)
			sp0++;
		if (sp1 < SPRECHARGE)
			sp1++;
		if (sp2 < SPRECHARGE)
			sp2++;

		// Collisions
		collision(game.handler.object);

		// Health Bar & Name Locations
		nameXLocation = (int) (x - (dm.getWidth() * 49 / 100));
		nameYLocation = (int) (y - (dm.getHeight() * 48 / 100));
		healthBarXLocation = (int) (x - (dm.getWidth() * 49 / 100));
		healthBarYLocation = (int) (y - (dm.getHeight() * 47 / 100));
		buildXLocation = (int) (x - (dm.getWidth() * 11 / 100));
		buildYLocation = (int) (y + (dm.getHeight() * 35 / 100));
	}

	/**
	 * Method to display critter, health, and special ability images
	 */
	@Override
	public void render(Graphics g) {

		movementFrameNum = (movementFrameNum + 1) % images.getMoveFrames();
		movementFrameNum2 = (movementFrameNum2 + 1) % images.getSwimFrames();
		movementFrameNum3 = (movementFrameNum3 + 1) % images.getInteractFrames();

		// Character Flickering & Normal
		if (flicker == 0) {
			switch (character) {
			case 0:
				switch (currentAnimation) {
				case 0:
					if (right) {
						g.drawImage(images.getBlueCrabImage(currentAnimation, 0), (int) x - 16, (int) y - 32, game);
					} else {
						g.drawImage(images.getBlueCrabImage(currentAnimation, 1), (int) x - 16, (int) y - 32, game);
					}
					break;
				case 1:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 2:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 3:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 4:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 5:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 6:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 7:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum3), (int) x - 16,
							(int) y - 32, game);
					break;
				case 8:
					if (right) {
						g.drawImage(images.getBlueCrabImage(currentAnimation, 0), (int) x - 16, (int) y - 32, game);
					} else {
						g.drawImage(images.getBlueCrabImage(currentAnimation, 1), (int) x - 16 - 64, (int) y - 32,
								game);
					}
					break;
				}
				break;
			case 1:
				switch (currentAnimation) {
				case 0:
					if (right) {
						g.drawImage(images.getOysterImage(currentAnimation, 0), (int) x - 16, (int) y - 32, game);
					} else {
						g.drawImage(images.getOysterImage(currentAnimation, 1), (int) x - 16, (int) y - 32, game);
					}
					break;
				case 1:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 2:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 3:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 4:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 5:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 6:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum), (int) x - 16, (int) y - 32,
							game);
					break;
				case 7:
					g.drawImage(images.getOysterImage(currentAnimation, movementFrameNum3), (int) x - 16, (int) y - 32,
							game);
					break;
				case 8:
					if (right) {
						g.drawImage(images.getOysterImage(currentAnimation, 0), (int) x - 16, (int) y - 32, game);
					} else {
						g.drawImage(images.getOysterImage(currentAnimation, 1), (int) x - 16, (int) y - 32, game);
					}
					break;
				}
				break;
			case 2:
				switch (currentAnimation) {
				case 0:
					if (right) {
						g.drawImage(images.getHorseshoeCrabImage(currentAnimation, 0), (int) x - 16, (int) y - 32,
								game);
					} else {
						g.drawImage(images.getHorseshoeCrabImage(currentAnimation, 1), (int) x - 16, (int) y - 32,
								game);
					}
					break;
				case 1:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum), (int) x - 16,
							(int) y - 32, game);
					break;
				case 2:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum), (int) x - 16,
							(int) y - 32, game);
					break;
				case 3:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 4:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 5:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 6:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum2), (int) x - 16,
							(int) y - 32, game);
					break;
				case 7:
					g.drawImage(images.getHorseshoeCrabImage(currentAnimation, movementFrameNum3), (int) x - 16,
							(int) y - 32, game);
					break;
				case 8:
					if (right) {
						g.drawImage(images.getHorseshoeCrabImage(currentAnimation, 0), (int) x - 16, (int) y - 32,
								game);
					} else {
						g.drawImage(images.getHorseshoeCrabImage(currentAnimation, 1), (int) x - 16, (int) y - 32,
								game);
					}
					break;
				}
				break;
			}
		} else {
			switch (character) {
			case 0:
				g.setColor(Color.DARK_GRAY);
				break;
			case 1:
				g.setColor(Color.DARK_GRAY);
				break;
			case 2:
				g.setColor(Color.DARK_GRAY);
				break;
			}
		}
		// g.fillRect((int) x, (int) y, 32, 32);

		// Character Inner Bounds
		// Graphics2D g2d = (Graphics2D) g;
		// g.setColor(Color.green);
		// g2d.draw(getBoundsTop());
		// g2d.draw(getBoundsBottom());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsRight());

		// Character Attack Range Bounds
		// g.setColor(Color.gray);
		// g2d.draw(getBounds());

		// Character Name
		g.setColor(Color.WHITE);
		switch (character) {
		case 0:
			g.drawString("Blue Crab", (int) x - 10, (int) y - 18);
			break;
		case 1:
			g.drawString("Eastern Oyster", (int) x - 22, (int) y - 18);
			break;
		case 2:
			g.drawString("Horseshoe Crab", (int) x - 26, (int) y - 18);
			break;
		default:
			g.drawString("Debug Godmode", (int) x - 24, (int) y - 18);
			break;
		}

		// Health Bars
		drawHealthBars(g);

		// Build Options
		drawBuildOptions(g);

		// Animations
		if (plantAction)
			drawWateringPlantAction(g);
		if (buildAction)
			drawBuildingAction(g);

		// Debugging Purposes Only
		if (debugging) {
			g.setColor(Color.red);
			switch (character) {
			case 0:
				g.drawString(health0 + " | " + sp0 + "/" + SPRECHARGE, (int) dm.getWidth() / 2,
						(int) dm.getHeight() / 2);
				break;
			case 1:
				g.drawString(health1 + " | " + sp1 + "/" + SPRECHARGE, (int) dm.getWidth() / 2,
						(int) dm.getHeight() / 2);
				break;
			case 2:
				g.drawString(health2 + " | " + sp2 + "/" + SPRECHARGE, (int) dm.getWidth() / 2,
						(int) dm.getHeight() / 2);
				break;
			}
			// test(g);
		}

	}

	/**
	 * Method that shows the different items your critter can collect
	 * 
	 * @param g
	 *            - graphics variable
	 */
	public void drawBuildOptions(Graphics g) {
		
		
		g.drawImage(images.getMenuBar(), buildXLocation - 32, buildYLocation - 90, game);

	}

	/**
	 * Method that sets a boolean to true or false depending of what object you
	 * are trying to build
	 * 
	 * @param planting
	 *            - boolean if planting or not
	 */
	public void setBuildAnimation(boolean planting) {
		setAnimation(7);
		if (planting)
			plantAction = true;
		else
			buildAction = true;
	}

	/**
	 * Method that sets a boolean to false when finished the animation
	 */
	public void endAnimation() {
		setAnimation(0);
		plantAction = false;
		buildAction = false;
	}

	/**
	 * Method that draws the health bars for each critter
	 * 
	 * @param g
	 *            - graphics variable
	 */
	public void drawHealthBars(Graphics g) {
		// Crab Health & SP Display
		g.setColor(Color.BLACK);
		if (health0 <= 0) {
			g.setColor(Color.RED);
		}
		g.setColor(Color.BLACK);
		if (x > dm.getWidth() * 2.5 / 5) {
			g.drawString("Blue Crab", nameXLocation, nameYLocation);
			g.drawRect(healthBarXLocation, healthBarYLocation, 200, 10);
			g.drawRect(healthBarXLocation, healthBarYLocation + 20, 200, 10);
			g.setColor(Color.RED);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2, (int) (health0 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 20, (int) sp0 * 2 / 3 - 1, 7);
		} else {
			g.drawString("Blue Crab", (int) (dm.getWidth() * 1 / 95), nameYLocation);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation, 200, 10);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation + 20, 200, 10);
			g.setColor(Color.RED);
			g.fillRect((int) (dm.getWidth() * 1 / 95 + 1), healthBarYLocation + 2, (int) (health0 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect((int) (dm.getWidth() * 1 / 95 + 1), healthBarYLocation + 2 + 20, (int) sp0 * 2 / 3 - 1, 7);
		}

		// Oyster Health & SP Display
		g.setColor(Color.BLACK);
		if (health1 <= 0) {
			g.setColor(Color.RED);
		}
		if (x > dm.getWidth() * 2.5 / 5) {
			g.setColor(Color.BLACK);
			g.drawString("Eastern Oyster", nameXLocation, nameYLocation + 70);
			g.drawRect(healthBarXLocation, healthBarYLocation + 70, 200, 10);
			g.drawRect(healthBarXLocation, healthBarYLocation + 90, 200, 10);
			g.setColor(Color.RED);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 72, (int) (health1 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 90, (int) sp1 * 2 / 3 - 1, 7);
		} else {
			g.setColor(Color.BLACK);
			g.drawString("Eastern Oyster", (int) (dm.getWidth() * 1 / 95), nameYLocation + 70);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation + 70, 200, 10);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation + 90, 200, 10);
			g.setColor(Color.RED);
			g.fillRect((int) ((dm.getWidth() * 1 / 95) + 1), healthBarYLocation + 72, (int) (health1 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect((int) ((dm.getWidth() * 1 / 95) + 1), healthBarYLocation + 2 + 90, (int) sp1 * 2 / 3 - 1, 7);
		}
		// Horseshoe Crab Health & SP Display
		g.setColor(Color.BLACK);
		if (health2 <= 0) {
			g.setColor(Color.RED);
		}
		if (x > dm.getWidth() * 2.5 / 5) {
			g.setColor(Color.BLACK);
			g.drawString("Horseshoe Crab", nameXLocation, nameYLocation + 140);
			g.drawRect(healthBarXLocation, healthBarYLocation + 140, 200, 10);
			g.drawRect(healthBarXLocation, healthBarYLocation + 160, 200, 10);
			g.setColor(Color.RED);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 142, (int) (health2 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 160, (int) sp2 * 2 / 3 - 1, 7);
		} else {
			g.setColor(Color.BLACK);
			g.drawString("Horseshoe Crab", (int) (dm.getWidth() * 1 / 95), nameYLocation + 140);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation + 140, 200, 10);
			g.drawRect((int) (dm.getWidth() * 1 / 95), healthBarYLocation + 160, 200, 10);
			g.setColor(Color.RED);
			g.fillRect((int) ((dm.getWidth() * 1 / 95) + 1), healthBarYLocation + 142, (int) (health2 * 2) - 1, 7);
			g.setColor(Color.YELLOW);
			g.fillRect((int) ((dm.getWidth() * 1 / 95) + 1), healthBarYLocation + 2 + 160, (int) sp2 * 2 / 3 - 1, 7);
		}
	}

	/**
	 * Method that draws the animation to the screen by going frame by frame
	 * 
	 * @param g
	 *            - graphics variable
	 */
	public void drawWateringPlantAction(Graphics g) {
		actionFrameNum = (actionFrameNum + 1) % images.getActionFrames();

		if (game.isPause()) {
			g.drawImage(images.getWateringPlant(actionFrameNum), (int) x, (int) y - 42, game);
		}

	}

	/**
	 * Method that draws building animations to the screen
	 * 
	 * @param g
	 *            - graphics varible
	 */
	public void drawBuildingAction(Graphics g) {
		actionFrameNum = (actionFrameNum + 1) % images.getActionFrames();

		if (game.isPause()) {
			g.drawImage(images.getBuildingAction(actionFrameNum), (int) x, (int) y - 42, game);
		}

	}

	/**
	 * Method to set the damage for each critter depending on its type
	 */
	public void setDamage() {
		switch (character) {
		case 0:
			damage = 10;
			break;
		case 1:
			damage = 10;
			break;
		case 2:
			damage = 10;
			break;
		}
	}

	/**
	 * Method to get the damage assigned to the critter
	 * 
	 * @return damage of critter
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Method that has a timer of when the critter can be hit again after being
	 * hit
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			invulnerable = false;
			clockInvincible.stop();
		}
	};

	/**
	 * Method to change the critter you are to one of three different kinds
	 */
	public void changeCharacter() {
		character += 1;
		character = character % 3;
		setDamage();
	}

	/**
	 * Method to set different characters
	 * 
	 * @param character
	 *            - character you want to change to
	 */
	public void setCharacter(int character) {
		this.character = character;
	}

	/**
	 * Crab has a huge swing that increases hitbox, and damage.
	 * 
	 * Oyster has a bubble that sucks in Recyclables and drops them in the bins.
	 * 
	 * Horseshoe Crab can heal other teammates whilst taking damage by itself.
	 */
	// Special Abilities
	public void ability() {
		switch (character) {
		case 0: // CRAB
			if (sp0 == SPRECHARGE) {
				sp0 = 0;
			}
			break;
		case 1: // OYSTER
			if (sp1 == SPRECHARGE) {
				sp1 = 0;
				game.handler.addObject(new Bubble(x - 8, y - 8, ObjectId.bubble, game, right));
			}
			break;
		case 2: // HORSESHOE CRAB
			if (sp2 == SPRECHARGE) {
				sp2 = 0;
			}
			break;
		}
	}

	/**
	 * Method that allows a critter to attack waste objects if the waste is in
	 * the crab attack boundaries
	 * 
	 * @param object
	 *            - list of game objects
	 */
	
	public void setTrash(){
		if(wasteBin.highlight){
			wasteBin.highlight = false;
			recycleBin.highlight = true;
		}
		if(recycleBin.highlight){
			recycleBin.highlight = false;
			wasteBin.highlight = true;
		}
	}
	public void attack(ArrayList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (!waste.checkDeath()) {
					if (waste.canAttack && !waste.getIsTrapped()) {
						waste.health -= damage;
					}
					if (waste.health <= 0) {
						waste.dead();
					}
				}
			}

			// if object is an oyster and in range, collect the oyster
			if (temp.getId() == ObjectId.oyster && temp.getBounds().intersects(this.getBounds())) {
				Oyster oyster = (Oyster) temp;
				game.handler.removeObject(oyster);
				inventory.addOyster();
			}
			// if object is a rope and in range, collect the rope
			if (temp.getId() == ObjectId.rope && temp.getBounds().intersects(this.getBounds())) {
				Rope rope = (Rope) temp;
				game.handler.removeObject(rope);
				inventory.addRope();
			}
			// if object is wood and in range, collect the wood
			if (temp.getId() == ObjectId.wood && temp.getBounds().intersects(this.getBounds())) {
				Wood wood = (Wood) temp;
				game.handler.removeObject(wood);
				inventory.addWood();
			}
			// if object is trash and in range, remove it
			if (temp.getId() == ObjectId.ptrash && temp.getBounds().intersects(this.getBounds())) {
				Trash trash = (Trash) temp;
				game.handler.removeObject(trash);
			}

			if (temp.getId() == ObjectId.waterTree) {
				WaterTree wt = (WaterTree) temp;
				if (wt.canAttack) {
					wt.hp -= damage;
				}
				if (wt.hp <= 0)
					wt.chopDown();
			}

		}
	}

	// Collects Items
	public void collect() {

	}

	/**
	 * Method that allows a critter to plant a plant depending on its type
	 * 
	 * @param type
	 *            - type of plant
	 */
	public void planT(int type) {
		if (game.currency >= 50) {
			game.handler.addObject(new Tree(x, dm.getHeight() * 3 / 5 - 32, ObjectId.tree, type, game, images));
			game.currency -= 50;
		}
	}

	/**
	 * Collision
	 * 
	 * @param object
	 * 
	 *            Collision with land so it doesn't phase through.
	 * 
	 *            If hit by Trash or Recycle, loses 5 health. Compost does not
	 *            hurt critter.
	 * 
	 */
	// Collision
	private void collision(ArrayList<GameObject> object) {
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 31);
					falling = false;
					setVelY(0);
					jump = false;
					onLand = true;
					inWater = false;
				}
			}
			if (temp.getId() == ObjectId.seaLevel) {
				if (getBoundsSelf().intersects(temp.getBounds())) {
					falling = true;
					jump = false;
					inWater = true;
					// System.out.println("I'm in WATER!!!");
				}
			}
			if (temp.getId() == ObjectId.sand) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 31);
					setVelY(0);
				}
			}
			if (temp.getId() == ObjectId.waterTree) {
				WaterTree tree = (WaterTree) temp;
				if (getBounds().intersects(temp.getBounds())) {
					tree.canAttack = true;
				}
				if (!getBounds().intersects(temp.getBounds())) {
					tree.canAttack = false;
				}
			}
			if (temp.getId() == ObjectId.wall) {
				if (getBoundsLeft().intersects(temp.getBounds())) {
					// setX(dm.getWidth() * 5 / 6);
					setVelX(0);
				}
			}
			// if (temp.getId() == ObjectId.seed) {
			// Seed seed = (Seed) temp;
			// if (getBoundsSelf().intersects(temp.getBounds())) {
			// switch (seed.type) {
			// case 0:
			// inventory.addSmallSeed();
			// break;
			// case 1:
			// inventory.addBigSeed();
			// break;
			// }
			// object.remove(temp);
			// }
			// }
			if (temp.getId() == ObjectId.compost1) {
				Compost comp = (Compost) temp;
				if (getBoundsSelf().intersects(temp.getBounds())) {
					game.currency += 25;
					System.out.println(game.currency);
					object.remove(temp);
				}
			}
			if (temp.getId() == ObjectId.compost2) {
				Compost comp = (Compost) temp;
				if (getBoundsSelf().intersects(temp.getBounds())) {
					game.currency += 1000;
					System.out.println(game.currency);
					object.remove(temp);
				}
			}
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (getBounds().intersects(temp.getBounds())) {
					waste.canAttack = true;
				}
				if (!getBounds().intersects(temp.getBounds())) {
					waste.canAttack = false;
				}
				if (getBodyBounds().intersects(temp.getBounds())) {
					if (!invulnerable && !waste.isDead) {
						invulnerable = true;
						clockInvincible = new Timer(3000, listener);
						clockInvincible.start();
						switch (character) {
						case 0:
							health0 -= 5;
							break;
						case 1:
							health1 -= 5;
							break;
						case 2:
							health2 -= 5;
							break;
						}
					}

					// if(invulnerable || waste.getType()==2|| waste.isDead){
					// return;
					// }
					// else{
					// invulnerable = true;
					// clockInvincible = new Timer(3000, listener);
					// clockInvincible.start();
					// switch (character) {
					// case 0:
					// health0 -= 5;
					// break;
					// case 1:
					// health1 -= 5;
					// break;
					// case 2:
					// health2 -= 5;
					// break;
					// }
					// }
					return;

				}
			}
			if (temp.getId() == ObjectId.guardian) {
				GuardianFish guardianfish = (GuardianFish) temp;
				if (getBodyBounds().intersects(temp.getBounds())) {
					if (!invulnerable) {
						invulnerable = true;
						clockInvincible = new Timer(3000, listener);
						clockInvincible.start();
						switch (character) {
						case 0:
							health0 -= 10;
							break;
						case 1:
							health1 -= 10;
							break;
						case 2:
							health2 -= 10;
							break;
						}
					}
				}
			}
		}
	}

	// Debug Mode
	public void setDebug() {
		if (debugging)
			debugging = false;
		else
			debugging = true;
	}

	public void test(Graphics g) {
		g.drawString("haha", (int) dm.getWidth() / 2, (int) dm.getHeight() / 2);
	}

	/**
	 * Returns attack boundaries of the critter object. Used to see if critter
	 * can attack waste
	 */
	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x - 20, (int) y - 20, 72, 72);
	}

	/**
	 * Returns boundaries of the critter object. Used see if critter is
	 * colliding with another game object
	 * 
	 * @return
	 */
	public Rectangle getBoundsSelf() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public Rectangle getBodyBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public Rectangle getBoundsTop() {

		return new Rectangle((int) x + 6, (int) y, 20, 6);
	}

	/**
	 * Returns boundaries of the bottom of the critter object. Used to check if
	 * the critter is touching the floor
	 * 
	 * @return bottom boundary
	 */
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

	/**
	 * Returns boundaries left of the critter object. Stops the critter it runs
	 * into a wall or boundary
	 * 
	 * @return left boundary
	 */
	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y + 6, 6, 20);
	}

	/**
	 * Returns boundaries right of the critter object. Stops the critter it runs
	 * into a wall or boundary
	 * 
	 * @return right boundary
	 */
	public Rectangle getBoundsRight() {

		return new Rectangle((int) x + 26, (int) y + 6, 6, 20);
	}

	/**
	 * Method to set if the critter is moving right
	 */
	public void setRight() {
		right = true;
	}

	/**
	 * Method to set if the critter is moving left
	 */
	public void setLeft() {
		right = false;
	}

	/**
	 * Returns boolean if the critter is in the water or not
	 * 
	 * @return boolean if in water
	 */
	public boolean getInWater() {
		return inWater;
	}

	/**
	 * Method to set animations depending on what the player is doing
	 * 
	 * @param currentAnimation
	 *            - action of the user
	 */
	public void setAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	/**
	 * Method to constructs a gabion if you have the needed materials in your
	 * inventory
	 */
	public void plantGabion() {
		// builds gabion if inventory method returns true, and you are on the
		// sand behind initial barrier
		if (inventory.buildGabion() && this.x < dm.getWidth() / 2 + dm.getWidth() / 4) {
			game.handler.addObject(new Gabion(x, dm.getHeight() * 3 / 5 - 32, ObjectId.gabion, game, images));
			inventory.removeOysters();
			inventory.removeRope();
			inventory.removeWood();
		} else {
			System.out.println("Failed");
		}

	}

}
