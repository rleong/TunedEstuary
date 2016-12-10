package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class Tree extends GameObject {
	//Attributes
	public int hp;
	public int type;
	private int stage = 0;
	Images images;
	Timer buildStage;

	/**
	 * Method that constructs a tree object. This tree object will block 
	 * incoming run-off heading towards the water in game2
	 * 
	 * @param x - x position of tree
	 * @param y - y position of tree
	 * @param id - object id to be read in handler
	 * @param type - type of tree being planted
	 * @param game - game object
	 * @param images - image of tree
	 * 
	 */
	public Tree(double x, double y, ObjectId id, int type, Game game, Images images) {
		super(x, y, id, game);
		hp = 3;
		this.type = type;
		this.images = images;
		buildStage = new Timer(1000, listener);
		buildStage.start();
	}

	// Timer for the render of the build stage of plant
	/**
	 * Method that executes code after a certain action
	 * 	-show decay in plants
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(stage == 2)
				buildStage.stop();
			else{
				buildStage.restart();
				stage++;
			}			
		}
	};


	/**
	 * Method that calls the collision function per call
	 */
	@Override
	public void tick(ArrayList<GameObject> object) {

		collision(object);
	}

	/**
	 * Method that checks if certain object intersect the tree and do 
	 * certain actions when so
	 * 	- If run-off collides with the tree: subtract health and remove the run-off from the game
	 * @param object - list of objects in the game
	 */
	private void collision(ArrayList<GameObject> object) {
		GameObject temp;
		for (int i=0; i<object.size();i++) {
			temp = object.get(i);
			if (temp.getId() == ObjectId.runOff) {
				Runoff rof = (Runoff) temp;
				if (getBounds().intersects(temp.getBounds())) {
					if (this.type == rof.type) {
						game.nWaste-=1.005;
						hp -= 1;
						object.remove(temp);
						if(hp<=0)
							object.remove(this);
					}
				}
			}
		}
	}

	/**
	 * Method that displays images of the tree depending on what type of tree
	 */
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		switch (type) {
		case 0:
			g.drawImage(images.getPlant1(stage), (int) x - 16, (int) y - 32, game);
			g.drawString("Panic Grass", (int) x - 8, (int) y + 32 + 8);
			break;
		case 1:
			g.drawImage(images.getPlant2(stage), (int) x - 16, (int) y - 32, game);
			g.drawString("Little Bluestem", (int) x - 16, (int) y + 32 + 8);
			break;
		case 2:
			g.drawImage(images.getPlant3(stage), (int) x - 16, (int) y - 32, game);
			g.drawString("Indiangrass", (int) x - 8, (int) y + 32 + 8);
			break;
		case 3:
			g.drawImage(images.getGoldenrod(stage), (int) x - 16, (int) y - 32, game);
			g.drawString("Wrinkled Leaf Goldenrod", (int) x - 24, (int) y + 32 + 8);
			break;
			
		}

		g.setColor(Color.red);
		g.fillRect((int) x - 16, (int) y - 42, (int) (hp * 64 / 3), 2);
	}

	/**
	 * Returns the boundary of the tree
	 * Used to tell if to touches another object
	 */
	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x - 16, (int) y - 32, 64, 64);
	}

	/**
	 * Method that gets the current stage 
	 * 
	 * @return stage
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * Method that sets the stage 
	 * 
	 * @param stage - stage to be
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}

}
