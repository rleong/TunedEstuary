package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ArrayList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Tree extends GameObject {
	public int hp;
	public int type;
	private int stage = 0;
	Images images;
	Timer buildStage;

	public Tree(double x, double y, ObjectId id, int type, Game game, Images images) {
		super(x, y, id, game);
		hp = 3;
		this.type = type;
		this.images = images;
		buildStage = new Timer(1000, listener);
		buildStage.start();
	}

	// Timer for the render of the build stage of plant
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(stage == 2)
				buildStage.stop();
			else{
				buildStage.restart();
				stage++;
			}			
		}
	};

	@Override
	public void tick(ArrayList<GameObject> object) {

		collision(object);
	}

	private void collision(ArrayList<GameObject> object) {
		GameObject temp;
		for (int i=0; i<object.size();i++) {
			temp = object.get(i);
			if (temp.getId() == ObjectId.runOff) {
				Runoff rof = (Runoff) temp;
				if (getBounds().intersects(temp.getBounds())) {
					if (this.type == rof.type) {
						game.setnRof(game.getnRof() - 1);
						hp -= 1;
						object.remove(temp);
						if(hp<=0)
							object.remove(this);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch (type) {
		case 0:
			g.drawImage(images.getPlant1(stage), (int) x - 16, (int) y - 32, game);
			break;
		case 1:
			g.drawImage(images.getPlant2(stage), (int) x - 16, (int) y - 32, game);
			break;
		case 2:
			g.drawImage(images.getPlant3(stage), (int) x - 16, (int) y - 32, game);
			break;
		case 3:
			g.drawImage(images.getGoldenrod(stage), (int) x - 16, (int) y - 32, game);
			break;
			
		}

		g.setColor(Color.red);
		g.fillRect((int) x - 16, (int) y - 42, (int) (hp * 64 / 3), 2);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x - 16, (int) y - 32, 64, 64);
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

}
