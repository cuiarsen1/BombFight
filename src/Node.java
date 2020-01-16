public class Node<T> {

	private T data;
	
	private Node<T> next;
	private Node<T> previous;
	
	public Node(T n)
	{
		data = n;
	}
	
	public T getValue() {
		return data;
	}
	
	public void setValue(T n) {
		data = n;
	}

	public void setNext(Node<T> n) {
		next = n;
	}
	
	public void setPrev(Node<T> n) {
		previous = n;	
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public Node<T> getPrev() {
		return previous;
	}
	
	public String toString() {
		return data.toString();
	}
	
}