import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Map extends Main {
	
	protected Pair[][] map;
	
	public Map()
	{
		map = new Pair[12][12];
	}
	
	/*// Creates and initializes the game board
	public void createMap() {
		
		HBox tempBox;
		
		for (int i = 0; i < 12; i += 1)
		{
			tempBox = new HBox();
			
			for (int j = 0; j < 12; j += 1)
			{
				StackPane pane = new StackPane();
				
				Image tempCrate = new Image("file:Crate.png");
				ImageView tempCrateView = new ImageView(tempCrate);
				tempCrateView.setFitHeight(50);
				tempCrateView.setFitWidth(50);
				
				pane.getChildren().add(tempCrateView);
				
				tempBox.getChildren().add(pane);
			}
			
			root.getChildren().add(tempBox);
		}
	}*/

}
