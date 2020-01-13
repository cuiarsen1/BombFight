import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	
	protected Player player1;
	protected Player player2;
	
	AnimationTimer Timer;
	
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
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(1, null);
				map.mapArray[j][i] = pair;
			}
			
			root.getChildren().add(tempBox);
		}
		
		// Initializes rows 6 to 7
		
		for (int i = 5; i < 7; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 2; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(0, null);
				map.mapArray[j][i] = pair;
			}
			
			for (int j = 2; j < 10; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(1, null);
				map.mapArray[j][i] = pair;
			}
			
			for (int j = 10; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
				
				Pair pair = new Pair(0, null);
				map.mapArray[j][i] = pair;
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
				
				Pair pair = new Pair(1, null);
				map.mapArray[j][i] = pair;
			}
			
			root.getChildren().add(tempBox);
		}
	}
	
	// Creates and initializes the player on the field
	public Player createPlayer(int playerNum, int x, int y) {
		
		Player player = null;
		
		StackPane playerImage = null;
		
		if (playerNum == 1)
		{
			player = new Player(x, y, playerNum);
			playerImage = player.createImage("file:PlayerRed.png");
		}
			
		else if (playerNum == 2)
		{
			player = new Player(x, y, playerNum);
			playerImage = player.createImage("file:PlayerBlue.png");
		}
    	
		/*Adds the player to the specific coordinate specified 
		by using the column and row to pinpoint the location*/
    	
		HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().set(x, playerImage);
    	
		Pair pair = new Pair(2, player);
		map.mapArray[x][y] = pair;
    	
		playerList.add(player);
		
		return player;
	}
	
	public Bomb createBomb(Player player) {
		
		Bomb bomb = new Bomb(0, 0);
		StackPane bombImage = bomb.createImage("file:Bomb.png", "file:PlayerRed.png");
		
		HBox row = (HBox)root.getChildren().get(player.getY());
    	row.getChildren().set(player.getX(), bombImage);
    	
		Pair pair = new Pair(3, bomb);
		map.mapArray[player.getX()][player.getY()] = pair;
		
		return bomb;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		playerList = new ArrayList();
		bombList = new ArrayList();
		
		root = new VBox();
		
		map = new Map();
		
		// Initializes playing field
		createMap();
		
		player1 = createPlayer(1, 0, 6);
		player2 = createPlayer(2, 11, 5);
		
		Bomb bomb = createBomb(player1);
		
		Scene scene = new Scene(root, 600, 600);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				// If there is no player on the screen, don't do anything
				if (playerList.isEmpty())
					return;

				if (event.getCode() == KeyCode.UP)
				{
					int tempY = player1.getY();
					player1.setY(tempY - 1);
					
					StackPane playerView = player1.createImage("file:PlayerRed.png");
					
					HBox row = (HBox)root.getChildren().get(player1.getY());
			    	row.getChildren().set(player1.getX(), playerView);
			    	
					Pair pair = new Pair(2, player1);
					map.mapArray[player1.getX()][player1.getY()] = pair;
					
					Pair pair2 = new Pair(0, null);
					map.mapArray[player1.getX()][tempY] = pair2;
				}
				
				else if (event.getCode() == KeyCode.DOWN)
				{
					int tempY = player1.getY();
					player1.setY(tempY + 1);
					
					StackPane playerView = player1.createImage("file:PlayerRed.png");
					
					HBox row = (HBox)root.getChildren().get(player1.getY());
			    	row.getChildren().set(player1.getX(), playerView);
			    	
					Pair pair = new Pair(2, player1);
					map.mapArray[player1.getX()][player1.getY()] = pair;
					
					Pair pair2 = new Pair(0, null);
					map.mapArray[player1.getX()][tempY] = pair2;
				}
					
				else if (event.getCode() == KeyCode.RIGHT)
				{
					int tempX = player1.getX();
					player1.setX(tempX + 1);
					
					StackPane playerView = player1.createImage("file:PlayerRed.png");
					
					HBox row = (HBox)root.getChildren().get(player1.getY());
			    	row.getChildren().set(player1.getX(), playerView);
			    	
					Pair pair = new Pair(2, player1);
					map.mapArray[player1.getX()][player1.getY()] = pair;
					
					Pair pair2 = new Pair(0, null);
					map.mapArray[tempX][player1.getY()] = pair2;
				}
				
				else if (event.getCode() == KeyCode.LEFT)
				{
					int tempX = player1.getX();
					player1.setX(tempX - 1);
					
					StackPane playerView = player1.createImage("file:PlayerRed.png");
					
					HBox row = (HBox)root.getChildren().get(player1.getY());
			    	row.getChildren().set(player1.getX(), playerView);
			    	
					Pair pair = new Pair(2, player1);
					map.mapArray[player1.getX()][player1.getY()] = pair;
					
					Pair pair2 = new Pair(0, null);
					map.mapArray[tempX][player1.getY()] = pair2;
				}
			}
		});
		
		// Animation timer used to animate the game
		Timer = new AnimationTimer() {

			// Variables used to track the time
			
			long oldTimeVelocity = 0;
			long intervalVelocity = 500000000l;

			@Override
			public void handle(long time) {
				
				// Animates all the objects every frame CHANGE THIS OBVIOUSLY
				try {
					upDateMove();
				} catch (IOException e) {
					e.printStackTrace();
				}

				oldTimeVelocity += 1;

				// Move the player every half a second if direction key held down
				if (time - oldTimeVelocity > intervalVelocity) {
					upDateVelocity();
					oldTimeVelocity = time;
				}

			}
		};

		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
