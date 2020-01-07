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
    private ArrayList<Bomb> bombList = new ArrayList();

    protected ArrayList<ImageView> playerViews;
    protected ArrayList<ImageView> bombViews;
    
    protected HBox hbox;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	playerList = new ArrayList();
    	bombList = new ArrayList();
    	
    	playerViews = new ArrayList();
    	bombViews = new ArrayList();
    	
    	
    	
    	//Player player1 = new Player(0, 0, 1);

    	 // TESTING
       // Pair pair = new Pair(1, player1);
        //int a = pair.type;
        
    	Image tempCrate = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Crate.png");
        ImageView tempView = new ImageView();
        tempView.setFitHeight(50);
        tempView.setFitWidth(50);
        tempView.setImage(tempCrate);
        
        Image player = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\PlayerRed.png");
        ImageView playerView = new ImageView();
        playerView.setFitHeight(50);
        playerView.setFitWidth(50);
        playerView.setImage(player);
        
        StackPane pane = new StackPane();
        
        hbox = new HBox();
        
        pane.getChildren().addAll(tempView, playerView);
        
        hbox.getChildren().addAll(pane);
        
        for (int i = 0; i < 11; i += 1)
        {
        	Image crate = new Image("file:\\G:\\My Drive\\ICS Grade 12\\BombFight\\Crate.png");
            ImageView crateView = new ImageView();
            crateView.setFitHeight(50);
            crateView.setFitWidth(50);
            crateView.setImage(crate);
        	hbox.getChildren().add(crateView);
        }
        
        VBox root = new VBox(); // root VBox displaying the main scene
        
        // maybe make a for loop to create new hbox rows to add some number of hboxes without repeat code.
        // of course, some rows are different, with spaces instead of crates, but just hard code that part too.
        root.getChildren().add(hbox);
        
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setTitle("Mouse Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public static void main(String[] args) throws IOException {
		launch(args);
	}
}
