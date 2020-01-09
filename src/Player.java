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

 /*   @Override
    public void createImage(String imageFile) {

    	// creates image and adds it to arraylist of imageviews in main
    	
    	ImageView playerView = new ImageView();
    	
		Image playerImage = new Image("file:" + imageFile);
		
		playerView = new ImageView(playerImage);
		playerView.setFitHeight(50);
		playerView.setFitWidth(50);
		
		playerViews.add(playerView);
    	
    	HBox row = (HBox)root.getChildren().get(y);
    	StackPane coordinate = (StackPane) row.getChildren().get(x);
    	
    	Image spaceImage = new Image("file:Space.png");
    	
    	ImageView spaceImageView = new ImageView(spaceImage);
		spaceImageView.setFitHeight(50);
		spaceImageView.setFitWidth(50);
    	
    	coordinate.getChildren().setAll(spaceImageView, playerView);
    	
    	
    	// when player on top of space, no need to create space image. only when space is empty
    	// do u need space image, and when player moves, make the previous grid they were on a space
    	
    	
    	// make all indexes a stackpane, whether or not there is anything to stack so you know it 
    	// is always a stackpane so you can add cast to stackpane
    	
    }*/
    
    @Override
    public void createImage(String imageFile) {

    	// creates image and adds it to arraylist of imageviews in main
    	
    	ImageView playerView = new ImageView();
    	
		Image playerImage = new Image("file:" + imageFile);
		
		playerView = new ImageView(playerImage);
		playerView.setFitHeight(50);
		playerView.setFitWidth(50);
		
		playerViews.add(playerView);
    	
    	HBox row = (HBox)root.getChildren().get(y);
    	StackPane coordinate = (StackPane) row.getChildren().get(x);
    	
    	Image spaceImage = new Image("file:Space.png");
    	
    	ImageView spaceImageView = new ImageView(spaceImage);
		spaceImageView.setFitHeight(50);
		spaceImageView.setFitWidth(50);
    	
    	coordinate.getChildren().setAll(spaceImageView, playerView);
    	
    	// when player on top of space, no need to create space image. only when space is empty
    	// do u need space image, and when player moves, make the previous grid they were on a space
    	
    	
    	// make all indexes a stackpane, whether or not there is anything to stack so you know it 
    	// is always a stackpane so you can add cast to stackpane
    	
    }
}
