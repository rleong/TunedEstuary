package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class WasteBin extends GameObject {
	int type = 0;
	Images images;
	Game gm;

	public WasteBin(double x, double y, ObjectId id, int type, Images images, Game game) {
		super(x, y, id, game);
		this.type = type;
		this.images = images;
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		collision(object);
	}

	@Override
	public void render(Graphics g) {
		if (type == 0) {
			g.drawImage(images.getWasteBins(0), (int)x, (int)y, gm);
		} else if (type == 1) {
			g.drawImage(images.getWasteBins(1), (int)x, (int)y, gm);
		} else {
			System.out.println("Something went wrong you baffoon!");
		}
		

	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);	
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
	public LinkedList<GameObject> testCollision(LinkedList<GameObject> test) {
		collision(test);
		return test;
	}


}
