import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Crate extends Entity {

	public Crate(int x, int y)
	{
		super(x, y);
	}

	@Override
	public StackPane createImage(String crateFile) {
		
		StackPane pane = new StackPane();
		Image tempImage = new Image(crateFile);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setFitHeight(50);
		tempImageView.setFitWidth(50);
		
		pane.getChildren().add(tempImageView);
		
		return pane;
	}
}
