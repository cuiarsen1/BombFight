public class BombMore extends Item {

    public BombMore(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void powerUp(Player player) {
    	
    	player.bombMores += 1;

    }
}
