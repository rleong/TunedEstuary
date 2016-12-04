package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Bubble extends GameObject {

	double bounds = 0;
	boolean pop = false;
	LinkedList<Waste> carriedWaste;
	boolean right;

	public Bubble(double x, double y, ObjectId id, Handler handler, boolean right) {
		super(x, y, id, handler);
		this.right = right;
		if(right)
			bounds = x += 700;
		else
			bounds = x -= 700;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(right)
			x += 3.5;
		else
			x -= 3.5;
		y += .5 * Math.sin(x / 25);
		popBubble();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(006666));
		g.drawOval((int) x, (int) y, 48, 48);
		g.setColor(Color.WHITE);
		g.drawOval((int) x + 12, (int) y + 6, 12, 10);
	}

	public void popBubble() {
		if (x == bounds) {
			pop = true;
		}
	}

	public boolean getDeath() {
		if(right){
			if (x + 10 >= bounds) {
				return true;
			} else
				return false;
		}else{
			if (x - 10 <= bounds) {
				return true;
			} else
				return false;
		}
	}

	public double getBoundaries() {
		return bounds;
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 48, 48);
	}

}
