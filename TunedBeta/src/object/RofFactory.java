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
	int count=0;
	public RofFactory(double x, double y, ObjectId id, Handler handler, Game game) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		this.game=game;
		timer=System.currentTimeMillis();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

		if(System.currentTimeMillis()-timer>2500){
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
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 0, game));
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
					handler.addObject(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, handler, game.dm));
				}
				count+=1;
				
			}
			if(game.g2stage==3){
				if(game.nW1<5){
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 0, game));
					game.nW1++;
					game.nWaste++;
					return;
				}
				if(game.nW1==5){
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 1, game));
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
						handler.addObject(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, handler, game.dm));
					}
					count+=1;
				}
			
			if(game.g2stage==5){
				if(game.nW1<3){
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 0, game));
					game.nW1++;
					game.nWaste++;
					return;
				}
				if(game.nW1==3){
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 1, game));
					game.nW2++;
					if(game.nW2==3){
						game.nW1++;
					}
					game.nWaste++;
					return;
				}
				if(game.nW2==3){
					handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 2, game));
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
					if(count>6){
						game.g2stage+=1;
						count=0;
					}
					count+=1;
				}
			
			if(game.g2stage==7){
				handler.object.add(new Runoff(x, y, game.dm, handler, ObjectId.runOff, 4, game));
				game.nW4++;
				game.nWaste++;
				if(game.nWaste<0){
					game.g2stage++;
					game.nWaste=0;
					game.nW4=0;
				}
			}
		}
		
	}
	public void iniTree(){
		for(int i=0; i<3; i++){
			Random random = new Random();
			int xx= random.nextInt(1000) % (int)(game.dm.getWidth()*1.5-game.dm.getWidth()*5/6-32);
			handler.object.add(new WaterTree(game.dm.getWidth()*5/6+xx+32, game.dm.getHeight()-192, ObjectId.waterTree, 0, handler, game.dm));
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
