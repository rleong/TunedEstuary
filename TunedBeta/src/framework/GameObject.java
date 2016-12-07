package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import window.Handler;

public abstract class GameObject {
	protected ObjectId id;
	protected double x,y;
	protected double velX = 0, velY = 0;
	protected boolean falling=true;
	protected double gravity =0.055f;
	protected Game game;
	
	public GameObject(double x, double y, ObjectId id, Game game) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.game=game;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public double getX() {
		return x;
	}
	public  double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y=y;
	}
	
	
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	
	public void setVelX(double x) {
		this.velX = x;
	}
	public void setVelY(double y) {
		this.velY = y;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public void setGravity(double gravity){
		this.gravity=gravity;
	}
	
	public double getGravity(){
		return this.gravity;
	}
	
	public void setFalling(boolean b){
		this.falling=b;
	}
	public boolean getFalling(){
		return this.falling;
	}

}
