import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BombMore extends Item {

    public BombMore(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void createImage(String imageFile) {
    	
    	Image bombMoreImage = new Image("BombMore.png");
		
		ImageView bombMoreView = new ImageView(bombMoreImage);
		
		bombMoreViews.add(bombMoreView);
    }

    @Override
    public void powerUp(Player player) {
    	
    	player.bombMores += 1;

    }
}
