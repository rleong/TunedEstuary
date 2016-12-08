package object;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import gfx.Images;
import window.Handler;

public class LandSurface extends GameObject {
	Images images;
	
	public LandSurface(double x, double y, ObjectId id, Game game, Images images) {
		super(x, y, id, game);
		this.images = images;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(id==ObjectId.landSurface){
			g.drawImage(images.getSandTiles(0), (int)x, (int)y, game);
			g.drawImage(images.getSandTiles(1), (int)x, (int)y+32, game);
			g.drawImage(images.getSandTiles(2), (int)x, (int)y+64, game);
			g.drawImage(images.getSandTiles(3), (int)x, (int)y+96, game);
			g.drawImage(images.getSandTiles(4), (int)x, (int)y+128, game);
			for(int i = (int) y + 160; i < game.dm.getHeight()*2; i += 32){
				g.drawImage(images.getSandTiles(4), (int)x, (int)i, game);
			}
		}
		if(id==ObjectId.seaLevel){
			switch(game.getCount()){
			case 0:
				g.setColor(Color.CYAN);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 2:
				g.setColor(Color.blue);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 4:
				g.setColor(Color.green);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 6:
				g.setColor(Color.darkGray);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			default:
				g.setColor(Color.black);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			}
			
			
		}
		if(id==ObjectId.wall){
			g.setColor(Color.gray);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.sand){
			g.setColor(Color.ORANGE);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
