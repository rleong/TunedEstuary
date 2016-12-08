package object;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Compost extends GameObject {
	//Attributes
	int type;
	Images images;
	Random random = new Random();
	int imageType;
	private int dir;
	
	/**
	 * Constructor that constructs compost objects. This object is drop by the 
	 * boat or water plants which is used to build plants that protect your estuary
	 * form run off.
	 * 
	 * @param x - x position of compost
	 * @param y - y position of compost
	 * @param id - objcet id of game object
	 * @param game - game object
	 * @param type - type of compost
	 * @param images - image of compost
	 */
	public Compost(double x, double y, ObjectId id, Game game, int type, Images images) {
		super(x, y, id, game);
		this.type=type;
		this.images = images;
		imageType = random.nextInt(2);
		Random random = new Random();
		dir=random.nextInt(100)%5;
		switch(dir){
		case 0:
			setVelX(-1);
			setVelY(-2);
			break;
		case 1:
			setVelX(1);
			setVelY(-3);
			break;
		case 2:
			setVelX(2);
			setVelY(-4);
			break;
		case 3:
			setVelX(-2);
			setVelY(-1);
			break;
		default:
			setVelX(0);
			setVelY(-0.5);
			break;
		}
	}

	/**
	 * Method that changes variables of compost per call
	 * 	-add x velocity to x position to make compost move in x direction
	 * 	-add y velocity to y position to make compost move in y direction
	 * 	-if falling add graivty to y velocity to make compost drop
	 * 	-call collision function
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		x+=velX;
		if(x<game.dm.getWidth()*5/6){
			velX*=-1;
		}
		if(x>game.dm.getWidth()*3/2-35){
			velX*=-1;
		}
		y+=velY;
		if(falling)
			velY+=gravity;
		collision();
	}

	/**
	 * Method that checks if compost is colliding with certain game object
	 * and performs an action if so.
	 * 	-if compost is colliding with sand: make y velocity 0 to prevent moving past sand
	 */
	private void collision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.sand) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					setVelY(0);
					setVelX(0);
					return;
				}
			}
			if (temp.getId() == ObjectId.landSurface) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					setVelY(0);
					setVelX(0);
					return;
				}
			}
			
		}
	}

	/**
	 * Method to display compost images depending on type
	 */
	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(images.getWaste(2, imageType), (int) x, (int) y, game);
			g.setColor(Color.WHITE);
			g.drawString("Compost", (int)x-8, (int)y-10);
			g.drawString("Regular", (int)x-4, (int)y-22);
			break;
		case 1:
			g.drawImage(images.getWaste(2, 2), (int) x, (int) y, game);
			g.setColor(Color.WHITE);
			g.drawString("Compost", (int)x-8, (int)y-10);
			g.drawString("Fertile", (int)x-4, (int)y-22);
			break;
			
		}
		

	}

	/**
	 * Method to get the boundaries of the compost object
	 * Used to tell if compost is colliding with other object
	 */
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
