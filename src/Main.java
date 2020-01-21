import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

/*
Make it so that things dont disappear after a flame goes over it
*/

public class Main extends Application {

	private ArrayList<Player> playerList;
	
	protected Map map;

	protected VBox root; // root VBox displaying the main scene
	
	private AnimationTimer Timer;
	
	private long bombMax = 180; // The amount of time in frames before a bomb detonates
	private long detonationMax = 30; // Time in frames before the flames from a bomb disappear
	
	// Labels displaying the current amount of lives of each player
	private Label player1Lives;
	private Label player2Lives;
	
	private Button startButton;
	
	private Font FONT_LIVES;
	private Font FONT_BUTTON;
	
	//Method used to create a single image, either a Space, Crate, Item, or Flame
	public StackPane createImage(String imageFile) {
		
		StackPane pane = new StackPane();
		
		Image tempImage = new Image(imageFile);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setFitHeight(50);
		tempImageView.setFitWidth(50);
		
		pane.getChildren().add(tempImageView);
		
		return pane;
	}
	
	/*Creates images for the case when there are 2 images on top of each 
	other, such as a Bomb on a grid or a player on a grid*/
	public StackPane createImage(String imageFile1, String imageFile2) {
	
		StackPane pane = new StackPane();
		
		Image spaceImage = new Image(imageFile1);
		ImageView spaceImageView = new ImageView(spaceImage);
		spaceImageView.setFitHeight(50);
		spaceImageView.setFitWidth(50);
		
		Image tempImage = new Image(imageFile2);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setFitHeight(50);
		tempImageView.setFitWidth(50);
		
		pane.getChildren().addAll(spaceImageView, tempImageView);
		
		return pane;
	}
	
	/*Creates images for the case that the player is moving out of a grid
	that contains both the other player and a bomb*/
	public StackPane createImage(String imageFile1, String imageFile2, String imageFile3) {
		
		StackPane pane = new StackPane();
		
		Image spaceImage = new Image(imageFile1);
		ImageView spaceImageView = new ImageView(spaceImage);
		spaceImageView.setFitHeight(50);
		spaceImageView.setFitWidth(50);
		
		Image bombImage = new Image(imageFile2);
		ImageView bombImageView = new ImageView(bombImage);
		bombImageView.setFitHeight(50);
		bombImageView.setFitWidth(50);
		
		Image playerImage = new Image(imageFile3);
		ImageView playerImageView = new ImageView(playerImage);
		playerImageView.setFitHeight(50);
		playerImageView.setFitWidth(50);
		
		pane.getChildren().addAll(spaceImageView, bombImageView, playerImageView);
		
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
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(0, null, false);
				map.mapArray[j][i] = triple;
			}
			
			for (int j = 2; j < 10; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}
			
			for (int j = 10; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
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
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
				
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}
			
			root.getChildren().add(tempBox);
		}
	}
	
	public void createSpace(int x, int y) {
		
		StackPane spaceImage = createImage("file:Space.png");
		
		HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().set(x, spaceImage);
    	
		Triple triple = new Triple(0, null, false);
		map.mapArray[x][y] = triple;
	}
	
	// Method used to start the game
	public void gameStart() {
	
		createPlayer(1, 0, 6);
		createPlayer(2, 11, 5);
		
		root.getChildren().remove(startButton);
		
		// Labels displaying the amount of lives the players have
		player1Lives = new Label("Player 1: " + playerList.get(0).getValue().lives + " lives");
		player1Lives.setFont(FONT_LIVES);
		player1Lives.setTextFill(Color.BLACK);
		
		player2Lives = new Label("Player 2: " + playerList.get(1).getValue().lives + " lives");
		player2Lives.setFont(FONT_LIVES);
		player2Lives.setTextFill(Color.BLACK);
		
		HBox livesBox = new HBox();
		
		livesBox.getChildren().addAll(player1Lives, player2Lives);
		livesBox.setSpacing(150);
		
		root.getChildren().add(livesBox);
		
		Timer.start();
	}
	
	// Creates and initializes the player on the field
	public void createPlayer(int playerNum, int x, int y) {
		
		Player player = null;
		
		StackPane playerImage = null;
		
		if (playerNum == 1)
		{
			player = new Player(x, y, playerNum);
			playerImage = player.createImage("file:Space.png", "file:PlayerRed.png");
		}
			
		else if (playerNum == 2)
		{
			player = new Player(x, y, playerNum);
			playerImage = player.createImage("file:Space.png", "file:PlayerBlue.png");
		}
    	
		/*Adds the player to the specific coordinate specified 
		by using the column and row to pinpoint the location*/
    	
		HBox row = (HBox)root.getChildren().get(y);
    	row.getChildren().set(x, playerImage);
    	
		Triple triple = new Triple(2, player, false);
		map.mapArray[x][y] = triple;
    	
		playerList.add(new Node<Player>(player));
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
		
		// Changes coordinates of the player object
		
		if (playerList.get(playerNum - 1).getValue().moveDirection == 1)
		{
			playerList.get(playerNum - 1).getValue().setY(prevY - 1);
		}
		
		else if (playerList.get(playerNum - 1).getValue().moveDirection == 2)
		{
			playerList.get(playerNum - 1).getValue().setY(prevY + 1);
		}

		else if (playerList.get(playerNum - 1).getValue().moveDirection == 3)
		{
			playerList.get(playerNum - 1).getValue().setX(prevX + 1);
		}
		
		else if (playerList.get(playerNum - 1).getValue().moveDirection == 4)
		{
			playerList.get(playerNum - 1).getValue().setX(prevX - 1);
		}
		
		// Creates an image of the player in its new location it moved to
		
		StackPane playerView = playerList.get(playerNum - 1).getValue().createImage("file:Space.png", playerFile);
		
		HBox row = (HBox)root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
    	row.getChildren().set(playerList.get(playerNum - 1).getValue().getX(), playerView);
		
    	// Create a Space image in the previous grid the player was in
		if (map.mapArray[prevX][prevY].bombExist == false)
    	{
    		createSpace(prevX, prevY);
    		Triple triple = new Triple(0, null, false);
	    	map.mapArray[prevX][prevY] = triple;
    	}
    	
		// If there was a bomb in the previous grid the player was in, keep it there
    	else if (map.mapArray[prevX][prevY].bombExist == true)
    	{
    		StackPane bombImage = createImage("file:Space.png", "file:Bomb.png");
    		HBox rowTemp = (HBox)root.getChildren().get(prevY);
	    	rowTemp.getChildren().set(prevX, bombImage);
	    	
	    	// Changes the previous grid the player was in to register there is a bomb there
	    	Triple triple = new Triple(3, null, true);
	    	map.mapArray[prevX][prevY] = triple;
    	}
		
		// If the player picks up an Item, give them the corresponding power up
		
		if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()].type == 4 && playerList.get(playerNum - 1).getValue().bombMores < 3)
    	{
			BombMore bombMore = (BombMore) map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()].object;
    		bombMore.powerUp(playerList.get(playerNum - 1).getValue());
    	}
    	
    	if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()].type == 5 && playerList.get(playerNum - 1).getValue().bombBoosts < 3)
    	{
    		BombBoost bombBoost = (BombBoost) map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()].object;
    		bombBoost.powerUp(playerList.get(playerNum - 1).getValue());
    	}
		
    	// Update the map on where the player is currently located
		Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
		map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1).getValue().getY()] = triple;
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
	public Bomb detonate(Player player) {
		
		/*Removes this bomb from the active bombs queue, and adds it to the 
		queue tracking when to make the detonation flames disappear*/
		Bomb bomb = player.bombQueue.dequeue();
		player.detonatedQueue.enqueue(bomb);
		
		int currentX = bomb.getX();
		int currentY = bomb.getY();
		
		/*Booleans tracking whether a player has already been
		hit by the detonation of a specific bomb*/
		boolean player1Hurt = false;
		boolean player2Hurt = false;
		
		/*Creates flames from the detonation of a bomb, 
		spreading outwards from the specified location*/
		
		// Creates flames spreading upwards
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentY < 0)
				break;
			
			StackPane flameImage = createImage("file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	if (player1Hurt == false)
	    	{
	    		if (playerList.get(0).getValue().getX() == currentX && playerList.get(0).getValue().getY() == currentY)
	    		{
	    			playerList.get(0).getValue().lives -= 1;
	    			player1Hurt = true;
	    		}
	    	}
	    	
	    	if (player2Hurt == false)
	    	{
	    		if (playerList.get(1).getValue().getX() == currentX && playerList.get(1).getValue().getY() == currentY)
	    		{
	    			playerList.get(1).getValue().lives -= 1;
	    			player2Hurt = true;
	    		}
	    	}
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentY -= 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		// Creates flames spreading downwards
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentY > 11)
				break;
			
			StackPane flameImage = createImage("file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	if (player1Hurt == false)
	    	{
	    		if (playerList.get(0).getValue().getX() == currentX && playerList.get(0).getValue().getY() == currentY)
	    		{
	    			playerList.get(0).getValue().lives -= 1;
	    			player1Hurt = true;
	    		}
	    	}
	    	
	    	if (player2Hurt == false)
	    	{
	    		if (playerList.get(1).getValue().getX() == currentX && playerList.get(1).getValue().getY() == currentY)
	    		{
	    			playerList.get(1).getValue().lives -= 1;
	    			player2Hurt = true;
	    		}
	    	}
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentY += 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		// Creates flames spreading to the right
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentX > 11)
				break;
			
			StackPane flameImage = createImage("file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	if (player1Hurt == false)
	    	{
	    		if (playerList.get(0).getValue().getX() == currentX && playerList.get(0).getValue().getY() == currentY)
	    		{
	    			playerList.get(0).getValue().lives -= 1;
	    			player1Hurt = true;
	    		}
	    	}
	    	
	    	if (player2Hurt == false)
	    	{
	    		if (playerList.get(1).getValue().getX() == currentX && playerList.get(1).getValue().getY() == currentY)
	    		{
	    			playerList.get(1).getValue().lives -= 1;
	    			player2Hurt = true;
	    		}
	    	}
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentX += 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		// Creates flames spreading to the left
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentX < 0)
				break;
			
			StackPane flameImage = createImage("file:Flame.png");
			
			HBox rowTemp = (HBox)root.getChildren().get(currentY);
	    	rowTemp.getChildren().set(currentX, flameImage);
	    	
	    	if (player1Hurt == false)
	    	{
	    		if (playerList.get(0).getValue().getX() == currentX && playerList.get(0).getValue().getY() == currentY)
	    		{
	    			playerList.get(0).getValue().lives -= 1;
	    			player1Hurt = true;
	    		}
	    	}
	    	
	    	if (player2Hurt == false)
	    	{
	    		if (playerList.get(1).getValue().getX() == currentX && playerList.get(1).getValue().getY() == currentY)
	    		{
	    			playerList.get(1).getValue().lives -= 1;
	    			player2Hurt = true;
	    		}
	    	}
	    	
	    	Triple triple = new Triple(6, null, false);
	    	map.mapArray[currentX][currentY] = triple;
	    	
	    	currentX -= 1;
		}
		
		return bomb;
	}
	
	// Method used to remove the flames from the detonation of a bomb from the screen
	public void removeFlames(Player player) {
		
		Bomb bomb = player.detonatedQueue.dequeue();
		
		int currentX = bomb.getX();
		int currentY = bomb.getY();
		
		// Removes the flames that were spread upwards
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentY < 0)
				break;
			
			if (map.mapArray[currentX][currentY].type == 6)
				createSpace(currentX, currentY);
			
	    	currentY -= 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentY > 11)
				break;
			
			if (map.mapArray[currentX][currentY].type == 6)
				createSpace(currentX, currentY);
	    	
	    	currentY += 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentX > 11)
				break;
			
			if (map.mapArray[currentX][currentY].type == 6)
				createSpace(currentX, currentY);
	    	
	    	currentX += 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();
		
		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			if (currentX < 0)
				break;
			
			if (map.mapArray[currentX][currentY].type == 6)
				createSpace(currentX, currentY);
	    	
	    	currentX -= 1;
		}
	}
	
	// Method used to spawn items on the playing field
	public void createItem() {
		
		Random random = new Random();
		int itemNum = random.nextInt(2);
		
		boolean coordinateFound = false; // Statement checking if an empty Space has been found
		
		int x = 0;
		int y = 0;
		
		// Generate random coordinates until one of them is an empty Space
		while (coordinateFound == false)
		{
			x = random.nextInt(12);
			y = random.nextInt(12);
			
			if (map.mapArray[x][y].type == 0)
				coordinateFound = true;
		}
		
		if (itemNum == 0)
		{
			BombMore bombMore = new BombMore(x, y);
			StackPane bombMoreImage = createImage("file:BombMore.jpg");
			
			HBox rowTemp = (HBox)root.getChildren().get(y);
	    	rowTemp.getChildren().set(x, bombMoreImage);
	    	
	    	Triple triple = new Triple(4, bombMore, false);
	    	map.mapArray[x][y] = triple;
		}
		
		else if (itemNum == 1)
		{
			BombBoost bombBoost = new BombBoost(x, y);
			StackPane bombBoostImage = createImage("file:BombBoost.jpg");
			
			HBox rowTemp = (HBox)root.getChildren().get(y);
	    	rowTemp.getChildren().set(x, bombBoostImage);
	    	
	    	Triple triple = new Triple(5, bombBoost, false);
	    	map.mapArray[x][y] = triple;
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		playerList = new ArrayList();
		
		root = new VBox();
		
		map = new Map();
		
		// Initializes playing field
		createMap();
		
		Scene scene = new Scene(root, 600, 700);
		
		FONT_LIVES = new Font("Arial", 30);
		FONT_BUTTON = new Font("Arial", 25);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
	
				// If there is no player on the screen, don't do anything
				if (playerList.isEmpty())
					return;
	
				// Handles movement for Player 1
				
				if (event.getCode() == KeyCode.W)
				{
					playerList.get(0).getValue().moveBoolean = true;
					playerList.get(0).getValue().moveDirection = 1;
				}
				
				else if (event.getCode() == KeyCode.S)
				{
					playerList.get(0).getValue().moveBoolean = true;
					playerList.get(0).getValue().moveDirection = 2;
				}
				
				else if (event.getCode() == KeyCode.D)
				{
					playerList.get(0).getValue().moveBoolean = true;
					playerList.get(0).getValue().moveDirection = 3;
				}
				
				else if (event.getCode() == KeyCode.A)
				{
					playerList.get(0).getValue().moveBoolean = true;
					playerList.get(0).getValue().moveDirection = 4;
				}
				
				// Handles movement for Player 2
				
				if (event.getCode() == KeyCode.UP)
				{
					playerList.get(1).getValue().moveBoolean = true;
					playerList.get(1).getValue().moveDirection = 1;
				}
				
				else if (event.getCode() == KeyCode.DOWN)
				{
					playerList.get(1).getValue().moveBoolean = true;
					playerList.get(1).getValue().moveDirection = 2;
				}
				
				else if (event.getCode() == KeyCode.RIGHT)
				{
					playerList.get(1).getValue().moveBoolean = true;
					playerList.get(1).getValue().moveDirection = 3;
				}
				
				else if (event.getCode() == KeyCode.LEFT)
				{
					playerList.get(1).getValue().moveBoolean = true;
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
				
				if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S || event.getCode() == KeyCode.D || event.getCode() == KeyCode.A)
				{
					playerList.get(0).getValue().moveBoolean = false;
					playerList.get(0).getValue().moveDirection = 0;
				}
				
				// Handles placing a bomb from Player 1
				
				if (event.getCode() == KeyCode.C && playerList.get(0).getValue().bombQueue.size() < playerList.get(0).getValue().bombMores + 1)
				{
					Bomb bomb = createBomb(playerList.get(0).getValue());
					playerList.get(0).getValue().bombQueue.enqueue(bomb);
				}
				
				// Handles stopping movement for Player 2
				
				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT)
				{
					playerList.get(1).getValue().moveBoolean = false;
					playerList.get(1).getValue().moveDirection = 0;
				}
				
				// Handles placing a bomb from Player 2
				
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
			long intervalVelocity = 150000000;
			
			long oldTimeItem = 0;
			long intervalItem = 10000000000l;
			
			@Override
			public void handle(long time) {
				
				oldTimeVelocity += 1;
				
				try {
					
					// Track collisions of players and detonations of bombs every frame
					
					upDate();
					upDateDetonate();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				// Move the players if a movement key is held down in intervals of a few milliseconds
				if (time - oldTimeVelocity > intervalVelocity) {
					try {
						upDateMove();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldTimeVelocity = time;
				}
				
				// Every 10 seconds, add an Item to the screen
				if (time - oldTimeItem > intervalItem)
				{
					try {
						spawnItem();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldTimeItem = time;
				}

			}
		};
		
		startButton = new Button("Start Game");
		startButton.setFont(FONT_BUTTON);
		
		// Button used to start the game
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				gameStart();
			}
		});
		
		root.getChildren().add(startButton);
		
		
		
		//Timer.start();
		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// This method runs every frame to track collisions and updates to the players' lives
	private void upDate() throws IOException {
		collisionCheck(1);
		collisionCheck(2);
		
		player1Lives.setText("Player 1: " + playerList.get(0).getValue().lives + " lives");
		player2Lives.setText("Player 2: " + playerList.get(1).getValue().lives + " lives");
		
		System.out.println(playerList.get(0).getValue().lives);
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
		
		playerList.get(0).getValue().moveBoolean = false;
		playerList.get(1).getValue().moveBoolean = false;
		
		/*// If the player collides with an obstacle, end the game
		if (stopGame == true)
			gameOver();*/
	}
	
	private void upDateDetonate() throws IOException {
		
		// Increments the timer on every existing bomb

		if (playerList.get(0).getValue().bombQueue.size() > 0)
		{	
			for (int i = 0; i < playerList.get(0).getValue().bombQueue.size(); i += 1)
				playerList.get(0).getValue().bombQueue.queue[i].timer += 1;
			
			if (playerList.get(0).getValue().bombQueue.peek().timer >= bombMax)
				detonate(playerList.get(0).getValue());
		}
		
		if (playerList.get(1).getValue().bombQueue.size() > 0)
		{
			for (int i = 0; i < playerList.get(1).getValue().bombQueue.size(); i += 1)
				playerList.get(1).getValue().bombQueue.queue[i].timer += 1;
			
			if (playerList.get(1).getValue().bombQueue.peek().timer >= bombMax)
				detonate(playerList.get(1).getValue());
		}
		
		if (playerList.get(0).getValue().detonatedQueue.size() > 0)
		{
			for (int i = 0; i < playerList.get(0).getValue().detonatedQueue.size(); i += 1)
				playerList.get(0).getValue().detonatedQueue.queue[i].timerDisappear += 1;
			
			if (playerList.get(0).getValue().detonatedQueue.peek().timerDisappear >= detonationMax)
				removeFlames(playerList.get(0).getValue());
		}
		
		if (playerList.get(1).getValue().detonatedQueue.size() > 0)
		{
			for (int i = 0; i < playerList.get(1).getValue().detonatedQueue.size(); i += 1)
				playerList.get(1).getValue().detonatedQueue.queue[i].timerDisappear += 1;
			
			if (playerList.get(1).getValue().detonatedQueue.peek().timerDisappear >= detonationMax)
				removeFlames(playerList.get(1).getValue());
		}
	}
	
	// Every 10 seconds, an item will spawn on the field
	private void spawnItem() throws IOException {
		createItem();
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
