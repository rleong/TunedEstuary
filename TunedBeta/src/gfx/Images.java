package gfx;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Images {
	
	// Actions
	final int actionFrameCount = 16;
	BufferedImage[] wateringPlant;
	BufferedImage[] buildingAction;
	
	// Icons
	BufferedImage gabionBuildIcon;
	BufferedImage menuBar;
	
	// Miscellaneous
	BufferedImage[] wasteBins;
	
	// Test
	BufferedImage testImg;

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
		
		// Miscellaneous
		wasteBins = new BufferedImage[3];
		for(int i = 0; i < 3; i++){
			BufferedImage img = createImage("misc/WasteBins.png");
			wasteBins[i] = img.getSubimage(64*i, 0, 64, 64);
    	}
		
		// Icons
		gabionBuildIcon = createImage("icons/BuildGabionIcon.png");
		menuBar = createImage("icons/MenuBar.png");
		
		// Test
		testImg = createImage("test/Test.png");
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
	
	public BufferedImage getTestImg() {
		return testImg;
	}

	public void setTestImg(BufferedImage testImg) {
		this.testImg = testImg;
	}
	
	public BufferedImage getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(BufferedImage menuBar) {
		this.menuBar = menuBar;
	}
	
	public BufferedImage getGabionBuildIcon() {
		return gabionBuildIcon;
	}

	public void setGabionBuildIcon(BufferedImage gabionBuildIcon) {
		this.gabionBuildIcon = gabionBuildIcon;
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
	
	public BufferedImage getWasteBins(int i) {
		return wasteBins[i];
	}

	public void setWasteBins(BufferedImage[] wasteBins) {
		this.wasteBins = wasteBins;
	}
}
