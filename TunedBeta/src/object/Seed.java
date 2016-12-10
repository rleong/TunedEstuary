package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Seed extends GameObject {
	//Attributes
	int type;
	int dir;
	Dimension dm;
	boolean growing;
	static Toolkit tk = Toolkit.getDefaultToolkit();
	Images images;
	
	/**
	 * Constructor that will create a seed object. This object will be how the
	 * aquatic plants in game 2 will be able to grow again once they decay
	 * 
	 * @param x - x position of seed
	 * @param y - y position of seed
	 * @param id - object id to be read in the handler
	 * @param game - game it will be in
	 * @param type - type of seed it will be 
	 * @param images - image of seed
	 */
	public Seed(double x, double y, ObjectId id, Game game, int type, Images images) {
		super(x, y, id, game);
		this.type=type;
		setVelY(-3);
		dm=tk.getScreenSize();
		Random random = new Random();
		dir=random.nextInt(100)%4;
		switch(dir){
		case 0:
			setVelX(-0.5);
			break;
		case 1:
			setVelX(0.5);
			break;
		case 2:
			setVelX(0.25);
			break;
		case 3:
			setVelX(-0.25);
			break;
		}
		growing=true;
		this.images = images;
	}

	/**
	 * Method will will change the attributes of the seed per call.
	 * 	-will set x to be x plus x velocity causing the object to move in the x direction
	 *  -will set y to be y plus y velocity causing the object to move in the y direction
	 *  -y velocity will have the addition of gravity causing the object it fall 
	 *  -creates boundaries so that the seed can only move in the set location
	 *  -calls the collision function
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		velY+=gravity;
		if(x<dm.getWidth()*5/6||x>dm.getWidth()*3/2-32)
			velX*=-1;
		
		collision();
	}

	/**
	 * Method that checks if certain object intersect the seed and do certain action when so
	 * 	-checks if the sand block is touching the seed: if so add a new tree and delete the seed and set falling to false
	 */
	private void collision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < game.handler.objectsList.size(); i++) {
			GameObject temp = game.handler.objectsList.get(i);
			if (temp.getId() == ObjectId.sand) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
//					if(growing){
						
						game.handler.objectsList.add(new WaterTree(x, y-65, ObjectId.waterTree, type, game, images));
						game.handler.objectsList.remove(this);
						return;
//					}
				}
			}
		}
	}

	/**
	 * Method that creates images of the seed depending on what seed it is
	 */
	@Override
	public void pngSelector(Graphics g) {
		g.drawImage(images.getSeed(), (int)x, (int)y, game);
	}

	/**
	 * Returns a boundary of the seed object which is used to tell if  
	 * the seed is touching other objects in the game
	 */
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}
	

}
