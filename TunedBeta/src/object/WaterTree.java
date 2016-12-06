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

public class WaterTree extends GameObject {
	Dimension dm;
	public boolean canAttack;
	public int hp;
	int type;
	private long timer0=System.currentTimeMillis();
	private long timer1=System.currentTimeMillis();
	private long timer2=System.currentTimeMillis();

	public WaterTree(double x, double y, ObjectId id,int type, Game game, Dimension dm) {
		super(x, y, id, game);
		this.dm=dm;
		hp=20;
		
		this.type=type;
	}
//	public void change(){
//		type+=1;
//		type=type%2;
//	}
	public void chopDown(){
		game.handler.addObject(new Compost(x,y,ObjectId.compost1, game, type));
		game.handler.addObject(new Compost(x,y,ObjectId.compost1, game, type));
		game.handler.addObject(new Compost(x,y,ObjectId.compost1, game, type));
		dead();
	}
	public void dead(){
		dropSeed();
		canAttack=false;
		game.handler.object.remove(this);
		
	}
	public void dropCompost() {
	// TODO Auto-generated method stub
		game.handler.addObject(new Compost(x, y, ObjectId.compost1, game, type));
		
	}
	public void dropSeed(){
		game.handler.addObject(new Seed(x,y,ObjectId.seed, game, type));
		
	}
	

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
//		if(hp<=0){
//			velY+=gravity*10;
//		}
		
		if (System.currentTimeMillis() - timer0 > 10000) {
			timer0+=10000;
			dropCompost();
			
		}
		
		if(System.currentTimeMillis()-timer2>23000){
			timer2+=23000;
			
			dead();
		}
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.setColor(Color.blue);
			g.fillRect((int)x, (int)y, 32, 32);
			g.setColor(Color.BLACK);
			g.fillRect((int)x, (int)y+32, 32, 64);
			break;
		case 1:
			g.setColor(Color.white);
			g.fillRect((int)x, (int)y, 32, 32);
			g.setColor(Color.BLACK);
			g.fillRect((int)x, (int)y+32, 32, 64);
			
			break;
		}
			
		
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 5);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 96);
	}

}
