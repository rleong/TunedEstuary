package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;

public class Game3Instructions extends GameObject {
	public Game3Instructions(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
	}
	@Override
	//no tick
	public void tick(LinkedList<GameObject> object){
	}

	@Override
	//prints game instructions
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Collect Oysters, Rope, and Wood",(int) (game.dm.getWidth()/2.5),(int) (game.dm.getHeight()/8));
		g.drawString("to build Gabions and protect your estuary from erosion",(int) (game.dm.getWidth()/3),(int) (game.dm.getHeight()/8 + 20));
		g.drawString("Press 'Space' to collect object and 'G' to build a Gabion", (int) (game.dm.getWidth()/3),(int) (game.dm.getHeight()/8+40));
		g.drawString("5 Oysters, 1 Rope, and 1 Wood are needed to build a Gabion", (int) (game.dm.getWidth()/3),(int) (game.dm.getHeight()/8+60));
	}
	@Override
	//has no bounds
	public Rectangle getBounds() {
		return null;
	}
	
}


