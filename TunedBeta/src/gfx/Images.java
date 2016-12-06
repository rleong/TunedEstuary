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

	// Tiles
	BufferedImage[] skyTile;

	// Test
	BufferedImage testImg;
	
	// Entities
	final int walkFrameCount = 18;
	final int swimFrameCount = 12;
	BufferedImage[][] blueCrab;

	public Images() {

		// Action Animations
		wateringPlant = new BufferedImage[actionFrameCount];
		for (int i = 0; i < actionFrameCount; i++) {
			BufferedImage img = createImage("actions/WateringPlant.png");
			wateringPlant[i] = img.getSubimage(32 * i, 0, 32, 32);
		}
		buildingAction = new BufferedImage[actionFrameCount];
		for (int i = 0; i < actionFrameCount; i++) {
			BufferedImage img = createImage("actions/Building.png");
			buildingAction[i] = img.getSubimage(32 * i, 0, 32, 32);
		}
		
		// Entities
		blueCrab = new BufferedImage[5][walkFrameCount];
		for (int i = 0; i < 4; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[0][i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[1][i] = img.getSubimage(64 * i, 64, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[2][i] = img.getSubimage(64 * i, 128, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[3][i] = img.getSubimage(64 * i, 192, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[4][i] = img.getSubimage(64 * i, 256, 64, 64);
		}

		// Miscellaneous
		wasteBins = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("misc/WasteBins.png");
			wasteBins[i] = img.getSubimage(64 * i, 0, 64, 64);
		}

		// Tiles
		skyTile = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			BufferedImage img = createImage("tiles/Sky.png");
			skyTile[i] = img.getSubimage(64 * i, 0, 64, 64);
		}

		// Icons
		gabionBuildIcon = createImage("icons/BuildGabionIcon.png");
		menuBar = createImage("icons/MenuBar.png");

		// Test
		testImg = createImage("test/Test.png");
	}

	private BufferedImage createImage(String x) {
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
	public BufferedImage getTestImg() {
		return testImg;
	}

	public BufferedImage getMenuBar() {
		return menuBar;
	}

	public BufferedImage getGabionBuildIcon() {
		return gabionBuildIcon;
	}

	public BufferedImage getBuildingAction(int i) {
		return buildingAction[i];
	}

	public BufferedImage getWateringPlant(int i) {
		return wateringPlant[i];
	}

	public BufferedImage getWasteBins(int i) {
		return wasteBins[i];
	}
	
	public BufferedImage getSkyTiles(int i) {
		return skyTile[i];
	}
	
	public BufferedImage getBlueCrabImage(int i, int j) {
		return blueCrab[i][j];
	}
	
	// Frame Counts
	// For use when other classes want to know how many frames per animation
	public int getActionFrames(){
		return 16;
	}
	
	public int getSwimFrames(){
		return 12;
	}
	
	public int getMoveFrames(){
		return 18;
	}

}
