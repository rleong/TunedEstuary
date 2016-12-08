package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Bubble extends GameObject {

	double bounds = 0;
	boolean pop = false;
	ArrayList<Waste> carriedWaste;
	boolean right;

	public Bubble(double x, double y, ObjectId id, Game game, boolean right) {
		super(x, y, id, game);
		this.right = right;
		if(right)
			bounds = x += 700;
		else
			bounds = x -= 700;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		if(right)
			x += 3.5;
		else
			x -= 3.5;
		y += .5 * Math.sin(x / 25);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(006666));
		g.drawOval((int) x, (int) y, 48, 48);
		g.setColor(Color.WHITE);
		g.drawOval((int) x + 12, (int) y + 6, 12, 10);
	}

	public void popBubble() {
		pop = true;
	}
	
	public boolean getPop(){
		return pop;
	}

	public boolean getDeath() {
		if(right){
			if (x + 10 >= bounds) {
				popBubble();
				return true;
			} else
				return false;
		}else{
			if (x - 10 <= bounds) {
				popBubble();
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
