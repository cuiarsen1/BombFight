// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// This class is used to create Bomb objects when a Player places down a Bomb

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bomb extends Entity implements CreateImage {
	
	protected long timer = 0; // tracks how long the bomb has existed to track when to detonate
	
	// tracks how long the detonation has lasted to know when to remove flames from its detonation
	protected long timerDisappear = 0; 
	
    public Bomb(int x, int y)
    {
        super(x, y);
    }
    
    // Method used to create a StackPane of the Bomb image when a Bomb is created.
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
 		
 		/*When a Bomb is added to the screen, it is placed underneath a Player, so there are 3 
 		layers: the Space, the Bomb, and the Player*/
 		pane.getChildren().addAll(tempImageView, bombView, playerView);
 		
 		return pane;
    }
}
