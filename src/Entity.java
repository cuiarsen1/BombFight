// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// Class used as base class for different objects (Player, Bomb, and Items (Bomb More, Bomb Boost))

public class Entity {

	// Coordinates of the object on the 12x12 grid
    protected int x;
    protected int y;

    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
