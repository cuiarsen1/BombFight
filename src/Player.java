import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    	
    	if (playerNum == 1)
    	{
    		Image playerImage = new Image("PlayerRed.png");
    		
    		ImageView playerView = new ImageView(playerImage);
    		
    		playerViews.add(playerView);
    		
    	}
    	
    	else if (playerNum == 2)
    	{
    		Image playerImage = new Image("PlayerBlue.png");
    		
    		ImageView playerView = new ImageView(playerImage);
    		
    		playerViews.add(playerView);
    	}
    	
    	HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().get(x);
    	
    	// make all indexes a stackpane, whether or not there is anything to stack so you know it 
    	// is always a stackpane so you can add cast to stackpane
    	
    }
}
