import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class Player extends Entity {
	
	protected int playerNum; // integer representing the number of the player
	
	protected int lives; // stores the number of lives the player has
	protected int bombBoosts; // stores the number of Bomb Boosts the player picked up
	protected int bombMores; // stores the number of Bomb Mores the player picked up
	
	protected boolean moveBoolean; // boolean tracking whether the player is able to move 
	protected int moveDirection; // represents the direction the player is currently moving in
	
	protected Queue bombQueue; // queue used to store the bombs placed by the player

    public Player(int x, int y, int playerNum)
    {
        super(x, y);
        
        this.playerNum = playerNum;
        
        lives = 3;
        bombBoosts = 0;
        bombMores = 0;
        
        moveBoolean = false;
        
        bombQueue = new Queue();
    }
    
    // Method used to create a StackPane of the Player image
 	public StackPane createImage(String playerFile) {
 		
 		StackPane pane = new StackPane();
 		
 		Image tempImage = new Image("file:Space.png");
 		ImageView tempImageView = new ImageView(tempImage);
 		tempImageView.setFitHeight(50);
 		tempImageView.setFitWidth(50);
 		
 		Image playerImage = new Image(playerFile);
 		ImageView playerView = new ImageView(playerImage);
 		playerView.setFitHeight(50);
 		playerView.setFitWidth(50);
 		
 		pane.getChildren().addAll(tempImageView, playerView);
 		
 		return pane;
 	}
 	
}
