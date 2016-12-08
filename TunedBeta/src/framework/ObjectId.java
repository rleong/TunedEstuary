package framework;

/**
 * Enum class that has all the ids to object so that we can find them quickly in our handler
 *
 */
public enum ObjectId {
	// Old Stuff
	recycle(), trashBin(), recycleBin(), trash(), compost(),

	// Unimplemented
	waterWaste(), 

	// Drop Items
	oyster(), concreteSlab, seed(),

	// Enemies
	bird(), fish(),

	// Environment
	land(), landSurface(), habitat(), seaLevel(), sand(),

	water(), waterSurface(), waterTree(), 

	// Hazards
	waves(), runOff(), waste(), bubble(),

	// Waste Deposits
	wasteBin(),
	
	// Barriers
	tree(), wall(),
	
	// Hazard Spawners
	clock(), boat(), RofFactory(),

	// Player
	inventory(), critter(), compost1, school, guardian, barrier, gameover, wclock, person, instr3, rope, wood, ptrash, estuary, game3timer, gabion, gamewin, compost2, MiracleTree;

}
