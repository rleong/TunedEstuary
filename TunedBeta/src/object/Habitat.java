package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Habitat extends GameObject{
	
	private double health = 30;
	private final double TRASHDAMAGE = .5;
	private final double COMPOSTDAMAGE = .25;
	private int numberOfHazards = 0;
	private int numberOfCompost = 0;
	private double width;
	private ArrayList<Integer> imageIndexStorage = new ArrayList();
	
	Random random = new Random();
	private int temporaryIndexNumber;
	
	private int stage = 0;
	
	Images images;
	
	Timer clock;

	public Habitat(double x, double y, ObjectId id, Game game, Dimension dm, Images images) {
		super(x, y, id,game);
		width = dm.getWidth()*3/2-dm.getWidth()*.84;
		clock = new Timer(7000, listener);
		clock.start();
		this.images = images;
		int temporaryIndexSize = (int) width / 64;
		for(int i = 0; i < temporaryIndexSize; i++){
			temporaryIndexNumber = random.nextInt(3);
			imageIndexStorage.add((Integer) temporaryIndexNumber);
		}
	}
	
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//collision(object);
			health -= (numberOfHazards * TRASHDAMAGE) + (numberOfCompost * COMPOSTDAMAGE);
			clock.restart();
		}
	};

	@Override
	public void tick(ArrayList<GameObject> object) {
		collision(object);
		if(health >= 30){
			stage = 0;
		}else if (health <= 30 * 2/3 && health > 30 * 1/3){
			stage = 1;
		}else if (health <= 30 * 1/3 && health > 0){
			stage = 2;
		}else if(health<=0){
			clock.stop();
		}
		
	}

	@Override
	public void render(Graphics g) {
		int temporaryXValue = 0;
		for(int i = 0; i < imageIndexStorage.size(); i++){
			g.drawImage(images.getHabitat(stage, imageIndexStorage.get(i)), (int) x + temporaryXValue, (int) y, game);
			temporaryXValue += 64;
		}
//		if(health<=0)
//			g.setColor(Color.RED);
//		else
//			g.setColor(Color.GRAY);
//		g.fillRect((int) x, (int) y, (int)width, 64);
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y-8, (int) ((health / 30) * width), 6);
	}
	
	private void collision(ArrayList<GameObject> object) {
		numberOfHazards = 0;
		numberOfCompost = 0;
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.waste) {
				Waste waste = (Waste) temp;
				if (getBounds().intersects(temp.getBounds()) && !waste.checkDeath() && !waste.getIsTrapped()) {
					if(waste.getType()!=2)
						numberOfHazards += 1;
					else
						numberOfCompost += 1;
				}
			}
		}
	}
	public double getHealth(){
		return health;
	}
	public double getWidth(){
		return width;
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x + 6, (int) y + 26, (int)width, 64);
	}
	public ArrayList<GameObject> testCollision(ArrayList<GameObject> test) {
		collision(test);
		return test;
	}

}
