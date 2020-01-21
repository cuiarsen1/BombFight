// Arsen Cui
// ICS4U1-01
// January 21, 2020
// Mr. Radulovic
// ICS4U1 Culminating Activity - Bomb Fight
/*This class is used to store store any type of object in the
ArrayList. I use it to represent Players and Bombs*/

public class Node<T> {

	private T data; // The Player or Bomb object
	
	public Node(T n)
	{
		data = n;
	}
	
	// Returns the object
	public T getValue() {
		return data;
	}
	
}