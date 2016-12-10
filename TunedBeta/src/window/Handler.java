package window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import object.Boat;
import object.Bubble;
import object.LandSurface;
import object.Tree;
import object.Waste;

public class Handler {

	//Attributes
	public ArrayList<GameObject> objectsList = new ArrayList<GameObject>();

	private GameObject temp;
	Game game;
	Images images;

	
	/**
	 * Constructor that constructs a handler object. This class is responsible 
	 * for calling every ojbect's render and tick method in the handler. This is 
	 * prevent the main game's tick and render from being cluttered with multiple calls
	 * 
	 * @param game - game object
	 */
	public Handler(Game game) {
		this.game = game;
	}

	/**
	 * Method that changes variables per call
	 * 	-Calls each individual objects tick  function 
	 * 	-Removes waste is the waste is dead
	 * 	-Removes bubble if bubble is dead
	 * 
	 */
	public void tick() {

		for (int i = 0; i < objectsList.size(); i++) {
			temp = objectsList.get(i);

			temp.tick(objectsList);
		}

		for (int i = 0; i < objectsList.size(); i++) {
			temp = objectsList.get(i);
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (waste.getType() == 2 && waste.checkDeath()) {
					objectsList.remove(temp);
				}
			}
		}
		for (int i = 0; i < objectsList.size(); i++) {
			temp = objectsList.get(i);
			if (temp.getId() == ObjectId.bubble) {
				Bubble bubble = (Bubble) temp;
				if (bubble.getDeath()) {
					objectsList.remove(temp);
				}
			}
		}
	}
	
	/**
	 * Method that removes all the objects from game 1 
	 */
	public void removeGame1(){
		for (int i = 0; i < objectsList.size(); i++){
			temp = objectsList.get(i);
			if (temp.getId() == ObjectId.waste){
				objectsList.remove(i);
			} else if (temp.getId() == ObjectId.boat){
				Boat boat = (Boat) temp;
				boat.removeBoat();
				objectsList.remove(i);
			}
			if(temp.getId() == ObjectId.game3timer)
				objectsList.remove(i);
		}
	}
	/**
	 * Method that removes all the object from game 2
	 */
	public void removeGame2(){
		for (int i=0;i<objectsList.size();i++){
			temp = objectsList.get(i);
			if (temp.getId() == ObjectId.waste){
				objectsList.remove(temp);
			} 
			else if (temp.getId() == ObjectId.compost){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.runOff){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.RofFactory){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.recycle){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.trash){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.recycleBin){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.trashBin){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.waterTree){
				objectsList.remove(temp);
			}
			else if (temp.getId() == ObjectId.tree){
				objectsList.remove(temp);
			}
			
		}
	}

	/**
	 * Method that displays all the object's individual render functions
	 * 
	 * @param g - graphics object
	 */
	public void render(Graphics g) {
		g.setFont(new Font("Calibri", Font.BOLD, 16));
		
		for (int i = 0; i < objectsList.size(); i++) {
			temp = objectsList.get(i);
			temp.pngSelector(g);
		}
	}

	/**
	 * Method that adds an object to the object list
	 * 
	 * @param object - game object to add
	 */
	public void addObject(GameObject object) {
		this.objectsList.add(object);

	}

	/**
	 * Method that removes an Object from the handler
	 * 
	 * @param object - game object to remove
	 */
	public void removeObject(GameObject object) {
		this.objectsList.remove(object);
	}

	/**
	 * Method that creates the world you move in
	 * 
	 * @param dm - dimensions 
	 */
	public void creatSurface(Dimension dm) {
		int i = 0;
		for (; i < ((double)dm.getWidth()) * 5/6; i += 32) {

				addObject(new LandSurface(i, dm.getHeight() * 3 / 5, ObjectId.landSurface, game, images));


		}
		for (double j = ((double)dm.getHeight()) * 3 / 5; j < ((double)dm.getHeight()); j += 32) {
			addObject(new LandSurface(i-32, j, ObjectId.wall, game, images));
		}
		for (; i <= ((double)dm.getWidth())*3/2; i += 32) {
			for (double j = ((double)dm.getHeight()) * 3 / 5; j < ((double)dm.getHeight()) - 64; j += 32) {
				addObject(new LandSurface(i, j, ObjectId.seaLevel, game, images));
			}
			addObject(new LandSurface(i, (double) dm.getHeight() * 3 / 5, ObjectId.waterImageMarker, game, images));

				addObject(new LandSurface(i, dm.getHeight() - 96, ObjectId.sand, game, images));

				
		}
		for (double j = ((double)dm.getHeight())-96; j <((double) dm.getHeight())*6/5; j += 32) {
			addObject(new LandSurface(i-32, j, ObjectId.wall, game, images));
		}
		for (; i <=((double) dm.getWidth())*2; i += 32) {
			for (double j = ((double)dm.getHeight()) * 3 / 5; j <((double) dm.getHeight())*6/5; j += 32) {
				addObject(new LandSurface(i, j, ObjectId.seaLevel, game, images));
			}
			addObject(new LandSurface(i, (double) dm.getHeight() * 3 / 5, ObjectId.waterImageMarker2, game, images));

				addObject(new LandSurface(i, dm.getHeight()*6/5 - 96, ObjectId.sand, game, images));

				
		}
		
	}
	
	/**
	 * Method that sets the images 
	 * 
	 * @param images - images to be
	 */
	public void setImages(Images images){
		this.images = images;
	}

	/**
	 * Method that gets the locations to spawn objects
	 * @param dm
	 * @return
	 */
	public double[] spawnLocations(Dimension dm) {
		
		double[] loc = { dm.getWidth()*2, dm.getHeight(), dm.getWidth() * 5 / 6, dm.getHeight() - 96,
				dm.getHeight() * 3 / 5 };
		return loc;
	}
}
