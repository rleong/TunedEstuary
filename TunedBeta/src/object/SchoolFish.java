package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class SchoolFish extends GameObject {
	Game game;
	int speed = 2;
	boolean isDead;
	List<Fish> school = new ArrayList<Fish>();

	public SchoolFish(double x, double y, ObjectId id, Handler handler, Game game) {
		super(x, y, id, handler);
		this.game = game;
		velX = speed;
		school.add(new Fish(1));
		school.add(new Fish(1));
		school.add(new Fish(1));
		school.add(new Fish(2));
		school.add(new Fish(2));
		school.add(new Fish(3));
		school.add(new Fish(4));

	}

	public int schoolSize() {
		return school.size();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		Iterator<Fish> it = school.iterator();
		while (it.hasNext()) {
			Fish temp = it.next();
			if (game.count == temp.gethType()) {
				it.remove();
			}
		}

		if (school.isEmpty()) {
			isDead = true;
		}

		x += velX;
		if (x <= 1600 || x >= 2400 ) {
			velX *= -1;
		}

	}

	@Override
	public void render(Graphics g) {
		switch (school.size()) {
		case 7:
			g.setColor(Color.YELLOW);
			break;
		case 4:
			g.setColor(Color.ORANGE);
			break;
		case 2:
			g.setColor(Color.RED);
			break;
		case 1:
			g.setColor(Color.LIGHT_GRAY);
			break;
		case 0:
			g.setColor(Color.PINK);
			break;
		}
		g.fillRect((int) x, (int) y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {

		return null;
	}
}
