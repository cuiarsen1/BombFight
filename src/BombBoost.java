// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// This class is used to create Bomb Boost items.

public class BombBoost extends Item {
	
    public BombBoost(int x, int y)
    {
        super(x, y);
    }

    // When the Player picks up a Bomb Boost, the range of their Bombs' detonation will increase
    @Override
    public void powerUp(Player player) {

    	player.bombBoosts += 1;
    }
    
}
