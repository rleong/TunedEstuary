package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Tutorial extends GameObject {
	long timer = System.currentTimeMillis();
	Runoff rof;
	Tree tree;
	Waste w1;
	Waste w2;
	Boat boat;
	Critter critter;
	Images images;
	
	public Tutorial(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		this.images = images;
		// TODO Auto-generated constructor stub
		rof=new Runoff(y, y, game.dm, ObjectId.runOff, 0, game, images);
		tree = new Tree(y, y, id, 0, game, null);
		w1  = new Waste(y, y, id, game, null, null, null, 0, null);
		w2 = new Waste(y, y, id, game, null, null, null, 0, null);
		boat = new Boat(y, y, id, game, null, null, null, y, y, falling, null);
		critter=new Critter(y, y, id, falling, falling, null, null, game, null);
	}
	
	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
