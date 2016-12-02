package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Seed extends GameObject {
	int type;
	int dir;
	Dimension dm;
	
	static Toolkit tk = Toolkit.getDefaultToolkit();
	public Seed(double x, double y, ObjectId id, Handler handler, int type) {
		super(x, y, id, handler);
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
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		velY+=gravity;
		collision();
	}

	private void collision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.sand) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					handler.object.add(new WaterTree(x, y-65, ObjectId.waterTree, type, handler, dm));
					handler.object.remove(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.setColor(Color.green);
			break;
		case 1:
			g.setColor(Color.PINK);
			break;
		}
		g.fillOval((int)x, (int)y, 32, 32);
		

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}
	

}
