package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Tutorial extends GameObject {
	public long timer = System.currentTimeMillis();
	Runoff rof;
	Compost cp1;
	Compost cp2;
	Compost cp3;
	Compost cp4;
	Waste w1;
	Waste w2;

	WasteBin wb1;
	WasteBin wb2;
	boolean lock = false;
	public Tutorial(double x, double y, ObjectId id, Game game,WasteBin wb1,WasteBin wb2, Inventory ivent, Images img) {
		super(x, y, id, game);
		this.wb1=wb1;
		this.wb2=wb2;
		// TODO Auto-generated constructor stub
		rof=new Runoff(0, game.dm.getHeight() * 3 / 5 - 32, game.dm, ObjectId.runOff, 0, game, img);
		w1  = new Waste(128, game.dm.getHeight() * 3 / 5 -32, ObjectId.waste, game, wb1, wb2, ivent, 0, img);
		w2 = new Waste(192, game.dm.getHeight() * 3 / 5-32, ObjectId.waste, game, wb1, wb2, ivent, 1, img);
		cp1 = new Compost(256, game.dm.getHeight() * 3 / 5-32, ObjectId.compost1, game, 0, img);
		cp2 = new Compost(320, game.dm.getHeight() * 3 / 5-32, ObjectId.compost1, game, 0, img);
		cp3 = new Compost(384, game.dm.getHeight() * 3 / 5-32, ObjectId.compost1, game, 0, img);
		cp4 = new Compost(448, game.dm.getHeight() * 3 / 5-32, ObjectId.compost1, game, 0, img);
		
		game.handler.addObject(cp1);
		game.handler.addObject(cp2);
		game.handler.addObject(cp3);
		game.handler.addObject(cp4);
		game.handler.addObject(w1);
		game.handler.addObject(w2);
		
		
	}
	public void check(){
		for(int i=0; i<game.handler.object.size(); i++){
			GameObject temp= game.handler.object.get(i);
			if(temp.getId()==ObjectId.tree){
				
//				if(System.currentTimeMillis()-timer>3000)
				if(!lock){
					game.handler.object.add(rof);
					lock=true;
				}
			}
		}
	}
	public void remove(){
		for(int i=0; i<game.handler.object.size(); i++){
			GameObject temp= game.handler.object.get(i);
			
			if(temp.getId()==ObjectId.tree){
				Tree tree= (Tree)temp;
				if(tree.hp<3)
					if(System.currentTimeMillis()-timer>20000){
						game.handler.object.remove(temp);
						game.currency=25;
						game.tutorial=false;
						game.game1=true;
						game.handler.object.remove(this);
						return;
					}
					
			}
		}
		
	}
	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		check();
		remove();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
