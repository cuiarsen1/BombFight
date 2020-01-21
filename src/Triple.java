// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
/*This class is used to create Triple objects. It contains 3 things: The type of the object stored
as an integer, the object itself, and a boolean tracking whether a Bomb exists in this coordinate. 
The Map 2D Array stores Triples to help track the location of different objects.

The integer types are represented by:

0 = Space
1 = Crate
2 = Player
3 = Bomb More
4 = Bomb Boost
5 = Flame*/

public class Triple {
	
    protected int type; // The type of the object
    protected Entity object; // The object itself
    protected boolean bombExist; // tracks whether a bomb exists in this grid. 

    public Triple(int type, Entity object, boolean bombExist) {
        this.type = type;
        this.object = object;
        this.bombExist = bombExist;
    }
}
