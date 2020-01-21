// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// Class used to create Items (Bomb More or Bomb Boost)

public abstract class Item extends Entity {

    public Item(int x, int y)
    {
        super(x, y);
    }
    
    // Method used to apply a power up to the player
    public abstract void powerUp(Player player);
}
