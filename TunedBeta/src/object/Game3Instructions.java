package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
import window.Window;

public class Game3Instructions extends GameObject {
	public Game3Instructions(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id, handler);
	}
	@Override
	//no tick
	public void tick(LinkedList<GameObject> object){
	}

	@Override
	//prints game instructions
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Collect Oysters, Rope, and Wood",(int) (Window.getDm().getWidth()/2.5),(int) (Window.getDm().getHeight()/8));
		g.drawString("to build Gabions and protect your estuary from erosion",(int) (Window.getDm().getWidth()/3),(int) (Window.getDm().getHeight()/8 + 20));
		g.drawString("Press 'Space' to collect object and 'G' to build a Gabion", (int) (Window.getDm().getWidth()/3),(int) (Window.getDm().getHeight()/8+40));
		g.drawString("5 Oysters, 1 Rope, and 1 Wood are needed to build a Gabion", (int) (Window.getDm().getWidth()/3),(int) (Window.getDm().getHeight()/8+60));
	}
	@Override
	//has no bounds
	public Rectangle getBounds() {
		return null;
	}
	
}


