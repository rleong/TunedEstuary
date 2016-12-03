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
	long timer;
	public RofFactory(double x, double y, ObjectId id, Handler handler, Game game) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		this.game=game;
		timer=System.currentTimeMillis();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		switch(game.g2stage){
		case 0:
			if (System.currentTimeMillis() - timer > 8000) {
				timer=System.currentTimeMillis();
				game.g2stage+=1;
			}
			break;
		case 1:
			if(game.nWaste<0){
				game.nWaste=0;
				game.g2stage+=1;
				break;
			}
			wave1();
			break;
		case 2:
			break;
		}
		
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
	
	
	public void wave1(){
		if (System.currentTimeMillis() - timer > 2500 && game.nW1<5) {
			timer += 2500;
			handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 0, game));
			game.nW1+=1;
			game.nWaste+=1;
		}
	}
	public void wave2(){
		
	}
	public void wave3(){
		
	}
	public void wave4(){
		
	}

}
