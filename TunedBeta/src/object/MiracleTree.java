package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class MiracleTree extends GameObject{
	public int hp;
	private long timer0=System.currentTimeMillis();
	private long timer1=System.currentTimeMillis();
	private long timer2=System.currentTimeMillis();
	public boolean lock;
	int stage = 0;
	Images images;

	public MiracleTree(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		hp=20;
		this.images = images;
	}
	
	public void setTimer(){
		timer0=System.currentTimeMillis();
		timer1=System.currentTimeMillis();
		timer2=System.currentTimeMillis();
	}

	public void dead(){
//		dropSeed();
		dropCompost();
		game.handler.object.remove(this);
		
	}
	public void dropCompost() {
	// TODO Auto-generated method stub
		game.handler.addObject(new Compost(x, y, ObjectId.compost2, game, 1, images));
		
	}
//	public void dropSeed(){
//		game.handler.addObject(new Seed(x,y,ObjectId.seed, game, type, images));
//		
//	}
	

	@Override
	public void tick(ArrayList<GameObject> object) {
		
		x+=velX;
		y+=velY;
//		if(hp<=0){
//			velY+=gravity*10;
//		}
		
		if (System.currentTimeMillis() - timer0 > 5000) {
			timer0+=5000;
			stage++;
		}
		if(System.currentTimeMillis()-timer1>20000){
			stage=0;
			game.handler.object.remove(this);
		}
		if(!lock){
			timer2=System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-timer2>15000){
			timer2+=15000;
			stage=0;
			dead();
			
		}
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(images.getWaterStarGrass(stage),(int)x-16, (int)y+32, game);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 5);
		g.setColor(Color.BLACK);
		g.drawString("Water Stargrass", (int) x - 8, (int) y+108); 
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 96);
	}
}
