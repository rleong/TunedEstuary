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
	
	// Barriers (Plant 1 = Panic Grass, Plant 2 = Little Bluestem, Plant 3 = Indiangrass)
	BufferedImage[] panicGrass;
	BufferedImage[] littleBlueStem;
	BufferedImage[] indianGrass;
	BufferedImage[] gaBion;
	BufferedImage[] conCrete;
	BufferedImage[] goldenRod;
	
	// Environmental
	BufferedImage[] waterStarGrass;
	BufferedImage[] hornWort;
	BufferedImage plantSeed;

	// Tiles
	BufferedImage[] skyTile;

	// Test
	BufferedImage testImg;

	// Entities
	final int walkFrameCount = 18;
	final int swimFrameCount = 12;
	BufferedImage[][] blueCrab;
	BufferedImage[][] easternOyster;
	BufferedImage[][] horseshoeCrab;

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
		
		// Barriers
		panicGrass = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/PanicGrass.png");
			panicGrass[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		littleBlueStem = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/LittleBlueStem.png");
			littleBlueStem[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		indianGrass = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/Indiangrass.png");
			indianGrass[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		gaBion = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/Gabion.png");
			gaBion[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		conCrete = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/Concrete.png");
			conCrete[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		goldenRod = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("barriers/Goldenrod.png");
			goldenRod[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		
		// Environmental
		waterStarGrass = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("environment/WaterStarGrass.png");
			waterStarGrass[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		hornWort = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			BufferedImage img = createImage("environment/Hornwort.png");
			hornWort[i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		plantSeed = createImage("environment/Seed.png");

		// Entities
		// There are no methods for generating these sprites, only because
		// These critters are unique compared to the rest of the game sprites,
		// And thus they have some parts that are larger, smaller, or require
		// more frames than usual. 
		blueCrab = new BufferedImage[9][walkFrameCount];
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
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[5][i] = img.getSubimage(64 * i, 320, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[6][i] = img.getSubimage(64 * i, 384, 64, 64);
		}
		for (int i = 0; i < 8; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[7][i] = img.getSubimage(64 * i, 448, 64, 64);
		}
		for (int i = 0; i < 2; i++) {
			BufferedImage img = createImage("entities/BlueCrab.png");
			blueCrab[8][i] = img.getSubimage(128 * i, 512, 128, 64);
		}

		easternOyster = new BufferedImage[9][walkFrameCount];
		for (int i = 0; i < 4; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[0][i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[1][i] = img.getSubimage(64 * i, 64, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[2][i] = img.getSubimage(64 * i, 128, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[3][i] = img.getSubimage(64 * i, 192, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[4][i] = img.getSubimage(64 * i, 256, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[5][i] = img.getSubimage(64 * i, 320, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[6][i] = img.getSubimage(64 * i, 384, 64, 64);
		}
		for (int i = 0; i < 8; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[7][i] = img.getSubimage(64 * i, 448, 64, 64);
		}
		for (int i = 0; i < 2; i++) {
			BufferedImage img = createImage("entities/EasternOyster.png");
			easternOyster[8][i] = img.getSubimage(64 * i, 512, 64, 64);
		}

		horseshoeCrab = new BufferedImage[9][walkFrameCount];
		for (int i = 0; i < 4; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[0][i] = img.getSubimage(64 * i, 0, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[1][i] = img.getSubimage(64 * i, 64, 64, 64);
		}
		for (int i = 0; i < walkFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[2][i] = img.getSubimage(64 * i, 128, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[3][i] = img.getSubimage(64 * i, 192, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[4][i] = img.getSubimage(64 * i, 256, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[5][i] = img.getSubimage(64 * i, 320, 64, 64);
		}
		for (int i = 0; i < swimFrameCount; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[6][i] = img.getSubimage(64 * i, 384, 64, 64);
		}
		for (int i = 0; i < 8; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[7][i] = img.getSubimage(64 * i, 448, 64, 64);
		}
		for (int i = 0; i < 2; i++) {
			BufferedImage img = createImage("entities/HorseshoeCrab.png");
			horseshoeCrab[8][i] = img.getSubimage(64 * i, 512, 64, 64);
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
	
//	private BufferedImage createCritterImage(){
//		return null;
//	}

	// Creates a Single Image
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
	
	// Barrier Getters
	
	public BufferedImage getPlant1(int i) {
		return panicGrass[i];
	}
	
	public BufferedImage getPlant2(int i) {
		return littleBlueStem[i];
	}
	
	public BufferedImage getPlant3(int i) {
		return indianGrass[i];
	}
	
	public BufferedImage getGabion(int i) {
		return gaBion[i];
	}
	
	public BufferedImage getConcrete(int i) {
		return conCrete[i];
	}
	
	public BufferedImage getGoldenrod(int i) {
		return goldenRod[i];
	}
	
	// Environmental Getters
	
	public BufferedImage getWaterStarGrass(int i) {
		return waterStarGrass[i];
	}
	
	public BufferedImage getHornwort(int i) {
		return hornWort[i];
	}
	
	public BufferedImage getSeed() {
		return plantSeed;
	}

	// Entity Getters

	public BufferedImage getBlueCrabImage(int i, int j) {
		return blueCrab[i][j];
	}

	public BufferedImage getOysterImage(int i, int j) {
		return easternOyster[i][j];
	}
	
	public BufferedImage getHorseshoeCrabImage(int i, int j) {
		return horseshoeCrab[i][j];
	}

	// Frame Counts
	// For use when other classes want to know how many frames per animation
	public int getActionFrames() {
		return 16;
	}

	public int getSwimFrames() {
		return 12;
	}

	public int getMoveFrames() {
		return 18;
	}

	public int getInteractFrames() {
		return 8;
	}

}
