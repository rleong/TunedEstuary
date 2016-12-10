package object;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class LandSurface extends GameObject {
	//Attributes
	Images images;
	private int stage = 0;
	private int init = 0;
	
	/**
	 * Constructor used to construct game object that are used to build the 
	 * map where the player moves around in. 
	 * 
	 * @param x - x position of land surface
	 * @param y - y position of land surface 
	 * @param id - object id 
	 * @param game - game it is in
	 */
	public LandSurface(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		this.images = images;
		
	}

	/**
	 * Method that changes variables of land surface per call.
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {
		if(init == 0){
			stage = game.getWaterCondition();
			init = 1;
		}
			
		
	}

	/**
	 * Method that displays land surface images depending on the type of surface
	 * you need
	 */
	@Override
	public void pngSelector(Graphics g) {
		if(id==ObjectId.landSurface){
			g.drawImage(images.getGrassTiles(0), (int)x, (int)y, game);
			g.drawImage(images.getGrassTiles(1), (int)x, (int)y+32, game);
			g.drawImage(images.getGrassTiles(2), (int)x, (int)y+64, game);
			for(int i = (int) y + 96; i < game.dm.getHeight()*2; i += 32){
				g.drawImage(images.getGrassTiles(3), (int)x, (int)i, game);
			}
		}
		if(id==ObjectId.seaLevel){
			// Not visible
		}
		if(id==ObjectId.wall){
			// Not visible
		}
		if(id==ObjectId.waterImageMarker){
			stage = game.getWaterCondition();
			g.drawImage(images.getWaterTiles(stage, 0), (int)x, (int)y, game);
			g.drawImage(images.getWaterTiles(stage, 1), (int)x, (int)y+32, game);
			for(int i = (int) y + 64; i < game.dm.getHeight() - 64; i += 32){
				g.drawImage(images.getWaterTiles(stage, 2), (int)x, (int)i, game);
			}
		}
		if(id==ObjectId.waterImageMarker2){
			stage = game.getWaterCondition();
			g.drawImage(images.getWaterTiles(stage, 0), (int)x, (int)y, game);
			g.drawImage(images.getWaterTiles(stage, 1), (int)x, (int)y+32, game);
			for(int i = (int) y + 64; i < game.dm.getHeight() * 6/5; i += 32){
				g.drawImage(images.getWaterTiles(stage, 2), (int)x, (int)i, game);
			}
		}
		if(id==ObjectId.sand){
			g.drawImage(images.getSandTiles(0), (int)x, (int)y, game);
			g.drawImage(images.getSandTiles(1), (int)x, (int)y+32, game);
			g.drawImage(images.getSandTiles(2), (int)x, (int)y+64, game);
			g.drawImage(images.getSandTiles(3), (int)x, (int)y+96, game);
			for(int i = (int) y + 128; i < game.dm.getHeight()*2; i += 32){
				g.drawImage(images.getSandTiles(4), (int)x, (int)i, game);
			}
		}
		
	}

	/**
	 * Returns the boundaries of the land surface object.
	 * Used to check if land surface is colliding with another object
	 */
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
