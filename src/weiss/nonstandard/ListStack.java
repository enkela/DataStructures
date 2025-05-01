package weiss.nonstandard;

// ListStack class
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
 * List-based implementation of the stack.
 *
 * @author Mark Allen Weiss
 */
public class ListStack<AnyType> implements Stack<AnyType> {
    /**
     * Construct the stack.
     */
    public ListStack() {
        topOfStack = null;
    }

    /**
     * Test if the stack is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return topOfStack == null;
    }

    /**
     * Make the stack logically empty.
     */
    public void makeEmpty() {
        topOfStack = null;
    }

    /**
     * Insert a new item into the stack.
     *
     * @param x the item to insert.
     */
    public void push(AnyType x) {
        topOfStack = new ListNode<AnyType>(x, topOfStack);
    }

    /**
     * Remove the most recently inserted item from the stack.
     *
     * @throws UnderflowException if the stack is empty.
     */
    public void pop() {
        if (isEmpty())
            throw new UnderflowException("ListStack pop");
        topOfStack = topOfStack.next;
    }

    /**
     * Get the most recently inserted item in the stack.
     * Does not alter the stack.
     *
     * @return the most recently inserted item in the stack.
     * @throws UnderflowException if the stack is empty.
     */
    public AnyType top() {
        if (isEmpty())
            throw new UnderflowException("ListStack top");
        return topOfStack.element;
    }

    /**
     * Return and remove the most recently inserted item
     * from the stack.
     *
     * @return the most recently inserted item in the stack.
     * @throws UnderflowException if the stack is empty.
     */
    public AnyType topAndPop() {
        if (isEmpty())
            throw new UnderflowException("ListStack topAndPop");

        AnyType topItem = topOfStack.element;
        topOfStack = topOfStack.next;
        return topItem;
    }

    private ListNode<AnyType> topOfStack;


    public void showElements() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        ListNode<AnyType> topOfStacktemp = topOfStack;
        while (topOfStacktemp != null) {

            System.out.println("element:" + topOfStacktemp.element);
            topOfStacktemp = topOfStacktemp.next;
        }

    }

    public void showInverse() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        ListNode<AnyType> topOfStacktemp = topOfStack;
        ListStack<AnyType> inverseStack = new ListStack<>();
        while (topOfStacktemp != null) {

            //System.out.println("element:" +topOfStacktemp.element);
            inverseStack.push(topOfStacktemp.element);
            topOfStacktemp = topOfStacktemp.next;
        }
        inverseStack.showElements();
    }
      public void decreasePriorityQosja(AnyType el) {  
    if (top().equals(el)) return;  
    ListStack<AnyType> tempStack = new ListStack<>();  
    boolean found = false;  
    while (!isEmpty()) {  
      AnyType topElement = top(); pop();  
      if (topElement.equals(el)) {  
        found = true; break;  
      } else {  
        tempStack.push(topElement);  
      }  
    }  
    if (found) push(el);  
    while (!tempStack.isEmpty()) {  
      push(tempStack.top());  
      tempStack.pop();  
    
  }  
}
      public void decreasePriorityAlushi(AnyType el) {
    ListStack<AnyType> tempStack = new ListStack<AnyType>();
    while (!this.isEmpty()) {
        AnyType top = this.top();
        if (!top.equals(el)) {
            tempStack.push(top);
        }
    }

    this.push(el);
    while (!tempStack.isEmpty()) {
        this.push(tempStack.topAndPop());
    }
}
}