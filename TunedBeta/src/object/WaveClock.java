package object;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import control.Game;
import window.Handler;

import framework.GameObject;
import framework.ObjectId;
/**
 * 
 * @author justin said
 *
 */
public class WaveClock extends GameObject{
	
	static boolean hard = false;
	int wSummon = 0;
	Random rand = new Random();
	int rand1;
	
	//initializer
	/**
	 * creates a WaveClock object that acts as a timer for when the waves in game 3 will spawn
	 * @param x object's x position
	 * @param y object's y position
	 * @param id object's Id Enum value
	 * @param game Game object
	 */
	public WaveClock(double x, double y, ObjectId id, Game game) {
		super(x, y, id, game);
		wSummon = 0;
		rand1 = rand.nextInt(200)+100;
	}

	@Override
	//summon a wave if wsummon counter is equal to the random
	/**
	 * continuously called to create wave objects when the incrementing value is equal to the random value
	 */
	public void tick(ArrayList<GameObject> object) {
		//wave spawn speed increases to increase difficulty
		if(wSummon == rand1 && hard == true){
			game.summonWave();
			wSummon = 0;//resets counter after wave spawn
			rand1 = rand.nextInt(150)+100;//next random to change spawning rate
		}
		else if(wSummon == rand1){
			game.summonWave();
			wSummon = -200;//resets counter after wave spawn
			rand1 = rand.nextInt(200)+150;//next random to change spawning rate
		}
		wSummon++;//increments wave summon
	}
	@Override
	//nothing to render
	/**
	 * no render
	 */
	public void render(Graphics g) {
	}

	@Override
	//no bounds
	/**
	 * no bounds to return
	 * @return returns null
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
