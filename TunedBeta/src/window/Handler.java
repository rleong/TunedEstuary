package window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.Boat;
import object.Bubble;
import object.LandSurface;
import object.Tree;
import object.Waste;

public class Handler {

	public ArrayList<GameObject> object = new ArrayList<GameObject>();
//--
	private GameObject temp;
	Game game;

	public Handler(Game game) {
		this.game = game;
	}

	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);

			temp.tick(object);
		}

		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (waste.getType() == 2 && waste.checkDeath()) {
					object.remove(temp);
				}
			}
		}
		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);
			if (temp.getId() == ObjectId.bubble) {
				Bubble bubble = (Bubble) temp;
				if (bubble.getDeath()) {
					object.remove(temp);
				}
			}
		}
	}
	
	public void removeGame1(){
		for (int i = 0; i < object.size(); i++){
			temp = object.get(i);
			if (temp.getId() == ObjectId.waste){
				object.remove(i);
			} else if (temp.getId() == ObjectId.boat){
				Boat boat = (Boat) temp;
				boat.removeBoat();
				object.remove(i);
			}
			if(temp.getId() == ObjectId.game3timer)
				object.remove(i);
		}
	}
	public void removeGame2(){
		for (int i=0;i<object.size();i++){
			temp = object.get(i);
			if (temp.getId() == ObjectId.waste){
				object.remove(temp);
			} 
			else if (temp.getId() == ObjectId.compost){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.runOff){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.RofFactory){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.recycle){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.trash){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.recycleBin){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.trashBin){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.waterTree){
				object.remove(temp);
			}
			else if (temp.getId() == ObjectId.tree){
				object.remove(temp);
			}
			
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);
			temp.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
//		for (int i = 0; i < this.object.size(); i++) {
//			temp = this.object.get(i);
//			if (temp.getId() == ObjectId.critter && i != this.object.size()-1) {
//				Collections.swap(this.object, i, this.object.size()-1);
//			}
//		} // NEED TO ASK HARVEY ABOUT HOW I CAN MAKE THIS A PRIORITY YET NOT REPEAT ALL The TIME IN THE TICK!!!!!!!!!!!!!!!!!!!!!!!!!! IT IS CAUSING
//		// CLASSCASTEXCEPTION ERRORS!!!!!!!!!!!
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void creatSurface(Dimension dm) {
		int i = 0;
		for (; i < ((double)dm.getWidth()) * 5/6; i += 32) {
//			for (double j = dm.getHeight() * 3 / 5; j < dm.getHeight(); j += 32) {
				addObject(new LandSurface(i, dm.getHeight() * 3 / 5, ObjectId.landSurface, game));
//			}

		}
		for (double j = ((double)dm.getHeight()) * 3 / 5; j < ((double)dm.getHeight()); j += 32) {
			addObject(new LandSurface(i-32, j, ObjectId.wall, game));
		}
		for (; i <= ((double)dm.getWidth())*3/2; i += 32) {
			for (double j = ((double)dm.getHeight()) * 3 / 5; j < ((double)dm.getHeight()) - 64; j += 32) {
				addObject(new LandSurface(i, j, ObjectId.seaLevel, game));
			}
//			for (double j = dm.getHeight() - 96; j < dm.getHeight(); j += 32) {
				addObject(new LandSurface(i, dm.getHeight() - 96, ObjectId.sand, game));
//			}
		}
		for (double j = ((double)dm.getHeight())-96; j <((double) dm.getHeight())*6/5; j += 32) {
			addObject(new LandSurface(i-32, j, ObjectId.wall, game));
		}
		for (; i <=((double) dm.getWidth())*2; i += 32) {
			for (double j = ((double)dm.getHeight()) * 3 / 5; j <((double) dm.getHeight())*6/5; j += 32) {
				addObject(new LandSurface(i, j, ObjectId.seaLevel, game));
			}
//			for (double j = dm.getHeight()*6/5 - 96; j < dm.getHeight()*6/5; j += 32) {
				addObject(new LandSurface(i, dm.getHeight()*6/5 - 96, ObjectId.sand, game));
//			}
		}
		
	}

	public double[] spawnLocations(Dimension dm) {
		// 0 1 2 3 4
		// Width, Height, Water Start Width, Water Bottom Height, Water Surface
		// Height
		double[] loc = { dm.getWidth()*2, dm.getHeight(), dm.getWidth() * 5 / 6, dm.getHeight() - 96,
				dm.getHeight() * 3 / 5 };
		return loc;
	}
}
