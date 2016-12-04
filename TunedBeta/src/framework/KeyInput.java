package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import control.Game;
import object.Critter;
import object.Inventory;
import window.Handler;

public class KeyInput extends KeyAdapter {
	Handler handler;
	Game gm;

	public KeyInput(Handler handler, Game gm) {
		this.handler = handler;
		this.gm = gm;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (!gm.isPause()) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);

				if (temp.getId() == ObjectId.critter) {
					Critter t = (Critter) temp;
					if (key == KeyEvent.VK_A) {
						t.setVelX(-2);
					}
					if (key == KeyEvent.VK_D) {
						t.setVelX(2);
					}
					if (key == KeyEvent.VK_W && !t.jump) {
						t.setVelY(-2);
						t.falling = true;
						if (!t.inWater) {
							t.jump = true;
						}
						t.onLand = false;

					}
					if (key == KeyEvent.VK_S && !((Critter) temp).onLand) {
						temp.setVelY(2);
					}

				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (!gm.isPause()) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);
				if (temp.getId() == ObjectId.critter) {
					Critter t = (Critter) temp;
					// Movements
					if (key == KeyEvent.VK_A) {
						temp.setVelX(0);
						t.setLeft();
					}
					if (key == KeyEvent.VK_D) {
						temp.setVelX(0);
						t.setRight();
					}
					if (key == KeyEvent.VK_W) {
						temp.setVelY(0);
					}
					if (key == KeyEvent.VK_S) {
						temp.setVelY(0);
					}
					// Attack
					if (key == KeyEvent.VK_SPACE) {
						t.attack(handler.object);
					}
					// Change Characters
					if (key == KeyEvent.VK_E) {
						t.changeCharacter();
					}
					// Plant
					if (key == KeyEvent.VK_P) {
						t.planT(0);
					}
					// Ability
					if (key == KeyEvent.VK_Q) {
						t.ability();
					}
					// Debug Toggle
					if (key == KeyEvent.VK_M) {
						t.setDebug();
					}
				}
				if (temp.getId() == ObjectId.inventory) {
					Inventory inv = (Inventory) temp;
					// Menu Toggle 
					if (key == KeyEvent.VK_R) {
						inv.toggleMenu();
					}
					if (key == KeyEvent.VK_Y) {
						inv.buildBarrier(gm, 0);
					}
					if (key == KeyEvent.VK_U) {
						inv.buildBarrier(gm, 1);
					}
					if (key == KeyEvent.VK_I) {
						
					}
					if (key == KeyEvent.VK_O) {
						
					}
					if (key == KeyEvent.VK_P) {
						
					}
				}
			}
		}

	}
}
