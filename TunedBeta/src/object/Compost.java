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
	int type;
	Images images;
	Random random = new Random();
	int imageType;
	
	public Compost(double x, double y, ObjectId id, Game game, int type, Images images) {
		super(x, y, id, game);
		this.type=type;
		this.images = images;
		imageType = random.nextInt(2);
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		if(falling)
			velY+=gravity;
		collision();
	}

	private void collision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.sand) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					setVelY(0);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(images.getWaste(0, imageType), (int) x, (int) y, game);
			break;
		case 1:
			g.drawImage(images.getWaste(0, 2), (int) x, (int) y, game);
			break;
			
		}
		

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
