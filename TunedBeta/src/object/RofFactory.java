package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class RofFactory extends GameObject {
	long timer;
	int count=0;
	Images images;
	
	public RofFactory(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		// TODO Auto-generated constructor stub
		timer=System.currentTimeMillis();
		this.images = images;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub

		if(System.currentTimeMillis()-timer>3300){
			timer=System.currentTimeMillis();
			if(game.g2stage==0){
				if(count>3){
					game.g2stage+=1;
					count=0;
				}
				count+=1;
			}
			if(game.g2stage==1){
				if(game.nW1<5){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 0,game));
					game.nW1++;
					game.nWaste++;
				}
				if(game.nWaste<0){
					game.g2stage++;
					game.nWaste=0;
					game.nW1=0;
				}
			}
			if(game.g2stage==2){
				if(count>3){
					game.g2stage+=1;
					count=0;
					Random random=new Random();
					int xx= random.nextInt(1000) % (int)(game.dm.getWidth()*1.5-game.dm.getWidth()*5/6-32);
					game.handler.addObject(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, game, images));
				}
				count+=1;
				
			}
			if(game.g2stage==3){
				if(game.nW1<5){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 0,game));
					game.nW1++;
					game.nWaste++;
					return;
				}
				if(game.nW1==5){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 1,game));
					game.nW2++;
					if(game.nW2==2){
						game.nW1++;
					}
					game.nWaste++;
					return;
				}
				if(game.nWaste<0){
					game.g2stage++;
					game.nWaste=0;
					game.nW1=0;
					game.nW2=0;
				}
			}
			
				if(game.g2stage==4){
					if(count>5){
						game.g2stage+=1;
						count=0;
						Random random=new Random();
						int xx= random.nextInt(1000) % (int)(game.dm.getWidth()*1.5-game.dm.getWidth()*5/6-32);
						game.handler.addObject(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, game, images));
					}
					count+=1;
				}
			
			if(game.g2stage==5){
				if(game.nW1<3){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 0,game));
					game.nW1++;
					game.nWaste++;
					return;
				}
				if(game.nW1==3){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 1,game));
					game.nW2++;
					if(game.nW2==3){
						game.nW1++;
					}
					game.nWaste++;
					return;
				}
				if(game.nW2==3){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 2, game));
					game.nW3++;
					if(game.nW3==3){
						game.nW2++;
					}
					game.nWaste++;
					return;
				}
				if(game.nWaste<0){
					game.g2stage++;
					game.nWaste=0;
					game.nW1=0;
					game.nW2=0;
					game.nW3=0;
				}
			}
			
				if(game.g2stage==6){
					if(count>7){
						game.g2stage+=1;
						count=0;
					}
					count+=1;
				}
			
			if(game.g2stage==7){
				if(game.nW4<1){
					game.handler.object.add(new Runoff(x, y, game.dm, ObjectId.runOff, 3, game));
					game.nW4++;
					game.nWaste+=0.5;
				}
				if(game.nWaste<0){
					game.g2stage++;
					game.nWaste=0;
					game.nW4=0;
					game.g2stage++;
				}
			}
		}
		
	}
	public void iniTree(){
		for(int i=0; i<3; i++){
			Random random = new Random();
			int xx= random.nextInt(1000) % (int)(game.dm.getWidth()*1.5-game.dm.getWidth()*5/6-32);
			game.handler.object.add(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, game, images));
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

}
