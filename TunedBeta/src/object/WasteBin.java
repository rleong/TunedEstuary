package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class WasteBin extends GameObject {
	int type = 0;
	Images images;
	Game gm;
	boolean highlight;
	Critter critter;

	public WasteBin(double x, double y, ObjectId id, int type, Images images, Game game, boolean highlighting) {
		super(x, y, id, game);
		this.type = type;
		this.images = images;
		highlight = highlighting;
	}
	
	public void setCritter(Critter critter){
		this.critter = critter;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		x = critter.getWasteX();
		y = critter.getWasteY() - 16 ;
		collision(object);
	}

	@Override
	public void render(Graphics g) {
		if(highlight){
			if (type == 0) {
				g.drawImage(images.getWasteBins(0), (int)x, (int)y, gm);
			} else if (type == 1) {
				g.drawImage(images.getWasteBins(1), (int)x, (int)y, gm);
			} else {
				System.out.println("Something went wrong you baffoon!");
			}
		}
		
	}

	private void collision(ArrayList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);	
			if (temp.getId() == ObjectId.waste) {
				if (getBounds().intersects(temp.getBounds()))
					object.remove(temp);
			}
		}
	}

	public int getType() {
		return type;
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}
	public ArrayList<GameObject> testCollision(ArrayList<GameObject> test) {
		collision(test);
		return test;
	}
	
	public void setHighlight(){
		if (highlight)
			highlight = false;
		else
			highlight = true;
	}
	
	public boolean getHighlight(){
		return highlight;
	}

}
