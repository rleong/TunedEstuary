package gfx;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Images {
	//Attributes
	
	// Actions
	final int actionFrameCount = 16;
	BufferedImage[] wateringPlant;
	BufferedImage[] buildingAction;
	
	// Items
	BufferedImage[][] hazardWaste;

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
	BufferedImage[][] habitat;
	
	// Tiles
	BufferedImage[] skyTile;
	BufferedImage[] sandTile;
	BufferedImage[] grassTile;
	BufferedImage[][] waterTile;

	// Test
	BufferedImage testImg;

	// Entities
	final int walkFrameCount = 18;
	final int swimFrameCount = 12;
	BufferedImage[][] blueCrab;
	BufferedImage[][] easternOyster;
	BufferedImage[][] horseshoeCrab;
	BufferedImage[] guardianFish;
	
	
	/**
	 * Constructor that creates a images object which initializes all the attributes to have animations.  
	 */
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
		habitat = new BufferedImage[3][4];
		for (int i = 0; i < 4; i++) {  // i = horizontal frames, j = vertical frames
			for (int j = 0; j < 3; j++){
				BufferedImage img = createImage("environment/Habitat.png");
				habitat[j][i] = img.getSubimage(64 * i, 64 * j, 64, 64);
			}
		}
		
		// Items 
		hazardWaste = new BufferedImage[4][4];
		for (int i = 0; i < 4; i++) {  // i = horizontal frames, j = vertical frames
			for (int j = 0; j < 4; j++){
				BufferedImage img = createImage("items/Waste.png");
				hazardWaste[j][i] = img.getSubimage(32 * i, 32 * j, 32, 32);
			}
		}

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
		
		guardianFish = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			BufferedImage img = createImage("entities/GuardianFish.png");
			guardianFish[i] = img.getSubimage(64 * i, 0, 64, 64);
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
		sandTile = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			BufferedImage img = createImage("tiles/SandTiles.png");
			sandTile[i] = img.getSubimage(32 * i, 0, 32, 32);
		}
		grassTile = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			BufferedImage img = createImage("tiles/GrassTiles.png");
			grassTile[i] = img.getSubimage(32 * i, 0, 32, 32);
		}
		waterTile = new BufferedImage[6][3];
		for (int i = 0; i < 6; i++) {  // i = horizontal frames, j = vertical frames
			for (int j = 0; j < 3; j++){
				BufferedImage img = createImage("tiles/WaterTiles.png");
				waterTile[i][j] = img.getSubimage(32 * i, 32 * j, 32, 32);
			}
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
	/**
	 * Method that gets an image from a file
	 * 
	 * @param x - name of the file path
	 * @return - image on file
	 */
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

	/**
	 * Method to get an image
	 * 
	 * @return image
	 */
	public BufferedImage getTestImg() {
		return testImg;
	}

	/**
	 * Method to get the menu 
	 * 
	 * @return image
	 */
	public BufferedImage getMenuBar() {
		return menuBar;
	}

	/**
	 * Method that gets the gabion build icon 
	 * 
	 * @return image of gabion icon
	 */
	public BufferedImage getGabionBuildIcon() {
		return gabionBuildIcon;
	}

	/**
	 * Method that returns the building animation when constructing an object
	 * 
	 * @param i - image at ith position in list 
	 * @return image of building 
	 */
	public BufferedImage getBuildingAction(int i) {
		return buildingAction[i];
	}
	
	/**
	 * Method that returns the building animation when planting a tree
	 * 
	 * @param i - image at ith position in list
	 * @return image of planting 
	 */
	public BufferedImage getWateringPlant(int i) {
		return wateringPlant[i];
	}

	/**
	 * Method that returns the images for the waste bins 
	 * 
	 * @param i - image at ith position in list
	 * @return image of bin
	 */
	public BufferedImage getWasteBins(int i) {
		return wasteBins[i];
	}

	/**
	 * Method that returns the sky tiles 
	 * 
	 * @param i - image at ith position in list
	 * @return sky image
	 */
	public BufferedImage getSkyTiles(int i) {
		return skyTile[i];
	}
	
	/**
	 * Method that returns the sand tiles 
	 * 
	 * @param i - image at ith position in list
	 * @return sand image
	 */
	public BufferedImage getSandTiles(int i) {
		return sandTile[i];
	}
	
	/**
	 * Method that returns the grass tiles 
	 * 
	 * @param i - image at ith position in list
	 * @return grass image
	 */
	public BufferedImage getGrassTiles(int i) {
		return grassTile[i];
	}
	
	/**
	 * Method that returns the water tiles 
	 * 
	 * @param i - image at ith position in list
	 * @param j - image at jth position in list
	 * @return water image
	 */
	public BufferedImage getWaterTiles(int i, int j) {
		return waterTile[i][j];
	}
	
	/**
	 * Method that returns the plant 1 image
	 * 
	 * @param i - image at ith position in list
	 * @return grass image
	 */
	public BufferedImage getPlant1(int i) {
		return panicGrass[i];
	}
	
	/**
	 * Method that returns the plant 2 image
	 * 
	 * @param i - image at ith position in list
	 * @return plant2 image
	 */
	public BufferedImage getPlant2(int i) {
		return littleBlueStem[i];
	}
	
	/**
	 * Method that returns the plant 3 image
	 * 
	 * @param i - image at ith position in list
	 * @return plant3 image
	 */
	public BufferedImage getPlant3(int i) {
		return indianGrass[i];
	}
	
	/**
	 * Method that returns the gabion image
	 * 
	 * @param i - image at ith position in list
	 * @return gabion image
	 */
	public BufferedImage getGabion(int i) {
		return gaBion[i];
	}
	
	/**
	 * Method that returns the concrete image
	 * 
	 * @param i - image at ith position in list
	 * @return concrete image
	 */
	public BufferedImage getConcrete(int i) {
		return conCrete[i];
	}
	
	/**
	 * Method that returns the goldenrod image 
	 * 
	 * @param i - image at ith position in list
	 * @return goldenrod image
	 */
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
	
	public BufferedImage getHabitat(int i, int j) {
		return habitat[i][j];
	}
	
	// Item Getters
	
	public BufferedImage getWaste(int i, int j) {
		return hazardWaste[i][j];
	}

	// Entity Getters

	/**
	 * Method that returns the blue crab image
	 *  
	 * @param i - image at ith position in list
	 * @param j - image at jth position in list
	 * @return crab image
	 */
	public BufferedImage getBlueCrabImage(int i, int j) {
		return blueCrab[i][j];
	}

	/**
	 * Method that returns the oyster image
	 *  
	 * @param i - image at ith position in list
	 * @param j - image at jth position in list
	 * @return oyster image
	 */
	public BufferedImage getOysterImage(int i, int j) {
		return easternOyster[i][j];
	}
	
	/**
	 * Method that returns the horseshoe crab image
	 *  
	 * @param i - image at ith position in list
	 * @param j - image at jth position in list
	 * @return crab image
	 */
	public BufferedImage getHorseshoeCrabImage(int i, int j) {
		return horseshoeCrab[i][j];
	}
	
	/**
	 * Method that returns the guardian fish image
	 *  
	 * @param i - image at ith position in list
	 * @return crab image
	 */
	public BufferedImage getGuardianFish(int i) {
		return guardianFish[i];
	}

	// Frame Counts
	// For use when other classes want to know how many frames per animation
	/**
	 * Method that used to get the number of frames per animation of actions 
	 * @return frame count 
	 */
	public int getActionFrames() {
		return 16;
	}

	/**
	 * Method that used to get the number of frames per animation of swimming
	 * @return frame count 
	 */
	public int getSwimFrames() {
		return 12;
	}

	/**
	 * Method that used to get the number of frames per animation of moving 
	 * @return frame count 
	 */
	public int getMoveFrames() {
		return 18;
	}

	/**
	 * Method that used to get the number of frames per animation of interactions 
	 * @return frame count 
	 */
	public int getInteractFrames() {
		return 8;
	}

}
