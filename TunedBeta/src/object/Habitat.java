package object;

import java.awt.Color;
import java.awt.Dimension;
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

public class Habitat extends GameObject{
	
	//Attributes
	private double health = 30;
	private final double TRASHDAMAGE = .5;
	private final double COMPOSTDAMAGE = .25;
	private int numberOfHazards = 0;
	private int numberOfCompost = 0;
	private double width;
	private ArrayList<Integer> imageIndexStorage = new ArrayList();
	
	Random random = new Random();
	private int temporaryIndexNumber;
	
	private int stage = 0;
	
	Images images;
	
	Timer clock;

	/**
	 * Constructor that creates habitat object. This habitat represents an actual 
	 * habitat in an estuary and will get damaged unless you defend it from waste.
	 * 
	 * @param x - x position of habitat
	 * @param y - y position of habitat
	 * @param id - object id read by the handler
	 * @param game - game that it is in
	 * @param dm - dimension of game
	 * @param images - image of the habitat
	 */
	public Habitat(double x, double y, ObjectId id, Game game, Dimension dm, Images images) {
		super(x, y, id,game);
		width = dm.getWidth()*3/2-dm.getWidth()*.84;
		clock = new Timer(7000, dmgOverTime);
		clock.start();
		this.images = images;
		int temporaryIndexSize = (int) width / 64;
		for(int i = 0; i < temporaryIndexSize; i++){
			temporaryIndexNumber = random.nextInt(3);
			imageIndexStorage.add((Integer) temporaryIndexNumber);
		}
	}
	
	/**
	 * Method that performs a certain action at a certain time
	 * 	- subtract the health of the habitat per waste inside every 7 seconds  
	 */
	ActionListener dmgOverTime = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			health -= (numberOfHazards * TRASHDAMAGE) + (numberOfCompost * COMPOSTDAMAGE);
			clock.restart();
		}
	};

	/**
	 * Method that changes the variables of Habitat per call.
	 * 	-If health of the habitat is equal to 0 stop the clock
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		collision(object);
		if(health >= 30){
			stage = 0;
		}else if (health <= 30 * 2/3 && health > 30 * 1/3){
			stage = 1;
		}else if (health <= 30 * 1/3 && health > 0){
			stage = 2;
		}else if(health<=0){
			clock.stop();
		}
		
	}

	/**
	 * Method that display the habitat image
	 */
	@Override
	public void pngSelector(Graphics g) {
		int temporaryXValue = 0;
		for(int i = 0; i < imageIndexStorage.size(); i++){
			g.drawImage(images.getHabitat(stage, imageIndexStorage.get(i)), (int) x + temporaryXValue, (int) y, game);
			temporaryXValue += 64;
		}
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y-8, (int) ((health / 30) * width), 6);
	}
	
	/**
	 * Method that checks if a certain item is colliding with Habitat 
	 * and performs action when so
	 * 	- number of hzards is set to 0
	 * 	- number of compost is set to 0
	 * 	- if waste collides with habitat: increase the number of waste or compost 
	 * 
	 * @param object - list of game object
	 */
	private void collision(ArrayList<GameObject> object) {
		numberOfHazards = 0;
		numberOfCompost = 0;
		for (int i = 0; i < game.handler.objectsList.size(); i++) {
			GameObject temp = game.handler.objectsList.get(i);
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (getBounds().intersects(temp.getBounds()) && !waste.checkDeath() && !waste.getIsTrapped()) {
					if(waste.getType()!=2)
						numberOfHazards += 1;
					else
						numberOfCompost += 1;
				}
			}
		}
	}
	/**
	 * Method that gets the health of the habitat
	 * 
	 * @return health 
	 */
	public double getHealth(){
		return health;
	}
	
	/**
	 * Method that gets the width of the habitat
	 * 
	 * @return width
	 */
	public double getWidth(){
		return width;
	}
	/**
	 * Returns the boundaries of the habitat object.
	 * Used to tell if the habitat is colliding with other objects
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x + 6, (int) y + 26, (int)width, 64);
	}
	
	public ArrayList<GameObject> testCollision(ArrayList<GameObject> test) {
		collision(test);
		return test;
	}

}
