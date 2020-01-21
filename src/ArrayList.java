// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
// This class is used to create ArrayList objects for the purpose of storing Player objects

public class ArrayList<T> {
	
	private Node<T>[] list; // Array used to implement the ArrayList
	
	int length; // tracks the amount of items in the arraylist
	
	public ArrayList()
	{
		int listLength = 20000; // size of the array
		
		list = new Node[listLength];
		
		length = 0;
	}
	
	// Adds a node to the end of the arraylist
	public void add(Node<T> n) {
		
		list[length] = n;
		
		length += 1;
		
	}
	
	// returns the node at the given index i
	public Node<T> get(int i) {
	
		return list[i];
	}
	
	// sets a value n to the current index i
	public void setValue(int i, Node<T> n) {
		
		list[i] = n;
	}

	// returns the length of the arraylist
	public int size() {
		return length;
	}
	
	// Returns true if the list is empty, false if it is not empty
	public boolean isEmpty() {
		if (length > 0)
			return false;
		else if (length == 0)
			return true;
					
		return true;
	}
}
