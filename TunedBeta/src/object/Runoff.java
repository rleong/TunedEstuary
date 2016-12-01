package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Runoff extends GameObject {
	public int type;
	Dimension dm;
	Game game;

	public Runoff(double x, double y, Dimension dm, Handler handler, ObjectId id, int type, Game game) {
		super(x, y, id, handler);
		if (type == 0 || type == 1 || type == 2) {
			setVelX(1.2);
		} else if (type == 3) {
			setVelX(.5);
		}
		this.type = type;
		this.dm = dm;
		this.game = game;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if (falling)
			velY += gravity;
		x += velX;
		y += velY;
		collision(handler.object);
	}

	@Override
	public void render(Graphics g) {
		switch (type) {
		case 0:
			g.setColor(Color.green);
			break;
		case 1:
			g.setColor(Color.darkGray);
			break;
		case 2:
			g.setColor(Color.yellow);
			break;
		case 3:
			g.setColor(Color.BLUE);
			break;
		}

		if (type == 0 || type == 1 || type == 2) {
			g.fillRect((int) x, (int) y, 32, 32);
		} else if (type == 3) {
			g.fillRect((int) x, (int) y, 75, 100);
		}

	}

	@Override
	public Rectangle getBounds() {
		if (type == 3) {
			return new Rectangle((int) x, (int) y, 75, 132);
		} else {
			return new Rectangle((int) x, (int) y, 32, 32);
		}
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.landSurface) {
				if (getBounds().intersects(temp.getBounds())) {
					falling = false;
					velY = 0;
					if (type == 3) {
						setY(temp.getY() - 100);
					} else {
						setY(temp.getY() - 32);
					}
				} else {
					falling = true;
				}
			}
			if (temp.getId() == ObjectId.sand) {
				if (getBounds().intersects(temp.getBounds())) {
					falling = false;
					velY = 0;
					setY(temp.getY() - 32);
				} else {
					falling = true;
				}
			}
			if (temp.getId() == ObjectId.seaLevel) {

				if (getBounds().intersects(temp.getBounds())) {
					game.count += 1;
					handler.removeObject(this);

				}
			}
		}

	}
}
