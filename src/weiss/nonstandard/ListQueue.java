package weiss.nonstandard;

// ListQueue class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void enqueue( x )      --> Insert x
// AnyType getFront( )    --> Return least recently inserted item
// AnyType dequeue( )     --> Return and remove least recent item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// getFront or dequeue on empty queue
import weiss.util.Comparator;

/**
 * List-based implementation of the queue.
 *
 * @author Mark Allen Weiss
 */
public class ListQueue<AnyType> implements Queue<AnyType> {

    /**
     * Construct the queue.
     */
    public ListQueue() {
        front = back = null;
    }

    /**
     * Test if the queue is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Insert a new item into the queue.
     *
     * @param x the item to insert.
     */
    public void enqueue(AnyType x) {
        if (isEmpty()) // Make queue of one element
        {
            back = front = new ListNode<AnyType>(x);
        } else // Regular case
        {
            back = back.next = new ListNode<AnyType>(x);
        }
    }

    /**
     * Return and remove the least recently inserted item from the queue.
     *
     * @return the least recently inserted item in the queue.
     * @throws UnderflowException if the queue is empty.
     */
    public AnyType dequeue() {
        if (isEmpty()) {
            throw new UnderflowException("ListQueue dequeue");
        }

        AnyType returnValue = front.element;
        front = front.next;
        return returnValue;
    }

    /**
     * Get the least recently inserted item in the queue. Does not alter the
     * queue.
     *
     * @return the least recently inserted item in the queue.
     * @throws UnderflowException if the queue is empty.
     */
    public AnyType getFront() {
        if (isEmpty()) {
            throw new UnderflowException("ListQueue getFront");
        }
        return front.element;
    }

    /**
     * Make the queue logically empty.
     */
    public void makeEmpty() {
        front = null;
        back = null;
    }

    private ListNode<AnyType> front;
    private ListNode<AnyType> back;

  
   public void showElements() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        ListNode<AnyType> frontQueue = front;
        while (frontQueue != null) {

            System.out.println("element:" + frontQueue.element);
            frontQueue = frontQueue.next;
        }

    }
   public void listInverse() {

        ListNode<AnyType> previousPosition;
        ListNode<AnyType> currentPosition;
        ListNode<AnyType> nextPosition;

        currentPosition = front;
        nextPosition = currentPosition.next;
        previousPosition = null;

        while (nextPosition != null) {

            currentPosition.next = previousPosition;
            previousPosition = currentPosition;
            currentPosition = nextPosition;
            nextPosition = nextPosition.next;
        }

        currentPosition.next = previousPosition;
        front = currentPosition;

    }


}
