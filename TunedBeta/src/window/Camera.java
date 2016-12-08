package window;

import java.awt.Dimension;

import framework.GameObject;

public class Camera {
	
	//Attributes
	private double x, y;
	public Dimension dm;
	
	/**
	 * Constructor that constructs a camera object. This object follows the critter allowing you 
	 * to move past the screen to explore the game created world.
	 * 
	 * @param x - x position of camera
	 * @param y - y position of camera
	 * @param dm - dimensions 
	 */
	public Camera(double x, double y, Dimension dm){
		this.x=x;
		this.y=y;
		this.dm=dm;
	}
	
	/**
	 * Method that gets the x position of the camera
	 * 
	 * @return returns x position
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Method that sets the x position of the camera
	 * 
	 * @param x - x position to be
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Method that gets the y position of the camera
	 * 
	 * @return y position
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Method that returns the y position of the camera
	 * 
	 * @param y - y position to be 
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Method that changes the variables of the camera per call
	 * 	-x position of the camera is set to the x position of the critter divided by the width of the jframe by 2
	 *  -y position of the camera is set to the y position of the critter divided by the height of the jframe by 2
	 *  Both these are used to make the camera follow the critter as you move it
	 *  
	 * @param critter - critter object in the game
	 */
	public void tick(GameObject critter) {
		x = -critter.getX() + dm.getWidth() / 2;
		
		y = -critter.getY() + dm.getHeight() / 2;
		
		
	}

}
