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

public class Main extends Application {

	private ArrayList<Player> playerList;
	
	protected Map map;

	protected VBox root; // root VBox displaying the main scene
	
	private Player player1;
	private Player player2;
	
	private AnimationTimer Timer;
	
	//private long bombMax = 3000000000l; // The amount of time before a bomb detonates
	private long bombMax = 3; // The amount of time before a bomb detonates
	
	/*Method used to create a StackPane of any object other than the Player 
	(Space, Crate, BombMore, BombBoost)*/
	public StackPane createImage(int numImages, String imageFile) {
		
		StackPane pane = new StackPane();
		
		if (numImages == 1)
		{
			Image tempImage = new Image(imageFile);
			ImageView tempImageView = new ImageView(tempImage);
			tempImageView.setFitHeight(50);
			tempImageView.setFitWidth(50);
			
			pane.getChildren().add(tempImageView);
		}
		
		else if (numImages == 2)
		{
			Image spaceImage = new Image("file:Space.png");
			ImageView spaceImageView = new ImageView(spaceImage);
			spaceImageView.setFitHeight(50);
			spaceImageView.setFitWidth(50);
			
			Image tempImage = new Image(imageFile);
			ImageView tempImageView = new ImageView(tempImage);
			tempImageView.setFitHeight(50);
			tempImageView.setFitWidth(50);
			
			pane.getChildren().addAll(spaceImageView, tempImageView);
		}
		
		// Creates images for the case that the player is moving out of a grid that 
		// contains both the other player and a bomb
		else if (numImages == 3) {
			
			Image spaceImage = new Image("file:Space.png");
			ImageView spaceImageView = new ImageView(spaceImage);
			spaceImageView.setFitHeight(50);
			spaceImageView.setFitWidth(50);
			
			Image bombImage = new Image("file:Bomb.png");
			ImageView bombImageView = new ImageView(bombImage);
			bombImageView.setFitHeight(50);
			bombImageView.setFitWidth(50);
			
			Image playerImage = new Image(imageFile);
			ImageView playerImageView = new ImageView(playerImage);
			playerImageView.setFitHeight(50);
			playerImageView.setFitWidth(50);
			
			pane.getChildren().addAll(spaceImageView, bombImageView, playerImageView);
		}
		
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
				StackPane pane = createImage(1, "file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}
			
			root.getChildren().add(tempBox);
		}
		
		// Initializes rows 6 to 7
		
		for (int i = 5; i < 7; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 2; j += 1)
			{
				StackPane pane = createImage(1, "file:Space.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(0, null, false);
				map.mapArray[j][i] = triple;
			}
			
			for (int j = 2; j < 10; j += 1)
			{
				StackPane pane = createImage(1, "file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}
			
			for (int j = 10; j < 12; j += 1)
			{
				StackPane pane = createImage(1, "file:Space.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(0, null, false);
				map.mapArray[j][i] = triple;
			}
			
			root.getChildren().add(tempBox);
		}
		
		//Initializes rows 8 to 12
		
		for (int i = 7; i < 12; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = createImage(1, "file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}
			
			root.getChildren().add(tempBox);
		}
	}
	
	public void createSpace(int x, int y) {
		
		StackPane spaceImage = createImage(1, "file:Space.png");
		
		HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().set(x, spaceImage);
    	
		Triple triple = new Triple(0, null, false);
		map.mapArray[x][y] = triple;
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
    	
		Triple triple = new Triple(2, player, false);
		map.mapArray[x][y] = triple;
    	
		playerList.add(new Node<Player>(player));
		
		return player;
	}
	
	// Method used to initialize a bomb and display it on the screen
	public Bomb createBomb(Player player) {
		
		Bomb bomb = new Bomb(player.getX(), player.getY()); // Initialize bomb 
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
    	
		Triple triple = new Triple(3, bomb, true);
		map.mapArray[player.getX()][player.getY()] = triple;
		
		return bomb;
	}
	
	// Method used to move players. The integer direction is represented with: 
	// 1: Up 2: Down 3: Right 4: Left
	public void movePlayers(int playerNum, String playerFile) {
		
		if (playerList.isEmpty())
			return;
		
		// Coordinates for the previous grid the player was in before they moved
		int prevX = playerList.get(playerNum - 1).getValue().getX();
		int prevY = playerList.get(playerNum - 1).getValue().getY();
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 1)
		{
			playerList.get(playerNum - 1).getValue().setY(prevY - 1);
			
			StackPane playerView = playerList.get(playerNum - 1).getValue().createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getValue().getX(), playerView);
	    	
	    	if (map.mapArray[prevX][prevY].bombExist == false)
	    	{
	    		createSpace(prevX, prevY);
	    		Triple triple = new Triple(0, null, false);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
	    	else if (map.mapArray[prevX][prevY].bombExist == true)
	    	{
	    		StackPane bombImage = createImage(2, "file:Bomb.png");
	    		HBox rowTemp = (HBox)root.getChildren().get(prevY);
		    	rowTemp.getChildren().set(prevX, bombImage);
		    	
		    	// Changes the previous grid the player was in to register there is a bomb there
		    	Triple triple = new Triple(3, null, true);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
			Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
			map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()] = triple;
		}
		
		else if (playerList.get(playerNum - 1).getValue().moveDirection == 2)
		{
			playerList.get(playerNum - 1).getValue().setY(prevY + 1);
			
			StackPane playerView = playerList.get(playerNum - 1).getValue().createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getValue().getX(), playerView);
	    	
	    	if (map.mapArray[prevX][prevY].bombExist == false)
	    	{
	    		createSpace(prevX, prevY);
	    		Triple triple = new Triple(0, null, false);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
	    	else if (map.mapArray[prevX][prevY].bombExist == true)
	    	{
	    		StackPane bombImage = createImage(2, "file:Bomb.png");
	    		HBox rowTemp = (HBox)root.getChildren().get(prevY);
		    	rowTemp.getChildren().set(prevX, bombImage);
		    	
		    	// Changes the previous grid the player was in to register there is a bomb there
		    	Triple triple = new Triple(3, null, true);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
	    	Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
			map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()] = triple;
		}

		else if (playerList.get(playerNum - 1).getValue().moveDirection == 3)
		{
			playerList.get(playerNum - 1).getValue().setX(prevX + 1);
			
			StackPane playerView = playerList.get(playerNum - 1).getValue().createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getValue().getX(), playerView);
	    	
	    	if (map.mapArray[prevX][prevY].bombExist == false)
	    	{
	    		createSpace(prevX, prevY);
	    		Triple triple = new Triple(0, null, false);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
	    	else if (map.mapArray[prevX][prevY].bombExist == true)
	    	{
	    		StackPane bombImage = createImage(2, "file:Bomb.png");
	    		HBox rowTemp = (HBox)root.getChildren().get(prevY);
		    	rowTemp.getChildren().set(prevX, bombImage);
		    	
		    	// Changes the previous grid the player was in to register there is a bomb there
		    	Triple triple = new Triple(3, null, true);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
			Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
			map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()] = triple;
		}
		
		else if (playerList.get(playerNum - 1).getValue().moveDirection == 4)
		{
			playerList.get(playerNum - 1).getValue().setX(prevX - 1);
			
			StackPane playerView = playerList.get(playerNum - 1).getValue().createImage(playerFile);
			
			HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
	    	row.getChildren().set(playerList.get(playerNum - 1).getValue().getX(), playerView);
	    	
	    	if (map.mapArray[prevX][prevY].bombExist == false)
	    	{
	    		createSpace(prevX, prevY);
	    		Triple triple = new Triple(0, null, false);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
	    	else if (map.mapArray[prevX][prevY].bombExist == true)
	    	{
	    		StackPane bombImage = createImage(2, "file:Bomb.png");
	    		HBox rowTemp = (HBox)root.getChildren().get(prevY);
		    	rowTemp.getChildren().set(prevX, bombImage);
		    	
		    	// Changes the previous grid the player was in to register there is a bomb there
		    	Triple triple = new Triple(3, null, true);
		    	map.mapArray[prevX][prevY] = triple;
	    	}
	    	
			Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
			map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()] = triple;
		}
	}
	
	// Checks for collisions with the edge of the screen, crate, bomb, or player
	public void collisionCheck(int playerNum) {
		
		if (playerList.isEmpty())
			return;
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 1)
		{
			if (playerList.get(playerNum - 1).getValue().getY() - 1 >= 0)
			{
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() - 1].type != 1 && map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() - 1].type != 2 && map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() - 1].type != 3)
				{
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				else
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			else                                                                                     
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 2)
		{
			if (playerList.get(playerNum - 1).getValue().getY() + 1 <= 11)
			{
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() + 1].type != 1 && map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() + 1].type != 2 && map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY() + 1].type != 3)
				{
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				else
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			else                                                                                     
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 3)
		{
			if (playerList.get(playerNum - 1).getValue().getX() + 1 <= 11)
			{
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX() + 1][playerList.get(playerNum - 1).getValue().getY()].type != 1 && map.mapArray[playerList.get(playerNum - 1).getValue().getX() + 1][playerList.get(playerNum - 1).getValue().getY()].type != 2 && map.mapArray[playerList.get(playerNum - 1).getValue().getX() + 1][playerList.get(playerNum - 1).getValue().getY()].type != 3)
				{
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				else
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			else                                                                                     
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 4)
		{
			if (playerList.get(playerNum - 1).getValue().getX() - 1 >= 0)
			{
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX() - 1][playerList.get(playerNum - 1).getValue().getY()].type != 1 && map.mapArray[playerList.get(playerNum - 1).getValue().getX() - 1][playerList.get(playerNum - 1).getValue().getY()].type != 2 && map.mapArray[playerList.get(playerNum - 1).getValue().getX() - 1][playerList.get(playerNum - 1).getValue().getY()].type != 3)
				{
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				else
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			else                                                                                     
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}
	}
	
	// Method used to detonate a player's Bomb
	public void detonate(Player player) {
		
		Bomb bomb = player.bombQueue.dequeue();
		createFlames(bomb.getX(), bomb.getY(), player.bombBoosts);
		System.out.println("Detonate");
	}
	
	/*Method used to create flames from the detonation of a 
	bomb, spreading outwards from the specified location*/
	public void createFlames(int x, int y, int bombBoosts) {
		
		System.out.println("Hi");
		
		int currentX = x;
		int currentY = y;
		
		// Creates flames spreading upwards
		
		for (int i = 0; i < 3 + bombBoosts; i += 1)
		{
			if (currentY < 0)
				break;
			
			StackPane flameImage = createImage(1, "file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentY -= 1;
		}
		
		currentX = x;
		currentY = y;
		
		// Creates flames spreading downwards
		
		for (int i = 0; i < 3 + bombBoosts; i += 1)
		{
			if (currentY > 11)
				break;
			
			StackPane flameImage = createImage(1, "file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentY += 1;
		}
		
		currentX = x;
		currentY = y;
		
		// Creates flames spreading to the right
		
		for (int i = 0; i < 3 + bombBoosts; i += 1)
		{
			if (currentX > 11)
				break;
			
			StackPane flameImage = createImage(1, "file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentX += 1;
		}
		
		currentX = x;
		currentY = y;
		
		// Creates flames spreading to the left
		
		for (int i = 0; i < 3 + bombBoosts; i += 1)
		{
			if (currentX < 0)
				break;
			
			StackPane flameImage = createImage(1, "file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentX -= 1;
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		playerList = new ArrayList();
		
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
					playerList.get(0).getValue().moveDirection = 1;
				
				else if (event.getCode() == KeyCode.S)
					playerList.get(0).getValue().moveDirection = 2;
				
				else if (event.getCode() == KeyCode.D)
					playerList.get(0).getValue().moveDirection = 3;
				
				else if (event.getCode() == KeyCode.A)
					playerList.get(0).getValue().moveDirection = 4;
				
				// Handles movement for Player 2
				
				if (event.getCode() == KeyCode.UP)
				{
					playerList.get(1).getValue().moveDirection = 1;
				}
				
				else if (event.getCode() == KeyCode.DOWN)
				{
					playerList.get(1).getValue().moveDirection = 2;
				}
				
				else if (event.getCode() == KeyCode.RIGHT)
				{
					playerList.get(1).getValue().moveDirection = 3;
				}
				
				else if (event.getCode() == KeyCode.LEFT)
				{
					playerList.get(1).getValue().moveDirection = 4;
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
					playerList.get(0).getValue().moveBoolean = false;
					
				else if (event.getCode() == KeyCode.S)
					playerList.get(0).getValue().moveBoolean = false;

				else if (event.getCode() == KeyCode.D)
					playerList.get(0).getValue().moveBoolean = false;

				else if (event.getCode() == KeyCode.A)
					playerList.get(0).getValue().moveBoolean = false;
				
				if (event.getCode() == KeyCode.C && playerList.get(0).getValue().bombQueue.size() < playerList.get(0).getValue().bombMores + 1)
				{
					Bomb bomb = createBomb(playerList.get(0).getValue());
					playerList.get(0).getValue().bombQueue.enqueue(bomb);
				}
				
				// Handles stopping movement for Player 2
				
				if (event.getCode() == KeyCode.UP)
					playerList.get(1).getValue().moveBoolean = false;

				else if (event.getCode() == KeyCode.DOWN)
					playerList.get(1).getValue().moveBoolean = false;

				else if (event.getCode() == KeyCode.RIGHT)
					playerList.get(1).getValue().moveBoolean = false;
				
				else if (event.getCode() == KeyCode.LEFT)
					playerList.get(1).getValue().moveBoolean = false;
				
				if (event.getCode() == KeyCode.SLASH && playerList.get(1).getValue().bombQueue.size() < playerList.get(1).getValue().bombMores + 1)
				{
					Bomb bomb = createBomb(playerList.get(1).getValue());
					playerList.get(1).getValue().bombQueue.enqueue(bomb);
				}
			}
		});
		
		// Animation timer used to animate the game
		Timer = new AnimationTimer() {

			// Variables used to track the time
			
			long oldTimeVelocity = 0;
			long intervalVelocity = 100000000;
			
			@Override
			public void handle(long time) {
				
				oldTimeVelocity += 1;

				// Move the player every fifth of a second if direction key held down
				
				try {
					upDate();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (time - oldTimeVelocity > intervalVelocity) {
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

	private void upDate() throws IOException {
		collisionCheck(1);
		collisionCheck(2);
		
		// Increments the timer on every existing bomb
		
		if (playerList.get(0).getValue().bombQueue.size() > 0)
		{
			for (int i = 0; i < playerList.get(0).getValue().bombQueue.size(); i += 1)
			{
				playerList.get(0).getValue().bombQueue.queue[i].timer += 1;
			}
			
			if (playerList.get(0).getValue().bombQueue.peek().timer >= bombMax)
			{
				detonate(playerList.get(0).getValue());
			}
		}
		
		if (playerList.get(1).getValue().bombQueue.size() > 0)
		{
			for (int i = 0; i < playerList.get(1).getValue().bombQueue.size(); i += 1)
			{
				playerList.get(1).getValue().bombQueue.queue[i].timer += 1;
			}
			
			if (playerList.get(1).getValue().bombQueue.peek().timer >= bombMax)
			{
				detonate(playerList.get(1).getValue());
			}
		}
	}
	
	private void upDateMove() throws IOException {
		
		if (playerList.get(0).getValue().moveBoolean == true)
		{
			movePlayers(1, "file:PlayerRed.png");
		}
		
		if (playerList.get(1).getValue().moveBoolean == true)
		{
			movePlayers(2, "file:PlayerBlue.png");
		}
		
		/*// If the player collides with an obstacle, end the game
		if (stopGame == true)
			gameOver();*/
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
