import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<Player> playerList = new ArrayList();
    private ArrayList<Bomb> bombList = new ArrayList();

    protected ArrayList<ImageView> playerViews = new ArrayList();
    private int a = 1;
    protected ArrayList<ImageView> bombViews = new ArrayList();

    @Override
    public void start(Stage stage) throws Exception {

        // TESTING
        Player player1 = new Player(0,0);

        Pair pair = new Pair(1, player1);
        int a = pair.type;
    }
}
