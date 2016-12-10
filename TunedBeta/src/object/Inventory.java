package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

//
public class Inventory extends GameObject {

	// Attributes
	private int regularCompost = 0;
	private int fertileCompost = 0;
	private int oysterShells = 0;

	private int xx;
	private int yy;

	private String errorMessage;

	Critter critter;
	Dimension dm;
	Images images;

	// Menu
	boolean menuActivation = false;
	boolean error = false;
	Timer errorTimer;

	/**
	 * Constructor that constructs inventory object. Holds all the items you
	 * collect throughout the game that allows you to construct different
	 * objects in the game
	 * 
	 * @param x
	 *            - x position of the inventory
	 * @param y
	 *            - y position of the inventory
	 * @param id
	 *            - object id to be read by the handler
	 * @param game
	 *            - game it is in
	 * @param dm
	 *            - dimensions of the game
	 */
	public Inventory(double x, double y, ObjectId id, Game game, Dimension dm, Images images) {
		super(x, y, id, game);
		this.dm = dm;
		errorTimer = new Timer(5000, listener);
		this.images = images;
	}

	/**
	 * Method that prevents the user from building inside the water
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			error = false;
			errorTimer.stop();
		}
	};

	/**
	 * Method the sets the x and y positions of the inventory according to the
	 * critters x and y
	 * 
	 * @param critter
	 *            - critter object
	 */
	public void setCritter(Critter critter) {
		this.critter = critter;
		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 144;
	}

	/**
	 * Method that changes variables of inventory per call. - changes
	 * inventory's x position to be based on critter's x position - changes
	 * inventory's y position to be based on critter's y position
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {

		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 174;

	}

	/**
	 * Method that increases the regular compost count in the inventory
	 */
	public void setRegularCompost(int amount) {
		regularCompost += amount;
	}

	/**
	 * Method that increases the fertile compost count in the inventory
	 */
	public void setFertileCompost(int amount) {
		fertileCompost += amount;
	}

	/**
	 * Method that increases the regular compost count in the inventory
	 */
	public int getRegularCompost() {
		return regularCompost;
	}

	/**
	 * Method that increases the fertile compost count in the inventory
	 */
	public int getFertileCompost() {
		return fertileCompost;
	}

	/**
	 * Method to display the inventory image
	 */
	@Override
	public void pngSelector(Graphics g) {
		
		// Trash Bin Icon
		g.drawImage(images.getWasteIcon(), critter.getWasteX(), critter.getWasteY(), game);

		// Menu
		if (menuActivation) {

			drawMenu(g);

		}

		// Error
		if (error) {
			drawError(g);
		}

	}

	/**
	 * Method that builds objects based on the number of inventory objects
	 * 
	 * @param gm-
	 *            game
	 * @param type
	 *            - type of object to construct
	 */
	public void buildBarrier(Game gm, int type) {
		if (critter.getX() <= dm.getWidth() * .84 - 64 && !error) {
			switch (type) {
			case 0:
				// critter.plantGabion();
				// critter.setBuildAnimation(false);
				break;
			case 1:
				if (regularCompost >= 50) {
					critter.planT(0);
					critter.setBuildAnimation(true);
					regularCompost -= 50;
					gm.setPlantingTime(3000);
				} else {
					setErrorMessage("You do not have enough compost!");
					error = true;
					errorTimer.start();
				}
				break;
			case 2:
				if (regularCompost >= 75) {
					critter.planT(1);
					critter.setBuildAnimation(true);
					regularCompost -= 75;
					gm.setPlantingTime(3000);
				} else {
					setErrorMessage("You do not have enough compost!");
					error = true;
					errorTimer.start();
				}
				break;
			case 3:
				if (regularCompost >= 100) {
					critter.planT(2);
					critter.setBuildAnimation(true);
					regularCompost -= 100;
					gm.setPlantingTime(3000);
				} else {
					setErrorMessage("You do not have enough compost!");
					error = true;
					errorTimer.start();
				}
				break;
			case 4:
				if (fertileCompost >= 1) {
					critter.planT(3);
					critter.setBuildAnimation(true);
					fertileCompost -= 1;
					gm.setPlantingTime(3000);
				} else {
					setErrorMessage("You do not have enough compost!");
					error = true;
					errorTimer.start();
				}
				break;
			}
		} else {
			if (!error)
				setErrorMessage("You cannot build or plant here!");
			error = true;
			errorTimer.start();
		}
	}

	/**
	 * Returns the bounds of the inventory
	 */
	@Override
	public Rectangle getBounds() {
		return null;
	}

	/**
	 * Returns the boundaries of an inventory object
	 * 
	 * @return - the bounds of an inventory object
	 */
	public Rectangle getWeakGrass() {
		return new Rectangle((int) xx + 10, (int) yy + 10, 16, 16);
	}

	/**
	 * Method that draws the menu to see your inventory items
	 * 
	 * @param g
	 *            - graphics variable
	 */
	public void drawMenu(Graphics g) {

		// Borders
		g.setColor(Color.WHITE);
		g.drawRect((int) critter.getX() - 64, (int) critter.getY() - 176, 160, 160);

		// Compost Regular
		String output0 = "x" + regularCompost + " Oyster Shells";
		g.setColor(Color.white);
		g.drawString(output0, (int) xx + 31, (int) yy + 23);
		g.setColor(Color.orange);
		g.fillRect((int) xx + 10, (int) yy + 10, 16, 16);

		// Compost Fertile
		String output1 = "x" + fertileCompost + " Regular Compost";
		g.setColor(Color.white);
		g.drawString(output1, (int) xx + 31, (int) yy + 43);
		g.setColor(Color.blue);
		g.fillRect((int) xx + 10, (int) yy + 30, 16, 16);
		
		// Oyster Shells
		g.setColor(Color.white);
		g.drawString("", (int) xx + 31, (int) yy + 63);
		g.setColor(Color.red);
		g.fillRect((int) xx + 10, (int) yy + 50, 16, 16);

	}

	/**
	 * Method to draw something if an error occurs
	 * 
	 * @param g
	 *            - graphics variable
	 */
	public void drawError(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString(errorMessage, (int) critter.getX() - 64, (int) critter.getY() - 32);
	}

	public void setErrorMessage(String message) {
		errorMessage = message;
	}

	/**
	 * Method is display the menu when you press a key and get rid of it when
	 * you press the key again
	 */
	public void toggleMenu() {
		if (menuActivation)
			menuActivation = false;
		else
			menuActivation = true;
	}

}
