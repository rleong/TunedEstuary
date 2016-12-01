package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

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

	public WaterTree(double x, double y, ObjectId id,int type, Handler handler, Dimension dm) {
		super(x, y, id, handler);
		this.dm=dm;
		hp=20;
		
		this.type=type;
	}
//	public void change(){
//		type+=1;
//		type=type%2;
//	}

	public void dead(){
			
		canAttack=false;
		dropCompost();
		
		
	}
	public void dropCompost() {
	// TODO Auto-generated method stub
	
}
	public void dropSeed(){
		handler.addObject(new Seed(x,y+64,ObjectId.seed, handler, type));
		
	}
	

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
//		if(hp<=0){
//			velY+=gravity*10;
//		}
		
		if (System.currentTimeMillis() - timer0 > 5000) {
			timer0+=5000;
			dropCompost();
			
		}
		if(System.currentTimeMillis()-timer1 >10000){
			timer1+=10000;
			dropSeed();
		}
		if(System.currentTimeMillis()-timer2>15000){
			timer2+=15000;
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
