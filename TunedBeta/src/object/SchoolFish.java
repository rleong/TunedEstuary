package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class SchoolFish extends GameObject {
	//Attributes
	Game game;
	int speed = 2;
	boolean isDead;
	List<Fish> school = new ArrayList<Fish>();
	Images images;
	private int stage = 0;
	
	/**
	 * Constructor that creates a school of fish containing multiple fish objects.
	 * SchoolFish will be placed in the water in game 2 swimming around. In the Game
	 * as the water gets more polluted more fish will die. Showing how the environment
	 * is being affected by waste and pollutants
	 * 
	 * @param x - the x position where the school will spawn
	 * @param y - the y position where the school will spawn
	 * @param id - the id of the object to be read in the handler
	 * @param game - the game that it is in
	 */
	public SchoolFish(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		this.game = game;
		velX = speed;
		this.images = images;
		school.add(new Fish(1));
		school.add(new Fish(1));
		school.add(new Fish(1));
		school.add(new Fish(2));
		school.add(new Fish(2));
		school.add(new Fish(3));
		school.add(new Fish(4));

	}

	/**
	 * Method to get the speed at which the school of fishes will travel
	 * 
	 * @return the speed of the school
	 */
	public int schoolSize() {
		return school.size();
	}
	
	/**
	 * Method to set the speed of the school of fishes
	 * 
	 * @param speed - the speed of the school
	 */
	public boolean isDead(){
		return isDead;
	}

	/**
	 * Method that changes multiple variables of the school per call.
	 * 	-The first part sets the velocity for the x position to the speed.
	 *  -X position is set to x plus the velocity causing the school to move in the x direction
	 * 	-Next depending on the water level fishes will be removed from the school
	 *  -Then checks to see if the school is empty and if so change alive to false
	 *  -Last the x position is set to certain bounds so that it can only move in a certain location
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		Iterator<Fish> it = school.iterator();
		while (it.hasNext()) {
			Fish temp = it.next();
			if (game.waterCondition == temp.gethType()) {
				it.remove();
			}
		}

		if (school.isEmpty()) {
			isDead = true;
		}

		x += velX;
		if (x < game.dm.getWidth() || x > game.dm.getWidth()*3/2 ) {
			velX *= -1;
		}
		
		if (velX > 0)
			stage = 1;
		else
			stage = 0;

	}

	/**
	 * Method that will change the image of the school depending on how many
	 * fishes are still in the school
	 */
	@Override
	public void pngSelector(Graphics g) {
		switch (school.size()) {
		case 7:
			g.drawImage(images.getSchoolFish(0, stage), (int) x, (int) y, game);
			break;
		case 4:
			g.drawImage(images.getSchoolFish(1, stage), (int) x, (int) y, game);
			break;
		case 2:
			g.drawImage(images.getSchoolFish(2, stage), (int) x, (int) y, game);
			break;
		case 1:
			g.drawImage(images.getSchoolFish(3, stage), (int) x, (int) y, game);
			break;
		case 0:
			g.drawImage(images.getSchoolFish(4, stage), (int) x, (int) y, game);
			break;
		}
	}

	/**
	 * Method that gets the boundary of the school 
	 */
	@Override
	public Rectangle getBounds() {

		return null;
	}
}
