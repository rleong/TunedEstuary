package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
	int currentAnimation = 0; // 0 Idle 1 Left Walk 2 Right Walk 3 Swim Up 4
								// Swim Down 5 Swim Left 6 Swim Right 7
								// Attack/Interact
	Images images;

	// Action Booleans
	boolean plantAction = false;
	boolean buildAction = false;

	// Debugging
	boolean debugging = false;

	public Critter(double x, double y, ObjectId id, boolean xdir, boolean ydir, Dimension dm,
			Inventory inventory, Game game, Images images) {
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
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

//		if (inWater) {
//			if (velX == 0) {
//				currentAnimation = 0;
//			} else if (velY > 0) {
//				currentAnimation = 3;
//			} else if (velY < 0) {
//				currentAnimation = 4;
//			} else {
//				currentAnimation = 0;
//			}
//		} else {
//			if (velX == 0) {
//				currentAnimation = 0;
//			} else if (velX < 0) {
//				currentAnimation = 1;
//			} else if (velX > 0) {
//				currentAnimation = 2;
//			}  else {
//				currentAnimation = 0;
//			}
//		} 

		// Character Physics
		x += velX;
		if (!game.isPause())
			y += velY;

		if (x > dm.getWidth() * 5 / 6) {
			onLand = false;
		}

		if (falling || inWater) {
			velY += gravity;
		}

		if (y < dm.getHeight() * 3 / 5 - 96 && x > dm.getWidth() * 5 / 6 - 32) {
			setY(dm.getHeight() * 3 / 5 - 96d);
			setVelY(0);
			jump = true;
		}

		// Character Status
		if (invulnerable) {
			flicker++;
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

	@Override
	public void render(Graphics g) {

		movementFrameNum = (movementFrameNum + 1) % images.getMoveFrames();
		movementFrameNum2 = (movementFrameNum2 + 1) % images.getSwimFrames();

		// Character Flickering & Normal
		if (flicker == 0 || flicker % 10 == 0) {
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
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16, (int) y - 32,
							game);
					break;
				case 4:
					g.drawImage(images.getBlueCrabImage(currentAnimation, movementFrameNum2), (int) x - 16, (int) y - 32,
							game);
					break;
				}
				break;
			case 1:
				g.setColor(Color.DARK_GRAY);
				break;
			case 2:
				g.setColor(Color.lightGray);
				break;
			}
		} else {
			switch (character) {
			case 0:
				if (right) {
					g.drawImage(images.getBlueCrabImage(currentAnimation, 2), (int) x - 16, (int) y - 32, game);
				} else {
					g.drawImage(images.getBlueCrabImage(currentAnimation, 3), (int) x - 16, (int) y - 32, game);
				}
			case 1:
				g.setColor(Color.DARK_GRAY);
				break;
			case 2:
				g.setColor(Color.lightGray);
				break;
			}
		}
		// g.fillRect((int) x, (int) y, 32, 32);

		// Character Inner Bounds
		Graphics2D g2d = (Graphics2D) g;
		// g.setColor(Color.green);
		// g2d.draw(getBoundsTop());
		// g2d.draw(getBoundsBottom());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsRight());

		// Character Attack Range Bounds
		g.setColor(Color.gray);
		g2d.draw(getBounds());

		// Character Name
		g.setColor(Color.WHITE);
		switch (character) {
		case 0:
			g.drawString("Blue Crab", (int) x - 10, (int) y - 10);
			break;
		case 1:
			g.drawString("Eastern Oyster", (int) x - 22, (int) y - 10);
			break;
		case 2:
			g.drawString("Horseshoe Crab", (int) x - 26, (int) y - 10);
			break;
		default:
			g.drawString("Debug Godmode", (int) x - 24, (int) y - 10);
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

	public void drawBuildOptions(Graphics g) {

		g.drawImage(images.getMenuBar(), buildXLocation - 32, buildYLocation - 26, game);

		g.drawImage(images.getGabionBuildIcon(), buildXLocation, buildYLocation, game);
		// g.fillRect(buildXLocation, buildYLocation, 64, 64);

		g.setColor(Color.BLUE);
		g.fillRect(buildXLocation + 64 + 32, buildYLocation, 64, 64);

		g.setColor(Color.RED);
		g.fillRect(buildXLocation + 64 + 32 + 64 + 32, buildYLocation, 64, 64);

		g.setColor(Color.GRAY);
		g.fillRect(buildXLocation + 64 + 32 + 64 + 32 + 64 + 32, buildYLocation, 64, 64);

		g.setColor(Color.GREEN);
		g.fillRect(buildXLocation + 64 + 32 + 64 + 32 + 64 + 32 + 64 + 32, buildYLocation, 64, 64);

	}

	public void setBuildAnimation(boolean planting) {
		if (planting)
			plantAction = true;
		else
			buildAction = true;
	}

	public void endAnimation() {
		plantAction = false;
		buildAction = false;
	}

	public void drawHealthBars(Graphics g) {
		// Crab Health & SP Display
		g.setColor(Color.BLACK);
		if (health0 <= 0) {
			g.setColor(Color.RED);
		}
		g.setColor(Color.BLACK);
		g.drawString("Blue Crab", nameXLocation, nameYLocation);
		g.drawRect(healthBarXLocation, healthBarYLocation, 200, 10);
		g.drawRect(healthBarXLocation, healthBarYLocation + 20, 200, 10);
		g.setColor(Color.RED);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2, (int) (health0 * 2) - 1, 7);
		g.setColor(Color.YELLOW);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 20, (int) sp0 * 2 / 3 - 1, 7);

		// Oyster Health & SP Display
		g.setColor(Color.BLACK);
		if (health1 <= 0) {
			g.setColor(Color.RED);
		}
		g.setColor(Color.BLACK);
		g.drawString("Eastern Oyster", nameXLocation, nameYLocation + 70);
		g.drawRect(healthBarXLocation, healthBarYLocation + 70, 200, 10);
		g.drawRect(healthBarXLocation, healthBarYLocation + 90, 200, 10);
		g.setColor(Color.RED);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 72, (int) (health1 * 2) - 1, 7);
		g.setColor(Color.YELLOW);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 90, (int) sp1 * 2 / 3 - 1, 7);

		// Horseshoe Crab Health & SP Display
		g.setColor(Color.BLACK);
		if (health2 <= 0) {
			g.setColor(Color.RED);
		}
		g.setColor(Color.BLACK);
		g.drawString("Horseshoe Crab", nameXLocation, nameYLocation + 140);
		g.drawRect(healthBarXLocation, healthBarYLocation + 140, 200, 10);
		g.drawRect(healthBarXLocation, healthBarYLocation + 160, 200, 10);
		g.setColor(Color.RED);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 142, (int) (health2 * 2) - 1, 7);
		g.setColor(Color.YELLOW);
		g.fillRect(healthBarXLocation + 1, healthBarYLocation + 2 + 160, (int) sp2 * 2 / 3 - 1, 7);
	}

	// Animations & GFX
	public void drawWateringPlantAction(Graphics g) {
		actionFrameNum = (actionFrameNum + 1) % images.getActionFrames();

		if (game.isPause()) {
			g.drawImage(images.getWateringPlant(actionFrameNum), (int) x, (int) y - 42, game);
		}

	}

	public void drawBuildingAction(Graphics g) {
		actionFrameNum = (actionFrameNum + 1) % images.getActionFrames();

		if (game.isPause()) {
			g.drawImage(images.getBuildingAction(actionFrameNum), (int) x, (int) y - 42, game);
		}

	}

	// Set Character Damage
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

	public int getDamage() {
		return this.damage;
	}

	// Timer for invincibility of Character after getting hurt
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			invulnerable = false;
			clockInvincible.stop();
		}
	};

	// Sets & Changes Characters
	public void changeCharacter() {
		character += 1;
		character = character % 3;
		setDamage();
	}

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

	// Attacking an enemy
	public void attack(LinkedList<GameObject> object) {
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
			
			//if object is an oyster and in range, collect the oyster  
			else if(temp.getId() == ObjectId.oyster && temp.getBounds().intersects(this.getBounds())){
				Oyster oyster = (Oyster)temp;
				game.handler.removeObject(oyster);
				inventory.addOyster();
			}
			//if object is a rope and in range, collect the rope
			else if(temp.getId() == ObjectId.rope && temp.getBounds().intersects(this.getBounds())){
				Rope rope = (Rope)temp;
				game.handler.removeObject(rope);
				inventory.addRope();
			}
			//if object is wood and in range, collect the wood
			else if(temp.getId() == ObjectId.wood && temp.getBounds().intersects(this.getBounds())){
				Wood wood = (Wood)temp;
				game.handler.removeObject(wood);
				inventory.addWood();
			}
			//if object is trash and in range, remove it
			else if(temp.getId() == ObjectId.ptrash && temp.getBounds().intersects(this.getBounds())){
				Trash trash = (Trash)temp;
				game.handler.removeObject(trash);
			}
			/*
			 * if (temp.getId() == ObjectId.waterTree) { WaterTree wt =
			 * (WaterTree) temp; if (wt.canAttack) { wt.hp -= damage; } if
			 * (wt.hp <= 0) wt.dead(); }
			 */
		}
	}

	// Collects Items
	public void collect() {

	}

	// Plants plants
	public void planT(int type) {
		game.handler.addObject(new Tree(x, dm.getHeight() * 3 / 5 - 32, ObjectId.tree, type, game));
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
	private void collision(LinkedList<GameObject> object) {
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
					falling=true;
					jump = false;
					inWater = true;
					//System.out.println("I'm in WATER!!!");
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
					switch (comp.type) {
					case 0:
						// inventory.addSmallSeed();
						break;
					case 1:
						// inventory.addBigSeed();
						break;
					}
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
					if (waste.canAttack && !invulnerable && waste.getType() != 2 && !waste.checkDeath()) {
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

	// Hitboxes and Collision boxes
	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x - 16, (int) y - 16, 64, 64);
	}

	public Rectangle getBoundsSelf() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public Rectangle getBodyBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public Rectangle getBoundsTop() {

		return new Rectangle((int) x + 6, (int) y, 20, 6);
	}

	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y + 6, 6, 20);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int) x + 26, (int) y + 6, 6, 20);
	}

	public void setRight() {
		right = true;
	}

	public void setLeft() {
		right = false;
	}
	
	public boolean getInWater(){
		return inWater;
	}
	
	public void setAnimation(int currentAnimation){
		this.currentAnimation = currentAnimation;
	}
	public void plantGabion() {
		//builds gabion if inventory method returns true, and you are on the sand behind initial barrier
				if(inventory.buildGabion() && this.x < dm.getWidth()/2 + dm.getWidth()/4){
					game.handler.addObject(new Gabion((int)x,(int)y-12,ObjectId.gabion,game));
					inventory.removeOysters();
					inventory.removeRope();
					inventory.removeWood();
				}
				else{
					System.out.println("Failed");
				}
				
	}


}
