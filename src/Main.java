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
	
	// booleans used to track which players need to be moved
	private boolean moveBoolean1 = false;
	private boolean moveBoolean2 = false;
	
	// Integers used to track the direction each player needs to be moved in
	private int moveDirection1;
	private int moveDirection2;
	
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
		
		for (int i = 7; i < 12; i += 1)
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
	
	public void createSpace(int x, int y) {
		
		StackPane spaceImage = createImage("file:Space.png");
		
		HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().set(x, spaceImage);
    	
		Pair pair = new Pair(0, null);
		map.mapArray[x][y] = pair;
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
		StackPane bombImage = null;
		
		if (player.playerNum == 1)
		{
			bombImage = bomb.createImage("file:Bomb.png", "file:PlayerRed.png");
		}
		
		else if (player.playerNum == 2)
		{
			bombImage = bomb.createImage("file:Bomb.png", "file:PlayerBlue.png");
		}
		
		HBox row = (HBox)root.getChildren().get(player.getY());
    	row.getChildren().set(player.getX(), bombImage);
    	
		Pair pair = new Pair(3, bomb);
		map.mapArray[player.getX()][player.getY()] = pair;
		
		return bomb;
	}
	
	// Method used to move players. The integer direction is represented with: 
	// 1: Up 2: Down 3: Right 4: Left
	public void movePlayers(int playerNum, int direction, String playerFile) {
		
		if (playerList.isEmpty())
			return;
		
		if (direction == 1)
		{
			int tempY = playerList.get(playerNum - 1).getY();
			playerList.get(playerNum - 1).setY(tempY - 1);
			
			StackPane playerView = playerList.get(playerNum - 1).createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getX(), playerView);
	    	
	    	createSpace(playerList.get(playerNum - 1).getX(), tempY);
	    	
			Pair pair = new Pair(2, playerList.get(playerNum - 1));
			map.mapArray[playerList.get(playerNum - 1).getX()][playerList.get(playerNum - 1).getY()] = pair;
		}
		
		else if (direction == 2)
		{
			int tempY = playerList.get(playerNum - 1).getY();
			playerList.get(playerNum - 1).setY(tempY + 1);
			
			StackPane playerView = playerList.get(playerNum - 1).createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getX(), playerView);
	    	
	    	createSpace(playerList.get(playerNum - 1).getX(), tempY);
	    	
			Pair pair = new Pair(2, playerList.get(playerNum - 1));
			map.mapArray[playerList.get(playerNum - 1).getX()][playerList.get(playerNum - 1).getY()] = pair;
		}

		else if (direction == 3)
		{
			int tempX = playerList.get(playerNum - 1).getX();
			playerList.get(playerNum - 1).setX(tempX + 1);
			
			StackPane playerView = playerList.get(playerNum - 1).createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getX(), playerView);
	    	
	    	createSpace(tempX, playerList.get(playerNum - 1).getY());
	    	
			Pair pair = new Pair(2, playerList.get(playerNum - 1));
			map.mapArray[playerList.get(playerNum - 1).getX()][playerList.get(playerNum - 1).getY()] = pair;
		}
		
		else if (direction == 4)
		{
			int tempX = playerList.get(playerNum - 1).getX();
			playerList.get(playerNum - 1).setX(tempX - 1);
			
			StackPane playerView = playerList.get(playerNum - 1).createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getX(), playerView);
	    	
	    	createSpace(tempX, playerList.get(playerNum - 1).getY());
	    	
			Pair pair = new Pair(2, playerList.get(playerNum - 1));
			map.mapArray[playerList.get(playerNum - 1).getX()][playerList.get(playerNum - 1).getY()] = pair;
		}
		
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
		
		//Bomb bomb = createBomb(player1);
		
		Scene scene = new Scene(root, 600, 600);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
	
				// If there is no player on the screen, don't do anything
				if (playerList.isEmpty())
					return;
	
				// Handles movement for Player 1
				
				if (event.getCode() == KeyCode.W)
				{
					if (playerList.get(0).getY() - 1 >= 0)
					{
						if (map.mapArray[playerList.get(0).getX()][playerList.get(0).getY() - 1].type != 1)
						{
							moveBoolean1 = true;
							moveDirection1 = 1;
						}
						else
							moveBoolean1 = false;
					}
					else                                                                                     
						moveBoolean1 = false;
					
				}
				
				else if (event.getCode() == KeyCode.S)
				{
					if (playerList.get(0).getY() + 1 <= map.mapArray.length - 1)
					{
						if (map.mapArray[playerList.get(0).getX()][playerList.get(0).getY() + 1].type != 1)
						{
							moveBoolean1 = true;
							moveDirection1 = 2;
						}
						else
							moveBoolean1 = false;
					}
					else
						moveBoolean1 = false;
				}
					
				else if (event.getCode() == KeyCode.D)
				{
					if (playerList.get(0).getX() + 1 <= map.mapArray.length - 1)
					{
						if (map.mapArray[playerList.get(0).getX() + 1][playerList.get(0).getY()].type != 1)
						{
							moveBoolean1 = true;
							moveDirection1 = 3;
						}
						else
							moveBoolean1 = false;
					}
					else
						moveBoolean1 = false;
				}
				
				else if (event.getCode() == KeyCode.A)
				{
					if (playerList.get(0).getX() - 1 >= 0)
					{
						if (map.mapArray[playerList.get(0).getX() - 1][playerList.get(0).getY()].type != 1)
						{
							moveBoolean1 = true;
							moveDirection1 = 4;
						}	
						else
							moveBoolean1 = false;
					}
					else
						moveBoolean1 = false;
				}
				
				// Handles movement for Player 2
				
				if (event.getCode() == KeyCode.UP)
				{
					moveBoolean2 = true;
					moveDirection2 = 1;
				}
				
				else if (event.getCode() == KeyCode.DOWN)
				{
					moveBoolean2 = true;
					moveDirection2 = 2;
				}
					
				else if (event.getCode() == KeyCode.RIGHT)
				{
					moveBoolean2 = true;
					moveDirection2 = 3;
				}
				
				else if (event.getCode() == KeyCode.LEFT)
				{
					moveBoolean2 = true;
					moveDirection2 = 4;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {

				// If there is no player on the screen, don't do anything
				if (playerList.isEmpty())
					return;

				// Handles stopping movement for the players
				
				if (event.getCode() == KeyCode.W)
					moveBoolean1 = false;
					
				else if (event.getCode() == KeyCode.S)
					moveBoolean1 = false;

				else if (event.getCode() == KeyCode.D)
					moveBoolean1 = false;

				else if (event.getCode() == KeyCode.A)
					moveBoolean1 = false;
				
				// Handles stopping movement for Player 2
				
				if (event.getCode() == KeyCode.UP)
					moveBoolean2 = false;

				else if (event.getCode() == KeyCode.DOWN)
					moveBoolean2 = false;

				else if (event.getCode() == KeyCode.RIGHT)
					moveBoolean2 = false;	

				else if (event.getCode() == KeyCode.LEFT)
					moveBoolean2 = false;
			}
		});
		
		// Animation timer used to animate the game
		Timer = new AnimationTimer() {

			// Variables used to track the time
			
			long oldTimeVelocity = 0;
			long intervalVelocity = 200000000;

			@Override
			public void handle(long time) {
				
				oldTimeVelocity += 1;

				// Move the player every half a second if direction key held down
				if (time - oldTimeVelocity > 1) {
					try {
						upDateMove();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldTimeVelocity = time;
				}

			}
		};
		
		Timer.start();

		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void upDateMove() throws IOException {
		
		if (moveBoolean1 == true)
		{
			movePlayers(1, moveDirection1, "file:PlayerRed.png");
		}
		
		if (moveBoolean2 == true)
		{
			movePlayers(2, moveDirection2, "file:PlayerBlue.png");
		}


		/*// If the player collides with an obstacle, end the game
		if (stopGame == true)
			gameOver();*/

	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
