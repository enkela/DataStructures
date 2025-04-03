package weiss.nonstandard;

// ArrayQueue class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void enqueue( x )      --> Insert x
// AnyType getFront( )    --> Return least recently inserted item
// AnyType dequeue( )     --> Return and remove the least recent item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// getFront or dequeue on empty queue


import DS.BookComparator;

/**
 * Array-based implementation of the queue.
 * @author Mark Allen Weiss
 */
public class ArrayQueue<AnyType> implements Queue<AnyType>
{
    /**
     * Construct the queue.
     */
    public ArrayQueue( )
    {
        theArray = (AnyType []) new Object[ DEFAULT_CAPACITY ];
        makeEmpty( );
    }

    public ArrayQueue(int capacity)
    {
        theArray = (AnyType []) new Object[capacity];
        makeEmpty( );
    }

    /**
     * Test if the queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
        front = 0;
        back = -1;
    }

    /**
     * Return and remove the least recently inserted item
     * from the queue.
     * @return the least recently inserted item in the queue.
     * @throws UnderflowException if the queue is empty.
     */
    public AnyType dequeue( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( "ArrayQueue dequeue" );
        currentSize--;

        AnyType returnValue = theArray[ front ];
        front = increment( front );
        return returnValue;
    }
    
    /**
     * Get the least recently inserted item in the queue.
     * Does not alter the queue.
     * @return the least recently inserted item in the queue.
     * @throws UnderflowException if the queue is empty.
     */
    public AnyType getFront( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( "ArrayQueue getFront" );
        return theArray[ front ];
    }

    /**
     * Insert a new item into the queue.
     * @param x the item to insert.
     */
    public void enqueue( AnyType x )
    {
        if( currentSize == theArray.length )
            doubleQueue( );
        back = increment( back );
        theArray[ back ] = x;
        currentSize++;
    }

    /**
     * Internal method to increment with wraparound.
     * @param x any index in theArray's range.
     * @return x+1, or 0 if x is at the end of theArray.
     */
    private int increment( int x )
    {
        if( ++x == theArray.length )
            x = 0;
        return x;
    }
    
    /**
     * Internal method to expand theArray.
     */
    private void doubleQueue( )
    {
        AnyType [ ] newArray;

        newArray = (AnyType []) new Object[ theArray.length * 2 ];

            // Copy elements that are logically in the queue
        for( int i = 0; i < currentSize; i++, front = increment( front ) )
            newArray[ i ] = theArray[ front ];

        theArray = newArray;
        front = 0;
        back = currentSize - 1;
    }


    //// Exercises

    public void showElements()
    {
        int temp = front;
        for( int i = 0; i < currentSize; i++, front = increment( front ) )
            System.out.println(theArray[front].toString());
        front = temp;
    }

    public void showInverse()
    {
        int temp = back;
        for( int i = currentSize; i > 0; i--, back = decrement(back) )
            System.out.println(theArray[back].toString());
        back = temp;
    }

        private int decrement( int x )
    {
        if( x == 0 )
            x = theArray.length -1;
        else
            x--;
        return x;
    }
    
    
    public ArrayQueue<AnyType> clone()
    {
        int temp = front;
        ArrayQueue<AnyType> clone = new ArrayQueue<AnyType>(theArray.length);

        for( int i = 0; i < currentSize; i++, front = increment(front) )
            clone.enqueue(theArray[front]);
        front = temp;
        return clone;
    }

    public AnyType findMinimum(BookComparator cmp )
    {
        int temp = front;
        AnyType minimum = theArray[front];
        //front = increment( front );
        //ComparatorAny<AnyType> cmp = new ComparatorAny<AnyType>();
        for( int i = 0; i < currentSize; i++, front = increment( front ) )
        {
            if(cmp.compare(theArray[front],minimum)<0)
                minimum = theArray[front];
        }
        front = temp;
        return minimum;
    }

    private AnyType [ ] theArray;
    private int         currentSize;
    private int         front;
    private int         back;

    private static final int DEFAULT_CAPACITY = 10;
}