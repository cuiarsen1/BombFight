import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

	private ArrayList<Player> playerList;
	private ArrayList<Bomb> bombList;
	
	protected Map map;

	protected VBox root; // root VBox displaying the main scene
	
	/*Method used to create a StackPane of any object other than the Player 
	(Space, Crate, BombMore, BombBoost)*/
	public StackPane createImage(String imageFile) {
		
		StackPane pane = new StackPane();
		Image tempImage = new Image(imageFile);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setFitHeight(50);
		tempImageView.setFitWidth(50);
		
		pane.getChildren().add(tempImageView);
		
		return pane;
	}
	
	// Creates and initializes the game board
	public void createMap() {
		
		// Initializes rows 1 to 5
		
		for (int i = 0; i < 5; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 12; j += 1)
			{
				Crate crate = new Crate(j, i);
				
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(1, crate);
				map.mapArray[j][i] = pair;
			}
			
			root.getChildren().add(tempBox);
		}
		
		// Initializes rows 6 to 7
		
		for (int i = 5; i < 6; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 2; j += 1)
			{
				Space space = new Space(j, i);
				
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(0, space);
				map.mapArray[j][i] = pair;
			}
			
			for (int j = 2; j < 10; j += 1)
			{
				Crate crate = new Crate(j, i);
				
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(1, crate);
				map.mapArray[j][i] = pair;
			}
			
			for (int j = 0; j < 2; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
			}
			
			root.getChildren().add(tempBox);
		}
		
		//Initializes rows 8 to 12
		
		for (int i = 0; i < 5; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
			}
			
			root.getChildren().add(tempBox);
		}
	}
	
	// Creates and initializes the player on the field
	public void createPlayer(int playerNum) {
		
		Player player = null;
		
		StackPane coordinate = null;
		
		if (playerNum == 1)
		{
			player = new Player(0, 6, playerNum);
			coordinate = player.createImage("file:PlayerRed.png");
		}
			
		else if (playerNum == 2)
		{
			player = new Player(11, 5, playerNum);
			coordinate = player.createImage("file:PlayerBlue.png");
		}
    	
		/*Adds the player to the specific coordinate specified 
		by using the column and row to pinpoint the location*/
    	
		HBox row = (HBox)root.getChildren().get(player.y);
    	row.getChildren().set(player.x, coordinate);
    	
		playerList.add(player);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		playerList = new ArrayList();
		bombList = new ArrayList();
		
		root = new VBox();
		
		map = new Map();
		
		// Initializes playing field
		createMap();
		//map.createMap();
		
		createPlayer(1);
		createPlayer(2);

		Scene scene = new Scene(root, 600, 600);

		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
