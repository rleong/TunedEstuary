package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;

public class Tutorial extends GameObject {
	public long timer = System.currentTimeMillis();
	Runoff rof;
	Compost cp1;
	Compost cp2;
	Compost cp3;
	Compost cp4;
	Waste w1;
	Waste w2;
	WaterTree wt;
	WasteBin wb1;
	WasteBin wb2;
	boolean secondStage = false;
	boolean firstStage = false;
	Images img;
	Critter critter;

	public Tutorial(double x, double y, ObjectId id, Game game, WasteBin wb1, WasteBin wb2, Inventory ivent, Images img,
			Critter critter) {
		super(x, y, id, game);
		this.wb1 = wb1;
		this.wb2 = wb2;
		this.img = img;
		// TODO Auto-generated constructor stub
		rof = new Runoff(0, game.dm.getHeight() * 3 / 5 - 32, game.dm, ObjectId.runOff, 0, game, img);
		w1 = new Waste(128 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.waste, game, wb1, wb2, ivent, 0, img);
		w2 = new Waste(192 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.waste, game, wb1, wb2, ivent, 1, img);
		cp1 = new Compost(256 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.compost1, game, 0, img);
		cp2 = new Compost(320 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.compost1, game, 0, img);
		cp3 = new Compost(384 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.compost1, game, 0, img);
		cp4 = new Compost(448 + 400, game.dm.getHeight() * 3 / 5 - 32, ObjectId.compost1, game, 0, img);
		wt = new WaterTree(448 + 400, game.dm.getHeight() * 3 / 5 - 96, ObjectId.waterTree, 0, game, img);
		// game.handler.addObject(cp1);
		// game.handler.addObject(cp2);
		// game.handler.addObject(cp3);
		// game.handler.addObject(cp4);
		wt.setFalling(false);
		this.critter = critter;
	}

	public void check() {

		if (!firstStage && critter.getX() >= 448 + 135) {
			firstStage = true;
			firstStageAdditions();
		}
		
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);
			if (temp.getId() == ObjectId.tree) {

				// if(System.currentTimeMillis()-timer>3000)
				if (!secondStage) {
					game.handler.object.add(rof);
					secondStage = true;
				}
			}
		}
	}

	public void remove() {
		for (int i = 0; i < game.handler.object.size(); i++) {
			GameObject temp = game.handler.object.get(i);

			if (temp.getId() == ObjectId.tree) {
				Tree tree = (Tree) temp;
				if (tree.hp < 3)
					if (System.currentTimeMillis() - timer > 20000) {
						game.handler.object.remove(temp);
						game.tutorial = false;
						game.game1 = true;
						game.handler.object.remove(this);
						critter.setHealth(0, 100, true);
						critter.setHealth(1, 100, true);
						critter.setHealth(2, 100, true);
						game.getGameTimer().startTimer();
						return;
					}

			}
		}

	}
	
	public void firstStageAdditions(){
		game.handler.addObject(w1);
		game.handler.addObject(w2);
		game.handler.addObject(wt);
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		check();
		remove();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if (!firstStage) {
			g.drawImage(img.getTutorialMove(), 128, (int) game.dm.getHeight() * 3 / 5 - 162, game);
			g.drawImage(img.getTutorialE(), 448 - 150, (int) game.dm.getHeight() * 3 / 5 - 216, game);
			g.drawImage(img.getTutorialQ(), 448, (int) game.dm.getHeight() * 3 / 5 - 216, game);
		}
		if (!secondStage && firstStage) {
			g.drawImage(img.getTutorialSpace(), 128 + 400, (int) game.dm.getHeight() * 3 / 5 - 162, game);
			g.drawImage(img.getTutorialR(),  critter.getWasteX() - 350, critter.getWasteY(), game);
			g.drawImage(img.getArrow(), critter.getWasteX() - 200, critter.getWasteY(), game);
			g.drawImage(img.getTutorialU(), 448 - 35 + 400, (int) game.dm.getHeight() * 3 / 5 - 216, game);
			g.drawImage(img.getTutorialCompost(), 448 + 135 + 400, (int) game.dm.getHeight() * 3 / 5 - 216, game);
		}
		if (secondStage) {
			g.drawImage(img.getTutorialRunoff(), 448 - 135 + 400, (int) game.dm.getHeight() * 3 / 5 - 216, game);
			g.drawImage(img.getTutorialStart(), 448 + 135 + 400, (int) game.dm.getHeight() * 3 / 5 - 216, game);
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
