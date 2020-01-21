// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
/*This program is a 2D game played by 2 players on a 12x12 grid. Each player has 3 lives, and if 
one of the players loses all 3 of their lives, the other player wins. Each player can place bombs, 
and the bomb will detonate in the shape of a plus sign, in all 4 directions (up, down, left and 
right). If a Player is hit by the detonation, they lose a life. Player 1 moves with WASD, and 
Player 2 moves with the arrow keys. Player 1 places a bomb down with the C button, and Player 2
places a bomb down with the slash (/) button. Items spawn around the screen every 10 seconds. The
items a Player could get is a Bomb More (Allows the Player to place 1 more Bomb at a time) and a 
Bomb Boost (increases the range of the Player's bomb detonation by 1 grid in all directions)*/

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

public class Main extends Application {

	private ArrayList<Player> playerList; // List storing the Player objects

	protected Map map; // Map containing the mapArray used to track the location of all objects

	protected VBox root; // root VBox displaying the main scene

	private AnimationTimer Timer; // Timer used to check for events

	private long bombMax = 180; // The amount of time in frames before a bomb detonates
	private long detonationMax = 30; // Time in frames before the flames from a bomb disappear

	// Labels displaying the current amount of lives of each player
	private Label player1Lives;
	private Label player2Lives;

	private Button startButton; // Button used to start the game

	// Fonts for different labels and buttons
	private Font FONT_LIVES;
	private Font FONT_BUTTON;

	// Method used to create a single image as a StackPane, either a Space, Crate,
	// Item, or Flame
	public StackPane createImage(String imageFile) {

		StackPane pane = new StackPane();

		Image tempImage = new Image(imageFile);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setFitHeight(50);
		tempImageView.setFitWidth(50);

		pane.getChildren().add(tempImageView);

		return pane;
	}

	/* Creates an image as a StackPane for the case when there are 2 images on top of each other,
	 * such as a Bomb on a grid or a Player on a grid */
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

	// Creates and initializes the game board
	public void createMap() {

		// Initializes rows 1 to 5 with all Crates

		for (int i = 0; i < 5; i += 1)
		{
			HBox tempBox = new HBox();

			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);

				// Records the object into the map array
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}

			root.getChildren().add(tempBox);
		}

		// Initializes rows 6 to 7 with 2 Spaces on either side and Crates in the middle

		for (int i = 5; i < 7; i += 1)
		{
			HBox tempBox = new HBox();

			for (int j = 0; j < 2; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);

				// Records the object into the map array
				Triple triple = new Triple(0, null, false);
				map.mapArray[j][i] = triple;
			}

			for (int j = 2; j < 10; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);

				// Records the object into the map array
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}

			for (int j = 10; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);

				// Records the object into the map array
				Triple triple = new Triple(0, null, false);
				map.mapArray[j][i] = triple;
			}

			root.getChildren().add(tempBox);
		}

		// Initializes rows 8 to 12 with all Crates

		for (int i = 7; i < 12; i += 1)
		{
			HBox tempBox = new HBox();

			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);

				// Records the object into the map array
				Triple triple = new Triple(1, null, false);
				map.mapArray[j][i] = triple;
			}

			root.getChildren().add(tempBox);
		}
	}

	// Method used to create a Space object
	public void createSpace(int x, int y) {

		StackPane spaceImage = createImage("file:Space.png");

		// Gets the specific coordinate on the grid and sets it to a Space
		HBox row = (HBox) root.getChildren().get(y);
		row.getChildren().set(x, spaceImage);

		// Records the object into the map array
		Triple triple = new Triple(0, null, false);
		map.mapArray[x][y] = triple;
	}

	// Method used to start the game
	public void gameStart() {

		// Initializes the initial coordinates of the players
		
		int player1X = 0;
		int player1Y = 6;

		int player2X = 11;
		int player2Y = 5;

		// Creates both Players
		createPlayer(1, player1X, player1Y);
		createPlayer(2, player2X, player2Y);

		root.getChildren().remove(startButton); // Removes the start game button after starting

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

		Timer.start(); // Starts the game timer
	}

	// Creates and initializes a Player on the field
	public void createPlayer(int playerNum, int x, int y) {

		Player player = null;

		StackPane playerImage = null;

		// Creates a Player with a different image based on whether they are Player 1 or
		// 2

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

		// Adds the Player image to a specific coordinate on the grid
		HBox row = (HBox) root.getChildren().get(y);
		row.getChildren().set(x, playerImage);

		// Records the object into the map array
		Triple triple = new Triple(2, player, false);
		map.mapArray[x][y] = triple;

		playerList.add(new Node<Player>(player)); // Adds the Player to the list of Players
	}

	// Method used to initialize a bomb and display it on the screen
	public Bomb createBomb(Player player) {

		Bomb bomb = new Bomb(player.getX(), player.getY()); // Initialize Bomb
		StackPane bombImage = null;

		// Creates the Bomb image based on which Player placed it down

		if (player.playerNum == 1)
		{
			bombImage = bomb.createImage("file:Bomb.png", "file:PlayerRed.png");
		}

		else if (player.playerNum == 2)
		{
			bombImage = bomb.createImage("file:Bomb.png", "file:PlayerBlue.png");
		}

		// Adds the Bomb image to a specific coordinate on the grid
		HBox row = (HBox) root.getChildren().get(player.getY());
		row.getChildren().set(player.getX(), bombImage);

		// Records the Bomb into the map array
		map.mapArray[player.getX()][player.getY()].bombExist = true;

		return bomb;
	}

	// Method used to move players. The integer direction is represented with:
	// 1 = Up, 2 = Down, 3 = Right, 4 = Left
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

		StackPane playerView = playerList.get(playerNum - 1).getValue()
				.createImage("file:Space.png", playerFile);

		// Add the Player image to the new coordinate it moved to

		HBox row = (HBox) root.getChildren().get(playerList.get(playerNum - 1).getValue().getY());
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
			HBox rowTemp = (HBox) root.getChildren().get(prevY);
			rowTemp.getChildren().set(prevX, bombImage);

			// Changes the previous grid the player was in to register there is a bomb there
			Triple triple = new Triple(0, null, true);
			map.mapArray[prevX][prevY] = triple;
		}

		// If the player picks up an Item, give them the corresponding power up

		if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
				.get(playerNum - 1).getValue().getY()].type == 3
				&& playerList.get(playerNum - 1).getValue().bombMores < 3)
		{
			// If picked up a Bomb More, allow the Player to place more Bombs at a time to a
			// maximum of 4

			BombMore bombMore = (BombMore) map.mapArray[playerList.get(playerNum - 1).getValue()
					.getX()][playerList.get(playerNum - 1).getValue().getY()].object;
			bombMore.powerUp(playerList.get(playerNum - 1).getValue());
		}

		if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
				.get(playerNum - 1).getValue().getY()].type == 4
				&& playerList.get(playerNum - 1).getValue().bombBoosts < 3)
		{
			// If picked up a Bomb Boost, the bombs the Player places down will have
			// increased range

			BombBoost bombBoost = (BombBoost) map.mapArray[playerList.get(playerNum - 1).getValue()
					.getX()][playerList.get(playerNum - 1).getValue().getY()].object;
			bombBoost.powerUp(playerList.get(playerNum - 1).getValue());
		}

		// If the player walks into flames from a detonation, they lose 1 life
		if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
				.get(playerNum - 1).getValue().getY()].type == 5)
		{
			playerList.get(playerNum - 1).getValue().lives -= 1;
		}

		// Update the map on where the player is currently located
		Triple triple = new Triple(2, playerList.get(playerNum - 1).getValue(), false);
		map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList.get(playerNum - 1)
				.getValue().getY()] = triple;
	}

	// Checks for collisions with all the edges of the screen, crate, bomb, or
	// player
	public void collisionCheck(int playerNum) {

		if (playerList.isEmpty())
			return;

		// If the Player is attempting to move up
		if (playerList.get(playerNum - 1).getValue().moveDirection == 1)
		{
			// If the Player is within the grid
			if (playerList.get(playerNum - 1).getValue().getY() - 1 >= 0)
			{
				// If the grid that the Player is trying to move to is not a Crate, Player or
				// Bomb
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
						.get(playerNum - 1).getValue().getY() - 1].type != 1
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() - 1].type != 2
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() - 1].bombExist == false
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() - 1].type != 5)
				{
					// Allow the Player to move
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				// If the grid that the Player is trying to move to is a Crate, Player or Bomb
				else
					// Stop the Player from moving
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			// If the player hits the edge of the screen, stop them from moving further
			else
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}

		// If the Player is attempting to move down
		if (playerList.get(playerNum - 1).getValue().moveDirection == 2)
		{
			// If the Player is within the grid
			if (playerList.get(playerNum - 1).getValue().getY() + 1 <= 11)
			{
				// If the grid that the Player is trying to move to is not a Crate, Player or
				// Bomb
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
						.get(playerNum - 1).getValue().getY() + 1].type != 1
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() + 1].type != 2
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() + 1].bombExist == false
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()][playerList
								.get(playerNum - 1).getValue().getY() + 1].type != 5)
				{
					// Allow the Player to move
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				// If the grid that the Player is trying to move to is a Crate, Player or Bomb
				else
					// Stop the Player from moving
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			// If the player hits the edge of the screen, stop them from moving further
			else
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}

		// If the Player is attempting to move right
		if (playerList.get(playerNum - 1).getValue().moveDirection == 3)
		{
			// If the Player is within the grid
			if (playerList.get(playerNum - 1).getValue().getX() + 1 <= 11)
			{
				// If the grid that the Player is trying to move to is not a Crate, Player or
				// Bomb
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX() + 1][playerList
						.get(playerNum - 1).getValue().getY()].type != 1
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								+ 1][playerList.get(playerNum - 1).getValue().getY()].type != 2
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								+ 1][playerList.get(playerNum - 1).getValue()
										.getY()].bombExist == false
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								+ 1][playerList.get(playerNum - 1).getValue().getY()].type != 5)
				{
					// Allow the Player to move
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				// If the grid that the Player is trying to move to is a Crate, Player or Bomb
				else
					// Stop the Player from moving
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			// If the player hits the edge of the screen, stop them from moving further
			else
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}

		// If the Player is attempting to move left
		if (playerList.get(playerNum - 1).getValue().moveDirection == 4)
		{
			// If the Player is within the grid
			if (playerList.get(playerNum - 1).getValue().getX() - 1 >= 0)
			{
				// If the grid that the Player is trying to move to is not a Crate, Player or
				// Bomb
				if (map.mapArray[playerList.get(playerNum - 1).getValue().getX() - 1][playerList
						.get(playerNum - 1).getValue().getY()].type != 1
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								- 1][playerList.get(playerNum - 1).getValue().getY()].type != 2
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								- 1][playerList.get(playerNum - 1).getValue()
										.getY()].bombExist == false
						&& map.mapArray[playerList.get(playerNum - 1).getValue().getX()
								- 1][playerList.get(playerNum - 1).getValue().getY()].type != 5)
				{
					// Allow the Player to move
					playerList.get(playerNum - 1).getValue().moveBoolean = true;
				}
				// If the grid that the Player is trying to move to is a Crate, Player or Bomb
				else
					// Stop the Player from moving
					playerList.get(playerNum - 1).getValue().moveBoolean = false;
			}
			// If the player hits the edge of the screen, stop them from moving further
			else
				playerList.get(playerNum - 1).getValue().moveBoolean = false;
		}
	}

	// Creates a flame from a detonation where there is no Player on the same grid
	public void createFlame(int x, int y, String imageFile) {

		StackPane flameImage = createImage(imageFile);

		HBox rowTemp = (HBox) root.getChildren().get(y);
		rowTemp.getChildren().set(x, flameImage);

		// Updates the map to track the flame
		Triple triple = new Triple(5, null, false);
		map.mapArray[x][y] = triple;
	}

	// Creates a flame from a detonation where there is a Player on the same grid
	public void createFlame(int x, int y, String imageFile, Player player) {

		StackPane flameImage = createImage(imageFile);

		HBox rowTemp = (HBox) root.getChildren().get(y);
		rowTemp.getChildren().set(x, flameImage);

		// Keeps the grid updated as a Player to keep track of their location
		Triple triple = new Triple(2, player, false);
		map.mapArray[x][y] = triple;
	}

	// Method used to detonate a Player's Bomb
	public Bomb detonate(Player player) {

		/* Removes this bomb from the active bombs queue, and adds it to the queue tracking when to
		 * make the detonation flames disappear */

		Bomb bomb = player.bombQueue.dequeue();
		player.detonatedQueue.enqueue(bomb);
		
		int initialRange = 3; // The range of each bomb without any powerups

		/* Gets the coordinates of the Bomb, which will then be incremented later in all 4
		 * directions to extend the range of the flames */
		int currentX = bomb.getX();
		int currentY = bomb.getY();

		/* Booleans tracking whether a player has already been hit by the detonation of a specific
		 * bomb */

		boolean player1Hurt = false;
		boolean player2Hurt = false;

		/* Creates flames from the detonation of the Bomb, spreading outwards in all 4 directions
		 * from the Bomb */

		// Creates flames spreading upwards

		for (int i = 0; i < initialRange + player.bombBoosts; i += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentY < 0)
				break;

			// If Player 1 is standing in the path of a detonation
			if (playerList.get(0).getValue().getX() == currentX
					&& playerList.get(0).getValue().getY() == currentY)
			{
				// If Player 1 has not already been hurt by the detonation of this Bomb
				if (player1Hurt == false)
				{
					// Make Player 1 lose a life
					playerList.get(0).getValue().lives -= 1;
					player1Hurt = true;
				}

				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(0).getValue());
				
			}
			// If Player 1 is not standing in the path of the detonation
			else if (playerList.get(0).getValue().getX() != currentX
					|| playerList.get(0).getValue().getY() != currentY)
			{
				// Create the flames of the detonation without worrying about the player location
				createFlame(currentX, currentY, "file:Flame.png");
			}
			
			// If Player 2 is standing in the path of a detonation
			if (playerList.get(1).getValue().getX() == currentX
					&& playerList.get(1).getValue().getY() == currentY)
			{
				// If Player 2 has not already been hurt by the detonation of this Bomb
				if (player2Hurt == false)
				{
					// Make Player 2 lose a life
					playerList.get(1).getValue().lives -= 1;
					player2Hurt = true;
				}
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(1).getValue());
			} 
			// If Player 2 is not standing in the path of the detonation
			else if (playerList.get(1).getValue().getX() != currentX
					|| playerList.get(1).getValue().getY() != currentY)
			{
				/* If Player 1 was not hit by this bomb's detonation (meaning they are not in this
				 * grid), then create a flame here. If Player 1 was hit by this detonation, that
				 * means that flames were already created for this grid and no longer needs to be
				 * created a second time */
				if (player1Hurt == false)
				{
					// Create the flames of the detonation
					createFlame(currentX, currentY, "file:Flame.png");
				}
			}
			
			// Move the coordinate up to account for the next grid
			currentY -= 1;
		}
		
		currentX = bomb.getX();
		currentY = bomb.getY();

		// Creates flames spreading downwards

		for (int i = 0; i < initialRange + player.bombBoosts; i += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentY > 11)
				break;
			
			// If Player 1 is standing in the path of a detonation
			if (playerList.get(0).getValue().getX() == currentX
					&& playerList.get(0).getValue().getY() == currentY)
			{
				// If Player 1 has not already been hurt by the detonation of this Bomb
				if (player1Hurt == false)
				{
					// Make Player 1 lose a life
					playerList.get(0).getValue().lives -= 1;
					player1Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(0).getValue());
			}
			
			// If Player 1 is not standing in the path of the detonation
			else if (playerList.get(0).getValue().getX() != currentX
					|| playerList.get(0).getValue().getY() != currentY)
			{
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png");
			}
			
			// If Player 2 is standing in the path of a detonation
			if (playerList.get(1).getValue().getX() == currentX
					&& playerList.get(1).getValue().getY() == currentY)
			{
				// If Player 2 has not already been hurt by the detonation of this Bomb
				if (player2Hurt == false)
				{
					// Make Player 2 lose a life
					playerList.get(1).getValue().lives -= 1;
					player2Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(1).getValue());
			}
			
			// If Player 2 is not standing in the path of the detonation
			else if (playerList.get(1).getValue().getX() != currentX
					|| playerList.get(1).getValue().getY() != currentY)
			{
				/* If Player 1 was not hit by this bomb's detonation (meaning they are not in this
				 * grid), then create a flame here. If Player 1 was hit by this detonation, that
				 * means that flames were already created for this grid and no longer needs to be
				 * created a second time */
				if (player1Hurt == false)
				{
					// Create the flames of the detonation
					createFlame(currentX, currentY, "file:Flame.png");
				}
			}

			// Move the coordinate down to account for the next grid
			currentY += 1;
		}

		currentX = bomb.getX();
		currentY = bomb.getY();

		// Creates flames spreading to the right
		
		for (int i = 0; i < initialRange + player.bombBoosts; i += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentX > 11)
				break;
			
			// If Player 1 is standing in the path of a detonation
			if (playerList.get(0).getValue().getX() == currentX
					&& playerList.get(0).getValue().getY() == currentY)
			{
				// If Player 1 has not already been hurt by the detonation of this Bomb
				if (player1Hurt == false)
				{
					// Make Player 1 lose a life
					playerList.get(0).getValue().lives -= 1;
					player1Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(0).getValue());
			}
			
			// If Player 1 is not standing in the path of the detonation
			else if (playerList.get(0).getValue().getX() != currentX
					|| playerList.get(0).getValue().getY() != currentY)
			{
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png");
			}
			
			// If Player 2 is standing in the path of a detonation
			if (playerList.get(1).getValue().getX() == currentX
					&& playerList.get(1).getValue().getY() == currentY)
			{
				// If Player 2 has not already been hurt by the detonation of this Bomb
				if (player2Hurt == false)
				{
					// Make Player 2 lose a life
					playerList.get(1).getValue().lives -= 1;
					player2Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(1).getValue());
			}
			
			// If Player 2 is not standing in the path of the detonation
			else if (playerList.get(1).getValue().getX() != currentX
					|| playerList.get(1).getValue().getY() != currentY)
			{
				/* If Player 1 was not hit by this bomb's detonation (meaning they are not in this
				 * grid), then create a flame here. If Player 1 was hit by this detonation, that
				 * means that flames were already created for this grid and no longer needs to be
				 * created a second time */
				if (player1Hurt == false)
				{
					// Create the flames of the detonation
					createFlame(currentX, currentY, "file:Flame.png");
				}
			}
			
			// Move the coordinate to the right to account for the next grid
			currentX += 1;
		}

		currentX = bomb.getX();
		currentY = bomb.getY();

		// Creates flames spreading to the left

		for (int i = 0; i < initialRange + player.bombBoosts; i += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentX < 0)
				break;
			
			// If Player 1 is standing in the path of a detonation
			if (playerList.get(0).getValue().getX() == currentX
					&& playerList.get(0).getValue().getY() == currentY)
			{
				// If Player 1 has not already been hurt by the detonation of this Bomb
				if (player1Hurt == false)
				{
					// Make Player 1 lose a life
					playerList.get(0).getValue().lives -= 1;
					player1Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(0).getValue());
			}
			
			// If Player 1 is not standing in the path of the detonation
			else if (playerList.get(0).getValue().getX() != currentX
					|| playerList.get(0).getValue().getY() != currentY)
			{
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png");
			}
			
			// If Player 2 is standing in the path of a detonation
			if (playerList.get(1).getValue().getX() == currentX
					&& playerList.get(1).getValue().getY() == currentY)
			{
				// If Player 2 has not already been hurt by the detonation of this Bomb
				if (player2Hurt == false)
				{
					// Make Player 2 lose a life
					playerList.get(1).getValue().lives -= 1;
					player2Hurt = true;
				}
				
				// Create the flames of the detonation
				createFlame(currentX, currentY, "file:Flame.png", playerList.get(1).getValue());
			}
			
			// If Player 2 is not standing in the path of the detonation
			else if (playerList.get(1).getValue().getX() != currentX
					|| playerList.get(1).getValue().getY() != currentY)
			{
				/* If Player 1 was not hit by this bomb's detonation (meaning they are not in this
				 * grid), then create a flame here. If Player 1 was hit by this detonation, that
				 * means that flames were already created for this grid and no longer needs to be
				 * created a second time */
				if (player1Hurt == false)
				{
					// Create the flames of the detonation
					createFlame(currentX, currentY, "file:Flame.png");
				}
			}
			
			// Move the coordinate to the left to account for the next grid
			currentX -= 1;
		}

		return bomb;
	}
	
	// Method used to check whether a grid containing a flame has a Player in it or just a flame
	public void flameCheck(int x, int y) {
		
		// If this grid contains a flame, remove it
		if (map.mapArray[x][y].type == 5)
			createSpace(x, y);
		
		
		// If this grid contains a flame and a Player, keep the Player there
		else if (map.mapArray[x][y].type == 2)
		{
			Player tempPlayer = (Player) map.mapArray[x][y].object;
			
			// Case of Player 1
			if (tempPlayer.playerNum == 1)
			{
				StackPane playerImage = createImage("file:Space.png", "file:PlayerRed.png");
				HBox rowTemp = (HBox) root.getChildren().get(y);
				rowTemp.getChildren().set(x, playerImage);
			} 
			
			// Case of Player 2
			else if (tempPlayer.playerNum == 2)
			{
				StackPane playerImage = createImage("file:Space.png", "file:PlayerBlue.png");
				HBox rowTemp = (HBox) root.getChildren().get(y);
				rowTemp.getChildren().set(x, playerImage);
			}
		}
	}
	
	// Method used to remove the flames from the detonation of a bomb from the
	// screen
	public void removeFlames(Player player) {
		
		// Removes the Bomb from the queue of bombs that have already been detonated
		Bomb bomb = player.detonatedQueue.dequeue();
		
		// Current coodinates of the Bomb
		int currentX = bomb.getX();
		int currentY = bomb.getY();

		// Removes the flames that were spread upwards

		for (int i = 0; i < 3 + player.bombBoosts; i += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentY < 0)
				break;
			
			/*Checks if the grid containing the flame also contains
			a Player, and changes the image accordingly*/
			flameCheck(currentX, currentY);
			
			// Move the coordinate up to account for the next grid
			currentY -= 1;
		}

		currentX = bomb.getX();
		currentY = bomb.getY();

		// Removes the flames that were spread downwards
		
		for (int j = 0; j < 3 + player.bombBoosts; j += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentY > 11)
				break;
			
			/*Checks if the grid containing the flame also contains
			a Player, and changes the image accordingly*/
			flameCheck(currentX, currentY);
			
			// Move the coordinate down to account for the next grid
			currentY += 1;
		}

		currentX = bomb.getX();
		currentY = bomb.getY();
		
		// Removes the flames that were spread to the right

		for (int k = 0; k < 3 + player.bombBoosts; k += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentX > 11)
				break;
			
			/*Checks if the grid containing the flame also contains
			a Player, and changes the image accordingly*/
			flameCheck(currentX, currentY);
			
			// Move the coordinate to the right to account for the next grid
			currentX += 1;
		}

		currentX = bomb.getX();
		currentY = bomb.getY();
		
		// Removes the flames that were spread to the left

		for (int x = 0; x < 3 + player.bombBoosts; x += 1)
		{
			// If the flames have already hit the edge of the screen, don't continue further
			if (currentX < 0)
				break;
			
			/*Checks if the grid containing the flame also contains
			a Player, and changes the image accordingly*/
			flameCheck(currentX, currentY);

			currentX -= 1;
		}
	}

	// Method used to spawn items on the playing field
	public void createItem() {

		// Generates either a Bomb More or Bomb Boost
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
		
		// If the item generated is a Bomb More, add it to the playing field
		if (itemNum == 0)
		{
			BombMore bombMore = new BombMore(x, y);
			StackPane bombMoreImage = createImage("file:BombMore.jpg");

			HBox rowTemp = (HBox) root.getChildren().get(y);
			rowTemp.getChildren().set(x, bombMoreImage);

			Triple triple = new Triple(3, bombMore, false);
			map.mapArray[x][y] = triple;
		}
		
		// If the item generated is a Bomb Boost, add it to the playing field
		else if (itemNum == 1)
		{
			BombBoost bombBoost = new BombBoost(x, y);
			StackPane bombBoostImage = createImage("file:BombBoost.jpg");

			HBox rowTemp = (HBox) root.getChildren().get(y);
			rowTemp.getChildren().set(x, bombBoostImage);

			Triple triple = new Triple(4, bombBoost, false);
			map.mapArray[x][y] = triple;
		}
	}

	// Method used to end the game
	public void gameEnd(int playerWin) {
		
		// Clear the screen
		root.getChildren().clear();
		
		Label endLabel = new Label();
		endLabel.setFont(FONT_LIVES);
		
		// Display the winner
		
		if (playerWin == 1)
		{
			endLabel.setText("Player 1 Wins!");
		}

		else if (playerWin == 2)
		{
			endLabel.setText("Player 2 Wins!");
		}
		
		root.getChildren().add(endLabel);

		Timer.stop(); // Stop the game timer
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

				if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S
						|| event.getCode() == KeyCode.D || event.getCode() == KeyCode.A)
				{
					playerList.get(0).getValue().moveBoolean = false;
					playerList.get(0).getValue().moveDirection = 0;
				}

				// Handles placing a bomb from Player 1

				if (event.getCode() == KeyCode.C && playerList.get(0).getValue().bombQueue
						.size() < playerList.get(0).getValue().bombMores + 1)
				{
					Bomb bomb = createBomb(playerList.get(0).getValue());
					playerList.get(0).getValue().bombQueue.enqueue(bomb);
				}

				// Handles stopping movement for Player 2

				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
						|| event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT)
				{
					playerList.get(1).getValue().moveBoolean = false;
					playerList.get(1).getValue().moveDirection = 0;
				}

				// Handles placing a bomb from Player 2

				if (event.getCode() == KeyCode.SLASH && playerList.get(1).getValue().bombQueue
						.size() < playerList.get(1).getValue().bombMores + 1)
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

				try
				{
					// Tracks collisions and Player lives every frame
					upDate();

					// Track detonations of bombs every frame
					upDateDetonate();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Move the players if a movement key is held down in intervals of 150 milliseconds
				if (time - oldTimeVelocity > intervalVelocity)
				{
					try
					{
						upDateMove();

					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldTimeVelocity = time;
				}

				// Every 10 seconds, add an Item to the screen
				if (time - oldTimeItem > intervalItem)
				{
					try
					{
						spawnItem();

					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					oldTimeItem = time;
				}

			}
		};
		
		// Button used to start the game
		startButton = new Button("Start Game");
		startButton.setFont(FONT_BUTTON);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				gameStart();
			}
		});

		root.getChildren().add(startButton);

		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// This method runs every frame to track collisions and updates to the Players'
	// lives
	private void upDate() throws IOException {

		// Checks for collisions for both players
		collisionCheck(1);
		collisionCheck(2);

		// Updates the amount of lives of each Player
		
		player1Lives.setText("Player 1: " + playerList.get(0).getValue().lives + " lives");
		player2Lives.setText("Player 2: " + playerList.get(1).getValue().lives + " lives");

		// If any of the players loses all their lives, the game ends and the other
		// player wins

		if (playerList.get(0).getValue().lives <= 0)
		{
			gameEnd(2);
		}

		if (playerList.get(1).getValue().lives <= 0)
		{
			gameEnd(1);
		}
	}

	// Every 150 milliseconds, if a movement key is held down, the Player will move
	private void upDateMove() throws IOException {

		if (playerList.get(0).getValue().moveBoolean == true)
		{
			movePlayers(1, "file:PlayerRed.png");
		}

		if (playerList.get(1).getValue().moveBoolean == true)
		{
			movePlayers(2, "file:PlayerBlue.png");
		}
	}
	
	// Tracks the detonations of existing Bombs every frame
	private void upDateDetonate() throws IOException {

		// Increments the timer on every existing Bomb to track when to detonate

		if (playerList.get(0).getValue().bombQueue.isEmpty() == false)
		{
			for (int i = 0; i < playerList.get(0).getValue().bombQueue.size(); i += 1)
				playerList.get(0).getValue().bombQueue.queue[i].timer += 1;

			// If the timer on this Bomb reaches the end, it will detonate
			if (playerList.get(0).getValue().bombQueue.peek().timer >= bombMax)
				detonate(playerList.get(0).getValue());
		}

		if (playerList.get(1).getValue().bombQueue.isEmpty() == false)
		{
			for (int i = 0; i < playerList.get(1).getValue().bombQueue.size(); i += 1)
				playerList.get(1).getValue().bombQueue.queue[i].timer += 1;
			
			// If the timer on this Bomb reaches the end, it will detonate
			if (playerList.get(1).getValue().bombQueue.peek().timer >= bombMax)
				detonate(playerList.get(1).getValue());
		}
		
		/*Increments the timer on every existing Bomb to track
		when to remove the flames from a detonation*/
		
		if (playerList.get(0).getValue().detonatedQueue.isEmpty() == false)
		{
			for (int i = 0; i < playerList.get(0).getValue().detonatedQueue.size(); i += 1)
				playerList.get(0).getValue().detonatedQueue.queue[i].timerDisappear += 1;
			
			// If the timer on this Bomb reaches the end, remove its flames from the screen
			if (playerList.get(0).getValue().detonatedQueue.peek().timerDisappear >= detonationMax)
				removeFlames(playerList.get(0).getValue());
		}

		if (playerList.get(1).getValue().detonatedQueue.isEmpty() == false)
		{
			for (int i = 0; i < playerList.get(1).getValue().detonatedQueue.size(); i += 1)
				playerList.get(1).getValue().detonatedQueue.queue[i].timerDisappear += 1;
			
			// If the timer on this Bomb reaches the end, remove its flames from the screen
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
