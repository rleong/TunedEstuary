package window;

import java.awt.Graphics;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.Boat;
import object.Bubble;
import object.Waste;

public class Handler2 {
	//Attributes
	public ArrayList<GameObject> object = new ArrayList<GameObject>();

	private GameObject temp;
	Game game;

	/**
	 * Constructor that constructs a handler object. This class is responsible 
	 * for calling every ojbect's render and tick method in the handler. This is 
	 * prevent the main game's tick and render from being cluttered with multiple calls
	 * 
	 * @param game - game object
	 */
	public Handler2(Game game) {
		this.game = game;
	}
	
	/**
	 * Method that changes variables per call
	 * 	-Calls each individual objects tick  function 
	 * 
	 */
	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);

			temp.tick(object);
		}
	}

	/**
	 * Method that displays all the object's individual render functions
	 * 
	 * @param g - graphics object
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);
			temp.render(g);
		}
	}

	/**
	 * Method that adds an object to the object list
	 * 
	 * @param object - game object to add
	 */
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

	/**
	 * Method that removes an Object from the handler
	 * 
	 * @param object - game object to remove
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
