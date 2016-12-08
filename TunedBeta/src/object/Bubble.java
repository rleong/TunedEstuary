package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Bubble extends GameObject {

	//Attributes 
	double bounds = 0;
	boolean pop = false;
	ArrayList<Waste> carriedWaste;
	boolean right;

	/**
	 * Constructor that constructs a bubble object that will be used by the oyster
	 * to remove recycle waste faster 
	 * 
	 * @param x - x position of the bubble 
	 * @param y - y position of the bubble
	 * @param id - object id to be read by the handler 
	 * @param game - game that it is in
	 * @param right - direction of bubble
	 */
	public Bubble(double x, double y, ObjectId id, Game game, boolean right) {
		super(x, y, id, game);
		this.right = right;
		if(right)
			bounds = x += 700;
		else
			bounds = x -= 700;
	}

	/**
	 * Method to change the variables of bubble per call
	 * 	-if boolean right is equal to true add 3.5 to the x position to make it move right in the x position
	 *  - else subtract 3.5 from the x position to make the bubble move left in the x position
	 *  - Add faction to y to make the bubble flow up and down 
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		if(right)
			x += 3.5;
		else
			x -= 3.5;
		y += .5 * Math.sin(x / 25);
	}

	/**
	 * Method to display the bubble image
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(006666));
		g.drawOval((int) x, (int) y, 48, 48);
		g.setColor(Color.WHITE);
		g.drawOval((int) x + 12, (int) y + 6, 12, 10);
	}

	/**
	 * Method to set the bubble true if it has popped 
	 */
	public void popBubble() {
		pop = true;
	}
	
	/**
	 * Method to return if the bubble is popped or not
	 * 
	 * @return - boolean if popped or not
	 */
	public boolean getPop(){
		return pop;
	}

	/**
	 * Method to pop the bubble if it goes out of bounds
	 * 
	 * @return boolean if the bubble is popped 
	 */
	public boolean getDeath() {
		if(right){
			if (x + 10 >= bounds) {
				popBubble();
				return true;
			} else
				return false;
		}else{
			if (x - 10 <= bounds) {
				popBubble();
				return true;
			} else
				return false;
		}
	}

	/**
	 * Method if get boundaries where the bubble can't go
	 * 
	 * @return x positions where the ubble can't go
	 */
	public double getBoundaries() {
		return bounds;
	}

	/**
	 * Returns the boundaries of the bubble object.
	 * Used to check if the bubble is colliding with other objects
	 */
	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 48, 48);
	}

}
