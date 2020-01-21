import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bomb extends Entity implements CreateImage {
	
	protected long timer = 0; // tracks how long the bomb has existed to track when to detonate
	
	// tracks how long the detonation has lasted to know when to remove flames
	protected long timerDisappear = 0; 
	
    public Bomb(int x, int y)
    {
        super(x, y);
    }
    
    // Method used to create a StackPane of the Bomb image
    @Override
    public StackPane createImage(String bombFile, String playerFile) {
    	
    	StackPane pane = new StackPane();
 		
 		Image tempImage = new Image("file:Space.png");
 		ImageView tempImageView = new ImageView(tempImage);
 		tempImageView.setFitHeight(50);
 		tempImageView.setFitWidth(50);
 		
 		Image bombImage = new Image(bombFile);
 		ImageView bombView = new ImageView(bombImage);
 		bombView.setFitHeight(50);
 		bombView.setFitWidth(50);
 		
 		Image playerImage = new Image(playerFile);
 		ImageView playerView = new ImageView(playerImage);
 		playerView.setFitHeight(50);
 		playerView.setFitWidth(50);
 		
 		pane.getChildren().addAll(tempImageView, bombView, playerView);
 		
 		return pane;
    }
}
