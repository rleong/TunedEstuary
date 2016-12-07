package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;
//
public class Inventory extends GameObject {
	
	private int countOyster = 0;
	private int countPlant1 = 0;
	private int countPlant2 = 0;
	private int countPlant3 = 0;
	
	
	private int countRope = 0;
	private int countWood = 0;
	private int countMiraclePlant = 0;
	private int xx;
	private int yy;
	
	Critter critter;
	Dimension dm;

	// Menu
	boolean menuActivation = false;
	boolean error = false;
	Timer errorTimer;

	public Inventory(double x, double y, ObjectId id, Game game, Dimension dm) {
		super(x, y, id, game);
		this.dm = dm;
		errorTimer = new Timer(5000, listener);
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			error = false;
			errorTimer.stop();
		}
	};

	public void setCritter(Critter critter) {
		this.critter = critter;
		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 144;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 174;

	}

	// Oyster
	public void addOyster() {
		countOyster++;
	}

	// Plant 1
	public void addPlant1() {
		countPlant1++;
	}

	// Plant 2
	public void addPlant2() {
		countPlant2++;
	}

	// Plant 3
	public void addPlant3() {
		countPlant3++;
	}

	// Miracle Plant
	public void addMiraclePlant() {
		countMiraclePlant++;
	}

	@Override
	public void render(Graphics g) {

		// Menu
		if (menuActivation) {

			drawMenu(g);

		}
		

		// Error
		if (error) {
			drawError(g);
		}

	}
	
	public void buildBarrier(Game gm, int type) {
		if (critter.getX() <= dm.getWidth() * .84 - 64 && !error) {
			switch(type){
			case 0:
				critter.planT(0);
				critter.setBuildAnimation(false);
				break;
			case 1:
				critter.planT(0);
				critter.setBuildAnimation(true);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
			gm.setPause(3000);
		} else {
			error = true;
			errorTimer.start();
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	public Rectangle getWeakGrass() {
		return new Rectangle((int) xx + 10, (int) yy + 10, 16, 16);
	}

	// Menu (Currently not using)

	public void drawMenu(Graphics g) {

		// Borders
		g.setColor(Color.WHITE);
		g.drawRect((int) critter.getX() - 64, (int) critter.getY() - 176, 160, 160);

		// Compost
		String output0 = "x" + countOyster + " Oyster Shells";
		g.setColor(Color.white);
		g.drawString(output0, (int) xx + 31, (int) yy + 23);
		g.setColor(Color.orange);
		g.fillRect((int) xx + 10, (int) yy + 10, 16, 16);

		// Oyster Shells
		String output1 = "x" + countPlant1 + " Regular Compost";
		g.setColor(Color.white);
		g.drawString(output1, (int) xx + 31, (int) yy + 43);
		g.setColor(Color.blue);
		g.fillRect((int) xx + 10, (int) yy + 30, 16, 16);

		// Concrete
		String output2 = "x" + countPlant2 + " Good Compost";
		g.setColor(Color.white);
		g.drawString(output2, (int) xx + 31, (int) yy + 63);
		g.setColor(Color.red);
		g.fillRect((int) xx + 10, (int) yy + 50, 16, 16);

		// Concrete
		String output3 = "x" + countPlant3 + " Great Compost";
		g.setColor(Color.white);
		g.drawString(output3, (int) xx + 31, (int) yy + 83);
		g.setColor(Color.gray);
		g.fillRect((int) xx + 10, (int) yy + 70, 16, 16);

		// Concrete
		String output4 = "x" + countMiraclePlant + " Nutritious Compost";
		g.setColor(Color.white);
		g.drawString(output4, (int) xx + 31, (int) yy + 103);
		g.setColor(Color.green);
		g.fillRect((int) xx + 10, (int) yy + 90, 16, 16);
		
		// Concrete
		String output5 = "x" + countRope + " Ropes";
		g.setColor(Color.white);
		g.drawString(output5, (int) xx + 31, (int) yy + 123);
		g.setColor(Color.gray);
		g.fillRect((int) xx + 10, (int) yy + 110, 16, 16);

		// Concrete
		String output6 = "x" + countWood + " Wood";
		g.setColor(Color.white);
		g.drawString(output6, (int) xx + 31, (int) yy + 143);
		g.setColor(Color.CYAN);
		g.fillRect((int) xx + 10, (int) yy + 130, 16, 16);

	}

	public void drawError(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("You cannot build or plant here!", (int) critter.getX() - 64, (int) critter.getY() - 32);
	}

	public void toggleMenu() {
		if (menuActivation)
			menuActivation = false;
		else
			menuActivation = true;
	}
	
	// Oyster
		
	public void addRope() {
			countRope++;
		}
		// adds 1 wood
		public void addWood() {
			countWood++;
		}
		//removes a rope
		public void removeRope() {
			countRope--;
		}
		// Big Seed
		public void removeWood() {
			countWood--;
		}
		//removes 5 oysters(for gabion)
		public void removeOysters(){
			countOyster-=5;
		}
		//returns true if you have enough materials to build gabion
		public boolean buildGabion(){
			if(countRope >=1 && countWood >=1 && countOyster >= 5)
				return true;
			else
				return false;
		}

}
