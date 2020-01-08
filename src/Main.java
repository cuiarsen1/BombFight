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
    
    VBox root; // root VBox displaying the main scene

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	playerList = new ArrayList();
    	bombList = new ArrayList();
    	
    	playerViews = new ArrayList();
    	bombViews = new ArrayList();
    	crateViews = new ArrayList();
    	spaceViews = new ArrayList();
    	bombMoreViews = new ArrayList();
    	bombBoostViews = new ArrayList();
    	
    	
    	
    	
    	Player player1 = new Player(1, 1, 1);
    	
    	player1.createImage("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\PlayerRed.png");
    	playerList.add(player1);
    	
    	root = new VBox();
    	
    	root.getChildren().addAll(playerViews);

    	 // TESTING
       // Pair pair = new Pair(1, player1);
        //int a = pair.type;
        
   /* 	Image tempCrate = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Crate.png");
        ImageView tempCrateView = new ImageView();
        tempCrateView.setFitHeight(50);
        tempCrateView.setFitWidth(50);
        tempCrateView.setImage(tempCrate);
        
        Image player = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\PlayerRed.png");
        ImageView playerView = new ImageView();
        playerView.setFitHeight(50);
        playerView.setFitWidth(50);
        playerView.setImage(player);
        
        StackPane pane = new StackPane();
        
        HBox hbox = new HBox();
        
        pane.getChildren().addAll(tempCrateView, playerView);
        
        hbox.getChildren().addAll(pane);
       
        root = new VBox();
        
    	for (int j = 0; j < 11; j += 1)
	    {
    		Image crate = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Crate.png");
	        ImageView crateView = new ImageView();
	        crateView.setFitHeight(50);
	        crateView.setFitWidth(50);
	        crateView.setImage(crate);
	        StackPane temppane = new StackPane();
	        temppane.getChildren().addAll(crateView);
	     	hbox.getChildren().add(temppane);
	    }
    	// maybe make a for loop to create new hbox rows to add some number of hboxes without repeat code.
        // of course, some rows are different, with spaces instead of crates, but just hard code that part too.
    	root.getChildren().add(hbox);
    	
    	for (int i = 0; i < 11; i += 1)
    	{
    		HBox tempbox = new HBox();
    		
    		for (int j = 0; j < 12; j += 1)
    		{
    			Image crate = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Crate.png");
    	        ImageView crateView = new ImageView();       
    	       
    	        crateView.setFitHeight(50);
    	        crateView.setFitWidth(50);
    	        crateView.setImage(crate);
    	        StackPane temppane = new StackPane();
    	        temppane.getChildren().addAll(crateView);
    	        tempbox.getChildren().add(temppane);
    		}
    		
    		root.getChildren().add(tempbox);
    	}
        */
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setTitle("Mouse Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public static void main(String[] args) throws IOException {
		launch(args);
	}
}
