package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JButton;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Inventory extends GameObject {

	private int countOyster = 0;
	private int countPlant1 = 0;
	private int countPlant2 = 0;
	private int countPlant3 = 0;
	private int countMiraclePlant = 0;
	private int xx;
	private int yy;
	Critter critter;

	// Menu
	boolean menuActivation = false;

	public Inventory(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id, handler);
	}

	public void setCritter(Critter critter) {
		this.critter = critter;
		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 144;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		xx = (int) critter.getX() - 64;
		yy = (int) critter.getY() - 144;
		
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

	}
	
	public void buildGabion(Game gm) {
		critter.planT(0);
		critter.setBuildAnimation(false);
		gm.setPause(3000);
	}

	public void buildPlant1(Game gm) {
		critter.planT(0);
		critter.setBuildAnimation(true);
		gm.setPause(3000);
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
		g.drawRect((int) critter.getX() - 64, (int) critter.getY() - 144, 160, 115);

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
		
	}

	public void toggleMenu() {
		if (menuActivation)
			menuActivation = false;
		else
			menuActivation = true;
	}

}
