package weiss.nonstandard;

// ArrayStack class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void push( x )         --> Insert x
// void pop( )            --> Remove most recently inserted item
// AnyType top( )         --> Return most recently inserted item
// AnyType topAndPop( )   --> Return and remove most recent item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// top, pop, or topAndPop on empty stack

/**
 * Array-based implementation of the stack.
 * @author Mark Allen Weiss
 */
public class ArrayStack<AnyType> implements Stack<AnyType>
{
    /**
     * Construct the stack.
     */
    public ArrayStack( )
    {
        theArray = (AnyType []) new Object[ DEFAULT_CAPACITY ];
        topOfStack = -1;
    }

    /**
     * Test if the stack is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return topOfStack == -1;
    }

    /**
     * Make the stack logically empty.
     */
    public void makeEmpty( )
    {
        topOfStack = -1;
    }

    /**
     * Get the most recently inserted item in the stack.
     * Does not alter the stack.
     * @return the most recently inserted item in the stack.
     * @throws UnderflowException if the stack is empty.
     */
    public AnyType top( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( "ArrayStack top" );
        return theArray[ topOfStack ];
    }

    /**
     * Remove the most recently inserted item from the stack.
     * @throws UnderflowException if the stack is empty.
     */
    public void pop( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( "ArrayStack pop" );
        topOfStack--;
    }

    /**
     * Return and remove the most recently inserted item
     * from the stack.
     * @return the most recently inserted item in the stack.
     * @throws UnderflowException if the stack is empty.
     */
    public AnyType topAndPop( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( "ArrayStack topAndPop" );
        return theArray[ topOfStack-- ];
    }

    /**
     * Insert a new item into the stack.
     * @param x the item to insert.
     */
    public void push( AnyType x )
    {
        if( topOfStack + 1 == theArray.length )
            doubleArray( );
        theArray[ ++topOfStack ] = x;
    }

    /**
     * Internal method to extend theArray.
     */
    private void doubleArray( )
    {
        AnyType [ ] newArray;

        newArray = (AnyType []) new Object[ theArray.length * 2 ];
        for( int i = 0; i < theArray.length; i++ )
            newArray[ i ] = theArray[ i ];
        theArray = newArray;
    }
//Exercises Lab1

    //ShowElements: shows all the elements in the stack

    public void showElements() {
        for (int i = 0; i < topOfStack + 1; i++) {
            System.out.println((AnyType)theArray[i].toString());
        }
    }
    //ShowInverse: show the elements in inverse order.
    public void showInverse() {
        for (int i = topOfStack; i >= 0; i--) {
            System.out.println((AnyType)theArray[i].toString());
        }
    }
    /* New constructor which specifies size of stack (for the
        array implementation) as parameter.
*/
    public ArrayStack(int capacity){
        theArray = (AnyType[]) new Object[capacity];
        topOfStack = -1;
    }


    public ArrayStack<AnyType> Clone() {
        AnyType[] cloneArray = (AnyType[]) new Object[theArray.length];
        for (int i = 0; i < topOfStack + 1; i++){
            cloneArray[i] = theArray[i];
        }
        ArrayStack<AnyType> clone = new ArrayStack<AnyType>();
        clone.theArray = cloneArray;
        clone.topOfStack = topOfStack;
        return clone;
    }



    public void swap(){
        AnyType temp;
        temp = theArray[topOfStack-1];
        theArray[topOfStack-1] = theArray[topOfStack];
        theArray[topOfStack] = temp;
    }

    private AnyType [ ] theArray;
    private int         topOfStack;

    private static final int DEFAULT_CAPACITY = 10;



}