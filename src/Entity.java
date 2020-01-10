import javafx.scene.layout.StackPane;

public abstract class Entity extends Main {

    protected int x;
    protected int y;

    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract StackPane createImage(String imageFile);




}
