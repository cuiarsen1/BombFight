/*Class used to create queues. The queue will be used to store Bomb objects in a first in first 
out basis. This helps track how many bombs the player has placed at a time, and which bomb to 
detonate first*/

public class Queue {

    protected Bomb[] queue; // Array used to implement the Queue

    private int length; // tracks the amount of items in the queue

    public Queue()
    {
        int queueSize = 5; // size of the array
        
        queue = new Bomb[queueSize];

        length = 0;
    }

    // adds a bomb to the end of the queue
    public void enqueue(Bomb n) {

        queue[length] = n;

        length += 1;

    }

    // removes the first bomb from the queue
    public Bomb dequeue() {

        Bomb bomb = queue[0]; // bomb to be dequeued

        // Shifts all the entities in the queue back by one to get rid of the first bomb, removing it
        for (int i = 0; i < length; i += 1)
        {
            queue[i] = queue[i + 1];
        }

        length -= 1;

        return bomb;

    }

    // returns the first bomb of the queue
    public Bomb peek() {
        return queue[0];

    }

    // returns the size of the queue
    public int size() {
        return length;
    }

    // returns true if the queue is empty, false if it is not empty
    public boolean isEmpty() {

        if (length > 0)
            return false;
        else if (length == 0)
            return true;

        return true;
    }

}