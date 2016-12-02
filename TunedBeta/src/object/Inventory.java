package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Inventory extends GameObject {

	private int countCompost = 0;
	private int countOyster = 0;
	private int countConcrete = 0;
	private int countSmallSeed = 0;
	private int countBigSeed = 0;
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

	// Compost
	public void addCompost() {
		countCompost++;
	}
	// public void subtractCompost(){
	// countCompost--;
	// }

	// Oyster
	public void addOyster() {
		countOyster++;
	}
	// public void subtractOysterCounter(){
	// countOyster--;
	// }

	// Concrete
	public void addConcrete() {
		countConcrete++;
	}
	// public void subtractConcrete(){
	// countConcrete--;
	// }

	// Small Seed
	public void addSmallSeed() {
		countSmallSeed++;
	}

	// Big Seed
	public void addBigSeed() {
		countBigSeed++;
	}

	@Override
	public void render(Graphics g) {

		// Menu
		if (menuActivation) {

			drawMenu(g);

		}
	}

	public void build(double mx, double my, Game gm) {
		if(menuActivation){
			if (new Rectangle((int) mx, (int) my, (int) mx + 1, (int) my + 1).intersects(getWeakGrass())) {
				critter.planT(0);
				gm.setPause(3000);
			}
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
		g.drawRect((int) critter.getX() - 64, (int) critter.getY() - 144, 148, 115);

		// Compost
		String output0 = "x" + countCompost + " Plant 1";
		g.setColor(Color.white);
		g.drawString(output0, (int) xx + 31, (int) yy + 23);
		g.setColor(Color.orange);
		g.fillRect((int) xx + 10, (int) yy + 10, 16, 16);

		// Oyster Shells
		String output1 = "x" + countOyster + " Plant 2";
		g.setColor(Color.white);
		g.drawString(output1, (int) xx + 31, (int) yy + 43);
		g.setColor(Color.blue);
		g.fillRect((int) xx + 10, (int) yy + 30, 16, 16);

		// Concrete
		String output2 = "x" + countConcrete + " Plant 3";
		g.setColor(Color.white);
		g.drawString(output2, (int) xx + 31, (int) yy + 63);
		g.setColor(Color.red);
		g.fillRect((int) xx + 10, (int) yy + 50, 16, 16);

		// Concrete
		String output3 = "x" + countSmallSeed + " Plant 4";
		g.setColor(Color.white);
		g.drawString(output3, (int) xx + 31, (int) yy + 83);
		g.setColor(Color.gray);
		g.fillRect((int) xx + 10, (int) yy + 70, 16, 16);

		// Concrete
		String output4 = "x" + countBigSeed + " Oyster Gabion";
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
