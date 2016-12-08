package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import window.Handler;

import framework.GameObject;
import framework.ObjectId;

public class Clock extends GameObject{
	
	int timer1 = 0;
	int countdown1 = 0;
	int amount = 0;
	Random rand = new Random();
	WasteBin trashbin;
	WasteBin recyclebin;
	Inventory counter;

	public Clock(double x, double y, ObjectId id, Game game, WasteBin trashbin, WasteBin recyclebin, Inventory counter) {
		super(x, y, id, game);
		this.trashbin=trashbin;
		this.recyclebin=recyclebin;
		this.counter = counter;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		if(timer1==countdown1){
			countdown1 = rand.nextInt(500);
			spawnTrash();
			spawnRecycle();
			timer1 = 0;
		}
		timer1 ++;
		//System.out.println(timer1 + ", "+ countdown1);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		String test = countdown1 + "";
//		g.drawString(test, (int)x, (int)y+20);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void spawnTrash(){
		amount = rand.nextInt(3);
		for(int i = 0; i < amount; i++){
			game.handler.addObject(new Waste(rand.nextInt(600), rand.nextInt(200), ObjectId.trash,game,trashbin, recyclebin, counter, 0));
		}
	}
	
	public void spawnRecycle(){
		amount = rand.nextInt(3);
		for(int i = 0; i < amount; i++){
			game.handler.addObject(new Waste(rand.nextInt(600), rand.nextInt(200), ObjectId.recycle,game, trashbin, recyclebin, counter, 1));
		}
	}

}
