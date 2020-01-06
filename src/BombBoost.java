public class BombBoost extends Item {

    public BombBoost(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void createImage(String imageFile) {

    }

    @Override
    public void powerUp(Player player) {

    	player.bombBoosts += 1;
    }
}
