package gfx;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Images {
	
	final int actionFrameCount = 16;
	BufferedImage[] wateringPlant;
	BufferedImage[] buildingAction;

	public Images(){
		
		// Action Animations
		wateringPlant = new BufferedImage[actionFrameCount];
		for(int i = 0; i < actionFrameCount; i++){
			BufferedImage img = createImage("actions/WateringPlant.png");
    		wateringPlant[i] = img.getSubimage(32*i, 0, 32, 32);
    	}
		buildingAction = new BufferedImage[actionFrameCount];
		for(int i = 0; i < actionFrameCount; i++){
			BufferedImage img = createImage("actions/Building.png");
			buildingAction[i] = img.getSubimage(32*i, 0, 32, 32);
    	}
		
	}
	
	private BufferedImage createImage(String x){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/" + x));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// Getters and Setters
	public int getActionFrameCount() {
		return actionFrameCount;
	}
	
	public BufferedImage getBuildingAction(int i) {
		return buildingAction[i];
	}

	public void setBuildingAction(BufferedImage[] buildingAction) {
		this.buildingAction = buildingAction;
	}
	
	public BufferedImage getWateringPlant(int i) {
		return wateringPlant[i];
	}

	public void setWateringPlant(BufferedImage[] wateringPlant) {
		this.wateringPlant = wateringPlant;
	}
}
