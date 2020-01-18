public class Triple {
	
    protected int type;
    protected Entity object;
    protected boolean bombExist; // tracks whether a bomb exists in this grid. 

    public Triple(int type, Entity object, boolean bombExist) {
        this.type = type;
        this.object = object;
        this.bombExist = bombExist;
    }
}
