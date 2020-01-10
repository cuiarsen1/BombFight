import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BombMore extends Item {

    public BombMore(int x, int y)
    {
        super(x, y);
    }

    @Override
    public StackPane createImage(String imageFile) {
    	
    	Image bombMoreImage = new Image("BombMore.png");
		
		ImageView bombMoreView = new ImageView(bombMoreImage);
    }

    @Override
    public void powerUp(Player player) {
    	
    	player.bombMores += 1;

    }
}
