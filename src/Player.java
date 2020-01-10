import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class Player extends Entity {
	
	protected int playerNum;
	
	protected int lives;
	protected int bombBoosts;
	protected int bombMores;

    public Player(int x, int y, int playerNum)
    {
        super(x, y);
        
        this.playerNum = playerNum;
        
        lives = 3;
        bombBoosts = 0;
        bombMores = 0;
    }
    
    // Method used to create a StackPane of the Player image
    @Override
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
    	
    	// make all indexes a stackpane, whether or not there is anything to stack so you know it 
    	// is always a stackpane so you can add cast to stackpane
    	
    
}
