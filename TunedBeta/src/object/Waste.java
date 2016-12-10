package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Waste extends GameObject {
	// Attributes
	int damage = 5;
	double health = 20;
	boolean canAttack = false;
	boolean isDead = false;
	boolean isTrapped = false;
	int type = 0;

	Inventory counter;

	WasteBin trashBin;
	WasteBin recycleBin;
	Bubble bubble;
	double boundaries = 0;

	Images images;
	int wasteName = 0;
	Random random = new Random();

	/**
	 * Constructor that constructs a waste object that will be either trash,
	 * recycle, or compost. These objects will continuous be dropped on the
	 * habitat in game 1 and will damage the habitat unless removed by the
	 * player.
	 * 
	 * @param x
	 *            - x position of waste
	 * @param y
	 *            - y position of waste
	 * @param id
	 *            - object id of game object
	 * @param game
	 *            - game object
	 * @param trashBin
	 *            - trash bin object
	 * @param recycleBin
	 *            - recycle bin object
	 * @param counter
	 *            - number of trash
	 * @param type
	 *            - type of waste
	 * @param images
	 *            - image of waste
	 */
	public Waste(double x, double y, ObjectId id, Game game, WasteBin trashBin, WasteBin recycleBin, Inventory counter,
			int type, Images images) {
		super(x, y, id, game);
		this.trashBin = trashBin;
		this.recycleBin = recycleBin;
		this.counter = counter;
		this.type = type;
		setVelY(1);
		this.images = images;
		if (type == 2)
			wasteName = random.nextInt(2);
		else
			wasteName = random.nextInt(4);
		// System.out.println(type + " " + wasteName);
	}

	/**
	 * Method to get the damage of the waste
	 * 
	 * @return damage of waste
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Method to set the damage of the waste
	 * 
	 * @param damage
	 *            - damage waste will do
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Method that will make the waste go into the trash or recycle bin when the
	 * health is equal to 0
	 */
	public void dead() {
		switch (type) {
		case 0: // TRASH
		case 1:
			setVelY(-5);
			break;
		case 2: // COMPOST
			counter.setRegularCompost(5);
			break;
		default: // ERROR
			System.out.println("SOMETHING WENT WRONG YO");
			break;
		}
		canAttack = false;
		isDead = true;

	}

	/**
	 * Returns boolean whether the waste is dead or not
	 * 
	 * @return boolean of alive status
	 */
	public boolean checkDeath() {
		return isDead;
	}

	/**
	 * Method that changes variable of waste per call. - if the waste is not in
	 * a bubble by the oyster continue to move the x and y positions - else the
	 * waste is trapped in the bubble - if the waste is not dead call the
	 * collision function - else make the trash go to the waste bin of the
	 * waste's type
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(isDead && !isTrapped){
			switch (type) {
			case 0:
				setVelX((trashBin.getX() - x) / 50);
				if(y < trashBin.getY())
					setVelY(5);
				else
					setVelY(-5);
				break;
			case 1:
				setVelX((recycleBin.getX() - x) / 50);
				if(y < recycleBin.getY())
					setVelY(5);
				else
					setVelY(-5);
				break;
			}
		}
		
		if (isTrapped) {
			bubbleCollide();
			if (bubble.getPop()) {
				isTrapped = false;
				// x=recycleBin.getX();
				health = 0;
				dead();
			}
		}

		if (!isDead)
			collision(object);

	}

	/**
	 * Returns whether or not the waste is stuck in the bubble
	 * 
	 * @return boolean if trapped
	 */
	public boolean getIsTrapped() {
		return isTrapped;
	}

	/**
	 * Method to set if the recycle is trapped or not
	 * 
	 * @param check
	 *            - boolean you want to set
	 */
	public void setIsTrapped(boolean check) {
		if (type == 1) {
			isTrapped = check;
		}
	}

	/**
	 * Method to display the waste images depending on the type of waste
	 */
	@Override
	public void render(Graphics g) {

		// Waste Type
		g.drawImage(images.getWaste(type, wasteName), (int) x, (int) y, game);

		// Waste Health
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y - 2, (int) ((health / 20) * 32), 2);

		// Waste Name
		g.setColor(Color.WHITE);
		switch (type) {
		case 0: // TRASH
			g.drawString("Trash", (int) x, (int) y - 10);
			break;
		case 1: // RECYCLE
			g.drawString("Recycle", (int) x - 4, (int) y - 10);
			break;
		case 2: // COMPOST
			g.drawString("Compost", (int) x - 8, (int) y - 10);
			break;
		default: // ERROR
			g.drawString("Error", (int) x, (int) y - 10);
			break;
		}

		g.drawString(getWasteName(type), (int) x - 4, (int) y - 22);
	}

	/**
	 * Method that get the name of the waste depending on the type
	 * 
	 * @param wasteType
	 *            - type of waste
	 * @return name of the waste
	 */
	public String getWasteName(int wasteType) {

		switch (wasteType) {
		case 0:
			switch (wasteName) {
			case 0:
				return "Plastic Bag";
			case 1:
				return "Battery";
			case 2:
				return "Waxed Carton";
			case 3:
				return "Styrofoam";
			}
			break;
		case 1:
			switch (wasteName) {
			case 0:
				return "Metal";
			case 1:
				return "Paper";
			case 2:
				return "Plastic Bottle";
			case 3:
				return "Glass";
			}
			break;
		case 2:
			switch (wasteName) {
			case 0:
			case 1:
				return "Regular";
			case 2:
				return "Fertile";
			}
			break;
		}
		return "Null";
	}

	/**
	 * Returns the boundaries of the waste object. Used to check if waste is
	 * colliding with other game objects
	 */
	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * Method to get the type of waste
	 * 
	 * @return waste type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Method to make the recycle move along with the bubble if trapped
	 */
	public void bubbleCollide() {
		x = bubble.getX() - 2;
		y += 1 * Math.sin(x / 25);
	}

	/**
	 * Method that checks if waste is colliding with certain objects and
	 * performs a certain action when so - If colliding with sand: set velocity
	 * at y to 0 so it can't move past - If colliding with bubble: set trapped
	 * to true;
	 * 
	 * @param object
	 *            - list of game objects
	 */
	private void collision(ArrayList<GameObject> object) {
		GameObject temp;
		for (int i = 0; i < game.handler.object.size(); i++) {
			temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.sand) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
			if (temp.getId() == ObjectId.bubble) {
				if (getBounds().intersects(temp.getBounds())) {
					if (type == 1) {
						if (!isTrapped) {
							isTrapped = true;
							bubble = (Bubble) temp;
							boundaries = bubble.getBoundaries();
						}
					}
				}
			}
		}
	}

	/**
	 * Method that sets the boundaries where the waste can't go
	 * 
	 * @param boundaries
	 *            - boundary to be
	 */
	public void setBoundaries(double boundaries) {
		this.boundaries = boundaries;
	}

	/**
	 * Returns bounds of the bottom of the waste. Used for floor collision
	 * 
	 * @return - boundaries on the bottom of the waste
	 */
	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

}
