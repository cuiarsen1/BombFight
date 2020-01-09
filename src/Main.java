import javafx.application.Application;
import javafx.scene.Group;
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

	protected ArrayList<ImageView> playerViews;
	protected ArrayList<ImageView> bombViews;
	protected ArrayList<ImageView> crateViews;
	protected ArrayList<ImageView> spaceViews;
	protected ArrayList<ImageView> bombMoreViews;
	protected ArrayList<ImageView> bombBoostViews;
	
	public Map map;

	protected VBox root; // root VBox displaying the main scene
	
	public void createMap(String imageFile) {
		
		HBox tempBox;
		
		for (int i = 0; i < 12; i += 1)
		{
			tempBox = new HBox();
			
			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = new StackPane();
				
				Image tempCrate = new Image(imageFile);
				ImageView tempCrateView = new ImageView(tempCrate);
				tempCrateView.setFitHeight(50);
				tempCrateView.setFitWidth(50);
				
				pane.getChildren().add(tempCrateView);
				
				tempBox.getChildren().add(pane);
			}
			
			root.getChildren().add(tempBox);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		playerList = new ArrayList();
		bombList = new ArrayList();

		playerViews = new ArrayList<ImageView>();
		bombViews = new ArrayList();
		crateViews = new ArrayList();
		spaceViews = new ArrayList();
		bombMoreViews = new ArrayList();
		bombBoostViews = new ArrayList();
		
		root = new VBox();
		
		map = new Map();
		
		// Initializes playing field
		createMap("file:Crate.png");
		
		//map.createMap();

		Player player1 = new Player(0, 0, 1);
  
		//player1.createImage("file:PlayerRed.png");
		
		//player1.createImage("PlayerRed.png");
		
		
		ImageView playerView = new ImageView();
    	
		Image playerImage = new Image("file:PlayerRed.png");
		
		playerView = new ImageView(playerImage);
		playerView.setFitHeight(50);
		playerView.setFitWidth(50);
		
		playerViews.add(playerView);
    	
    	HBox row = (HBox)root.getChildren().get(player1.y);
    	StackPane coordinate = (StackPane) row.getChildren().get(player1.x);
    	
    	Image spaceImage = new Image("file:Space.png");
    	
    	ImageView spaceImageView = new ImageView(spaceImage);
		spaceImageView.setFitHeight(50);
		spaceImageView.setFitWidth(50);
    	
    	coordinate.getChildren().setAll(spaceImageView, playerView);
		playerList.add(player1);
  	
		
		 

		// TESTING
		// Pair pair = new Pair(1, player1);
		// int a = pair.type;

		/*Image tempCrate = new Image("file:Crate.png");
		ImageView tempCrateView = new ImageView();
		tempCrateView.setFitHeight(50);
		tempCrateView.setFitWidth(50);
		tempCrateView.setImage(tempCrate);

		Image player = new Image("file:PlayerRed.png");
		ImageView playerView = new ImageView();
		playerView.setFitHeight(50);
		playerView.setFitWidth(50);
		playerView.setImage(player);

		StackPane pane = new StackPane();

		HBox hbox = new HBox();

		pane.getChildren().addAll(tempCrateView, playerView);

		hbox.getChildren().addAll(pane);

		root = new VBox();

		for (int j = 0; j < 11; j += 1) {
			Image crate = new Image("file:Crate.png");
			ImageView crateView = new ImageView();
			crateView.setFitHeight(50);
			crateView.setFitWidth(50);
			crateView.setImage(crate);
			StackPane temppane = new StackPane();
			temppane.getChildren().addAll(crateView);
			hbox.getChildren().add(temppane);
		}
		// maybe make a for loop to create new hbox rows to add some number of hboxes
		// without repeat code.
		// of course, some rows are different, with spaces instead of crates, but just
		// hard code that part too.
		root.getChildren().add(hbox);

		for (int i = 0; i < 11; i += 1) {
			HBox tempbox = new HBox();

			for (int j = 0; j < 12; j += 1) {
				Image crate = new Image("file:Crate.png");
				ImageView crateView = new ImageView();

				crateView.setFitHeight(50);
				crateView.setFitWidth(50);
				crateView.setImage(crate);
				StackPane temppane = new StackPane();
				temppane.getChildren().addAll(crateView);
				tempbox.getChildren().add(temppane);
			}

			root.getChildren().add(tempbox);
		}*/
	
		/*root = new VBox();
		
		Image crate = new Image("file:Crate.png");
		ImageView crateView = new ImageView(crate);
		crateView.setFitHeight(50);
		crateView.setFitWidth(50);
		
		root.getChildren().add(crateView);*/

		Scene scene = new Scene(root, 600, 600);

		primaryStage.setTitle("Bomb Fight");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
