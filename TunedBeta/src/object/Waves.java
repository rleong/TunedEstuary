package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Waves extends GameObject {
	static boolean hard = false;
	//private BufferedImage waveImg;
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
	public void tick(LinkedList<GameObject> object) {
		x-=velX;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);

	}
	

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}
	public void setVelx(int val){
		setVelX(val);
	}
}

