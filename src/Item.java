public abstract class Item extends Entity {

    public Item(int x, int y)
    {
        super(x, y);
    }

    public abstract void createImage(String imageFile);

    public abstract void powerUp(Player player);


}
