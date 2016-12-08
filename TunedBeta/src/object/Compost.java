package object;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Compost extends GameObject {
	int type;
	public Compost(double x, double y, ObjectId id, Game game, int type) {
		super(x, y, id, game);
		this.type=type;
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
			g.setColor(Color.MAGENTA);
			break;
		case 1:
			g.setColor(Color.ORANGE);
			break;
		case 2: 
			g.setColor(Color.CYAN);
			break;
		case 3: 
			g.setColor(Color.RED);
			break;
		}
		g.fillOval((int)x, (int)y, 32, 32);
		

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
