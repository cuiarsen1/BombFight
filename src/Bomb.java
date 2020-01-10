import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bomb extends Entity {
	
    public Bomb(int x, int y)
    {
        super(x, y);
    }
    
    // Method used to create a StackPane of the Bomb image
    public StackPane createImage(String bombFile) {
    	
    	StackPane pane = new StackPane();
 		
 		Image tempImage = new Image("file:Space.png");
 		ImageView tempImageView = new ImageView(tempImage);
 		tempImageView.setFitHeight(50);
 		tempImageView.setFitWidth(50);
 		
 		Image bombImage = new Image(bombFile);
 		ImageView bombView = new ImageView(bombImage);
 		bombView.setFitHeight(50);
 		bombView.setFitWidth(50);
 		
 		pane.getChildren().addAll(tempImageView, bombView);
 		
 		return pane;
    }
}
