package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
/**
 * 
 * @author justin said
 *
 */
public class Waves extends GameObject {
	static boolean hard = false;
	//private BufferedImage waveImg;
	/**
	 * creates a wave object spawned from waveclock, they move to the left and damage the estuary, barriers, and gabions
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public Waves(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		if(hard == true){
			setVelX(5);
		}
		else{
			setVelX(3);
		}
	}

	@Override
	/**
	 * continuously called to update position
	 */
	public void tick(ArrayList<GameObject> object) {
		x-=velX;
		
	}

	@Override
	/**
	 * prints wave object
	 */
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);

	}
	

	@Override
	/**
	 * gets object bounds
	 * @return new Rectangle with bounds for collision
	 */
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}
}

