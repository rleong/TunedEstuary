package window;

import java.awt.Graphics;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.Boat;
import object.Bubble;
import object.Waste;

public class Handler2 {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject temp;
	Game game;

	public Handler2(Game game) {
		this.game = game;
	}
	
	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			temp = object.get(i);

			temp.tick(object);
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
}
