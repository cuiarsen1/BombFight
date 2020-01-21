// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
/*This class is used to store a 2D array of Triple objects. It is used to track the location of 
different objects around the 12x12 grid to detect events and collisions*/

public class Map {
	
	protected Triple[][] mapArray; // 2D array storing object locations
	
	public Map()
	{
		mapArray = new Triple[12][12];
	}

}
