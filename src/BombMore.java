// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// This class is used to create Bomb More items.

public class BombMore extends Item {

    public BombMore(int x, int y)
    {
        super(x, y);
    }
    
    /*When a Player picks up a Bomb More, they will be able to place down 1 more Bomb at a time 
    before the previous ones detonate*/
    @Override
    public void powerUp(Player player) {
    	
    	player.bombMores += 1;

    }
}
