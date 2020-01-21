// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// Class used to create Player objects.

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Player extends Entity implements CreateImage {
	
	protected int playerNum; // integer representing the number of the player
	
	protected int lives; // stores the number of lives the player has
	protected int bombBoosts; // stores the number of Bomb Boosts the player picked up
	protected int bombMores; // stores the number of Bomb Mores the player picked up
	
	protected boolean moveBoolean; // boolean tracking whether the player is able to move 
	protected int moveDirection; // represents the direction the player is currently moving in
	
	protected Queue bombQueue; // queue used to store the bombs placed by the player
	
	/*queue used to store bombs that have already detonated
	to track when to remove flames from a detonation*/
	protected Queue detonatedQueue; 

    public Player(int x, int y, int playerNum)
    {
        super(x, y);
        
        this.playerNum = playerNum;
        
        lives = 3;
        bombBoosts = 0;
        bombMores = 0;
        
        moveBoolean = false;
        
        bombQueue = new Queue();
        detonatedQueue = new Queue();
    }
    
    // Method used to create a StackPane of the Player image
    @Override
 	public StackPane createImage(String spaceFile, String playerFile) {
 		
 		StackPane pane = new StackPane();
 		
 		Image tempImage = new Image(spaceFile);
 		ImageView tempImageView = new ImageView(tempImage);
 		tempImageView.setFitHeight(50);
 		tempImageView.setFitWidth(50);
 		
 		Image playerImage = new Image(playerFile);
 		ImageView playerView = new ImageView(playerImage);
 		playerView.setFitHeight(50);
 		playerView.setFitWidth(50);
 		
 		// StackPane used to display the Player on top of the Space image
 		pane.getChildren().addAll(tempImageView, playerView);
 		
 		return pane;
 	}
 	
}
