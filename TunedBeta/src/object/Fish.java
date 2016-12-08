package object;

public class Fish {
	//Attributes
	int hType;
	
	/**
	  * Constructor to create a fish object that will be placed in a school. 
	  * The hazNum parameter is the fishes hazard type which shows how resistant 
	  * the fish is to pollution.
	  * 
	  * @param htype - the pollution number in which the fish cannot survive
	  */ 
	public Fish(int hType){
		this.hType = hType;
	}

	/**
	 * Returns the specified hazard number of the fish.
	 * 
	 * @return the hazard number in an integer
	 */
	public int gethType() {
		return hType;
	}

	/**
	 * A method to set a hazard number for a specific fish.
	 * 
	 * @param htype - the pollution number in which the fish cannot survive
	 */
	public void sethType(int hType) {
		this.hType = hType;
	}
}
