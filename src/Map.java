import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Map {
	
	protected Pair[][] mapArray;
	
	public Map()
	{
		mapArray = new Pair[12][12];
	}
	
	/*
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
			}
			
			root.getChildren().add(tempBox);
		}
		
		// Initializes rows 6 to 7
		
		for (int i = 0; i < 2; i += 1)
		{
			HBox tempBox = new HBox();
			
			for (int j = 0; j < 2; j += 1)
			{
				StackPane pane = createImage("file:Space.png");
				tempBox.getChildren().add(pane);
			}
			
			for (int j = 0; j < 8; j += 1)
			{
				StackPane pane = createImage("file:Crate.png");
				tempBox.getChildren().add(pane);
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
	}*/

}
