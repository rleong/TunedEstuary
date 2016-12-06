package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import control.Game;
import window.Handler;

import framework.GameObject;
import framework.ObjectId;
public class WaveClock extends GameObject{
	
	static boolean hard = false;
	int wSummon = 0;
	Random rand = new Random();
	int rand1;
	
	//initializer
	public WaveClock(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		wSummon = 0;
		rand1 = rand.nextInt(200)+100;
	}

	@Override
	//summon a wave if wsummon counter is equal to the random
	public void tick(LinkedList<GameObject> object) {
		//wave spawn speed increases to increase difficulty
		if(wSummon == rand1 && hard == true){
			game.summonWave();
			wSummon = 0;//resets counter after wave spawn
			rand1 = rand.nextInt(150)+100;//next random to change spawning rate
		}
		else if(wSummon == rand1){
			game.summonWave();
			wSummon = -300;//resets counter after wave spawn
			rand1 = rand.nextInt(200)+100;//next random to change spawning rate
		}
		wSummon++;//increments wave summon
	}
	@Override
	//nothing to render
	public void render(Graphics g) {
	}

	@Override
	//no bounds
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
