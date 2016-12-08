package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ArrayList;

import control.Game;
import window.Handler;

public abstract class GameObject {
	protected ObjectId id;
	protected double x,y;
	protected double velX = 0, velY = 0;
	protected boolean falling=true;
	protected double gravity =0.055f;
	protected Game game;
	
	/**
	 * Constructor that constructs a game object. This is an abstract class used
	 * to build almost every item that will be featured in the game.
	 * 
	 * @param x - x position of the game object
	 * @param y - y position of the game object
	 * @param id - object id 
	 * @param game - game 
	 */
	public GameObject(double x, double y, ObjectId id, Game game) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.game=game;
	}
	
	/**
	 * Abstract method that changes the variables of the object per call
	 * 
	 * @param object - list of game objects
	 */
	public abstract void tick(ArrayList<GameObject> object);
	
	/**
	 * Abstract method that display the object image
	 * 
	 * @param g - graphic variable
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Abstract method that returns the bounds of an object
	 * @return
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Method to get the x position of an object
	 * 
	 * @return x position
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Method to get the y position of an object
	 * 
	 * @return y position 
	 */
	public  double getY() {
		return y;
	}
	
	/**
	 * Method the set the x position for an object
	 * 
	 * @param x - x position to be
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Method to set the y position for an object
	 * 
	 * @param y - y position to be
	 */
	public void setY(double y) {
		this.y=y;
	}
	
	/**
	 * Method to get the x velocity of an object
	 * 
	 * @return the x velocity
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * Method to get the y velocity of an object
	 * 
	 * @return the y velocity
	 */
	public double getVelY() {
		return velY;
	}
	
	/**
	 * Method to set the x velocity for an object
	 * 
	 * @param x - x velocity to be
	 */
	public void setVelX(double x) {
		this.velX = x;
	}
	
	/**
	 * Method to set the y velocity for an object
	 * 
	 * @param y - y velocity to be
	 */
	public void setVelY(double y) {
		this.velY = y;
	}
	
	/**
	 * Method to get the object id for an object
	 * 
	 * @return id of an object
	 */
	public ObjectId getId() {
		return id;
	}
	
	/**
	 * Method that sets the gravity for an object
	 * 
	 * @param gravity - gravity to be
	 */
	public void setGravity(double gravity){
		this.gravity=gravity;
	}
	
	/**
	 * Method that gets the gravity of an object
	 * 
	 * @return gravity 
	 */
	public double getGravity(){
		return this.gravity;
	}
	
	/**
	 * Method to set if an object is falling or not
	 * 
	 * @param b - boolean to state if falling
	 */
	public void setFalling(boolean b){
		this.falling=b;
	}
	

	/**
	 * Method that returns if falling is true or false
	 *  
	 * @return falling boolean
	 */
	public boolean getFalling(){
		return this.falling;
	}

}
