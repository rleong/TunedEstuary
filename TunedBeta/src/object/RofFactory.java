package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class RofFactory extends GameObject {
	Game game;
	public RofFactory(double x, double y, ObjectId id, Handler handler, Game game) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		this.game=game;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y-32, 32, 64);
		g.setColor(Color.ORANGE);
		g.drawRect((int)x, (int)y-32, 32, 64);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
