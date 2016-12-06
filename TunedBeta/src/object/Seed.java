package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;

public class Seed extends GameObject {
	int type;
	int dir;
	Dimension dm;
	boolean growing;
	static Toolkit tk = Toolkit.getDefaultToolkit();
	public Seed(double x, double y, ObjectId id, Game game, int type) {
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
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		velY+=gravity;
		if(x<dm.getWidth()*5/6||x>dm.getWidth()*3/2-32)
			velX*=-1;
		
		collision();
	}

	private void collision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.sand) {
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
//					if(growing){
						
						game.handler.object.add(new WaterTree(x, y-65, ObjectId.waterTree, type, game, dm));
						game.handler.object.remove(this);
						return;
//					}
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
