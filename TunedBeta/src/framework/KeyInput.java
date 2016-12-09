package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import control.Game;
import object.Critter;
import object.Inventory;
import window.Handler;

public class KeyInput extends KeyAdapter {
	//Attributes
	Handler handler;
	Game gm;
	Handler handler2;
	
	/**
	 * Constructor that constructs a key input that will read the keyboard
	 * and do actions for different keys.
	 * 
	 * @param handler - list of game objects
	 * @param handler2 - list of game objects
	 * @param gm - game 
	 */
	public KeyInput(Handler handler,Handler handler2, Game gm) {
		this.handler = handler;
		this.gm = gm;
		this.handler2=handler2;
	}

	
	/**
	 * Method that does certain actions when certain keys are pressed
	 * 	-if the 'A' key is pressed move character to the left 2 positions over and do animation 1
	 * 	-if the 'D' key is pressed move character to the right 2 positions over and do animation 2
	 *  -if the 'W' key is pressed move the character 2 positions upward and set falling to true;
	 *  -if the 'W' key is pressed and in water then do animation 3
	 *  -if the 'S' key is pressed and in water then move the character 2 positions downward 
	 *  -if pressing 'S' key in water do animation 4
	 * 	-if escape key is pressed exit the game
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (!gm.isPause()) {
			for (int i = 0; i < handler2.object.size(); i++) {
				GameObject temp = handler2.object.get(i);

				if (temp.getId() == ObjectId.critter) {
					Critter t = (Critter) temp;
					if (key == KeyEvent.VK_A) {
						t.setVelX(-2);
						if(t.getInWater())
							t.setAnimation(5);
						else
							t.setAnimation(1);
					}
					if (key == KeyEvent.VK_D) {
						t.setVelX(2);
						if(t.getInWater())
							t.setAnimation(6);
						else
							t.setAnimation(2);
					}
					if (key == KeyEvent.VK_W && !t.jump) {
						t.setVelY(-2);
						t.falling = true;
						if (!t.inWater) {
							t.jump = true;
						}
						t.onLand = false;
					}
					if (key == KeyEvent.VK_W && t.getInWater()) {
						t.setAnimation(3);
					}
					if (key == KeyEvent.VK_S && !((Critter) temp).onLand) {
						temp.setVelY(2);
					}
					if (key == KeyEvent.VK_S && t.getInWater()) {
						t.setAnimation(4);
					}
					if (key == KeyEvent.VK_SPACE) {
						t.setAnimation(8);
					}
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	/**
	 * Method that does certain actions when certain keys are released
	 * 	-if the 'A' key is released then don't move the critter in the x position. If in the water do animation 4 else animation 0 
	 * 	-if the 'D' key is released then don't move the critter in the x position. If in the water do animation 4 else animation 0
	 * 	-if the 'W' key is released then don't move the critter in the y position. If in the water do animation 3 
	 * 	-if the 'S' key is released then don't move the critter in the y position. If in the water do animation 4
	 * 	-if the 'Space' key is released have the critter attack 
	 * 	-if the 'E' key is released then switch critters
	 * 	-if the 'P' key is released plant tree object
	 *  -if the 'G' key is released plant construct gabion object
	 *  -if the 'Q' key is released use the special ability of the critter
	 *  -if the 'R' key is released open up the inventory 
	 *  -if the 'Y' key is released build a barrier
	 *  -if the 'U' key is released build a barrier
	 *  -if the 'I' key is released build a barrier
	 *  -if the 'O' key is released build a barrier
	 *  -if the 'P' key is released build a barrier
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (!gm.isPause()) {
			for (int i = 0; i < handler2.object.size(); i++) {
				GameObject temp = handler2.object.get(i);
				if (temp.getId() == ObjectId.critter) {
					Critter t = (Critter) temp;
					// Movements
					if (key == KeyEvent.VK_A) {
						temp.setVelX(0);
						t.setLeft();
						if(t.getInWater())
							t.setAnimation(5);
						else
							t.setAnimation(0);
					}
					if (key == KeyEvent.VK_D) {
						temp.setVelX(0);
						t.setRight();
						if(t.getInWater())
							t.setAnimation(6);
						else
							t.setAnimation(0);
					}
					if (key == KeyEvent.VK_W) {
						temp.setVelY(0);
						if(t.getInWater())
							t.setAnimation(3);
					}
					if (key == KeyEvent.VK_S) {
						temp.setVelY(0);
						if(t.getInWater())
							t.setAnimation(4);
					}
					// Attack 
					if (key == KeyEvent.VK_SPACE) {
						t.attack(handler.object);
						t.setAnimation(0);
					}
					// Change Characters
					if (key == KeyEvent.VK_E) {
						t.changeCharacter();
					}
					// Plant or Build
					if (key == KeyEvent.VK_G) {
						t.plantGabion();
					}
					// Ability 
					if (key == KeyEvent.VK_Q) {
						t.ability();
					}
					// Debug Toggle
					if (key == KeyEvent.VK_M) {
						t.setDebug();
					}
					if (key == KeyEvent.VK_R) {
						//inv.toggleMenu();
						t.setTrash();
					}
				}
			}
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);
				
				if (temp.getId() == ObjectId.inventory) {
					Inventory inv = (Inventory) temp;
					// Menu Toggle
					
					if (key == KeyEvent.VK_Y) {
						inv.buildBarrier(gm, 0);
					}
					if (key == KeyEvent.VK_U) {
						inv.buildBarrier(gm, 1);
					}
					if (key == KeyEvent.VK_I) {
						inv.buildBarrier(gm, 2);
					}
					if (key == KeyEvent.VK_O) {
						inv.buildBarrier(gm, 3);
					}
					if (key == KeyEvent.VK_P) {
						inv.buildBarrier(gm, 4);
					}
				}
			}
		
		}

	}
}
