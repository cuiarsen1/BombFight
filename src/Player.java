import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

    @Override
    public void createImage(String imageFile) {

    	// creates image and adds it to arraylist of imageviews in main
    	
    	ImageView playerView = new ImageView();
    	
    	if (playerNum == 1)
    	{
    		Image playerImage = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\PlayerRed.png");
    		
    		playerView = new ImageView(playerImage);
    	}
    	
    	else if (playerNum == 2)
    	{
    		Image playerImage = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\PlayerBlue.png");
    		
    		playerView = new ImageView(playerImage);
    	}
    	
    	playerViews.add(playerView);
    	
    	HBox row = (HBox)root.getChildren().get(y - 1);
    	StackPane coordinate = (StackPane) row.getChildren().get(x - 1);
    	
    	Image spaceImage = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Space.png");
    	ImageView spaceImageView = new ImageView(spaceImage);
    	
    	coordinate.getChildren().setAll(spaceImageView, playerView);
    	
    	
    	// when player on top of space, no need to create space image. only when space is empty
    	// do u need space image, and when player moves, make the previous grid they were on a space
    	
    	
    	// make all indexes a stackpane, whether or not there is anything to stack so you know it 
    	// is always a stackpane so you can add cast to stackpane
    	
    }
}
