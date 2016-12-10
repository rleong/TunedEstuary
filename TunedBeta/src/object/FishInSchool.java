package object;

public class FishInSchool {
	//Attributes
	int hazardNum;
	
	/**
	  * Constructor to create a fish object that will be placed in a school. 
	  * The hazNum parameter is the fishes hazard type which shows how resistant 
	  * the fish is to pollution.
	  * 
	  * @param htype - the pollution number in which the fish cannot survive
	  */ 
	public FishInSchool(int hType){
		this.hazardNum = hType;
	}

	/**
	 * Returns the specified hazard number of the fish.
	 * 
	 * @return the hazard number in an integer
	 */
	public int getHazNum() {
		return hazardNum;
	}

	/**
	 * A method to set a hazard number for a specific fish.
	 * 
	 * @param htype - the pollution number in which the fish cannot survive
	 */
	public void setHazType(int hType) {
		this.hazardNum = hType;
	}
}
