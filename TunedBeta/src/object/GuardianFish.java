package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class GuardianFish extends GameObject {
	int damage;
	Game game;
	boolean chasing;
	MiracleTree mt;
	boolean firstTime=true;
	Images images;

	public GuardianFish(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		damage = 5;
		this.game = game;
		chasing = false;
		velX = .5;
		velY = .5;
		this.images = images;
		this.mt=new MiracleTree(game.dm.getWidth()*7/4, game.dm.getHeight()*6/5-192, ObjectId.MiracleTree, game, images);
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		if(!chasing){
		x += velX;
		y += velY;
		if (x < (game.dm.getWidth() * 1.517) || x > (game.dm.getWidth() * 1.98)) {
			velX *= -1;
		}
		if (y < game.dm.getHeight() * 73 / 100 || y > game.dm.getHeight() * 1.07) {
			velY *= -1;
		}
		}
		collision(game.handler.object);

	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillRect((int) game.dm.getWidth() * 1517 / 1000, (int) game.dm.getHeight() * 73 / 100, 960, 420);
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 64, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 32);
	}

	public Rectangle getGuardBounds() {
		return new Rectangle((int) game.dm.getWidth() * 1517 / 1000, (int) game.dm.getHeight() * 73 / 100, 960, 420);

	}

	private void collision(ArrayList<GameObject> object) {
		for (int i = 0; i < game.handler2.object.size(); i++) {
			GameObject temp = game.handler2.object.get(i);
			if (temp.getId()  == ObjectId.critter) {
				if (getGuardBounds().intersects(temp.getBounds())) {
					chasing = true;
					mt.lock=true;
					if(firstTime){
						game.handler.addObject(mt);
						mt.setTimer();
						firstTime=false;
					}
					if(this.x <= temp.getX()){
						velX = 1.2;
						x += velX;
						
					}
					if(this.x >= temp.getX() && x > (game.dm.getWidth() * 1.517)){
						velX = 1.2;
						x -= velX;
						
					}
					if( this.y <= temp.getY()){
						velY = 1.2;
						y += velY;
						
					}
					if( this.y >= temp.getY() && y > game.dm.getHeight() * 73 / 100 ){
						velY = 1.5;
						y -= velY;
						
					}
				}
				if (!getGuardBounds().intersects(temp.getBounds())) {
					chasing = false;
					mt.lock=false;
					
				}
			}
		}
	}
}
