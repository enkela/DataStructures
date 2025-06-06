package weiss.nonstandard;

// LinkedList class
import DS.IntComparator;
import DS.Book;
import DS.BookComparator;
import weiss.util.Comparator;

//
// CONSTRUCTION: with no initializer
// Access is via LinkedListIterator class
//
// ******************PUBLIC OPERATIONS*********************
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// LinkedListIterator zeroth( )
//                        --> Return position to prior to first
// LinkedListIterator first( )
//                        --> Return first position
// void insert( x, p )    --> Insert x after current iterator position p
// void remove( x )       --> Remove x
// LinkedListIterator find( x )
//                        --> Return position that views x
// LinkedListIterator findPrevious( x )
//                        --> Return position prior to x
// ******************ERRORS********************************
// No special errors
/**
 * Linked list implementation of the list using a header node. Access to the
 * list is via LinkedListIterator.
 *
 * @author Mark Allen Weiss
 * @see LinkedListIterator
 */
public class LinkedList<AnyType> {

    /**
     * Construct the list
     */
    public LinkedList() {
        header = new ListNode<AnyType>(null);
    }

    /**
     * Test if the list is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return header.next == null;
    }

    /**
     * Make the list logically empty.
     */
    public void makeEmpty() {
        header.next = null;
    }

    /**
     * Return an iterator representing the header node.
     */
    public LinkedListIterator<AnyType> zeroth() {
        return new LinkedListIterator<AnyType>(header);
    }

    /**
     * Return an iterator representing the first node in the list. This
     * operation is valid for empty lists.
     */
    public LinkedListIterator<AnyType> first() {
        return new LinkedListIterator<AnyType>(header.next);
    }

    /**
     * Insert after p.
     *
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    public void insert(AnyType x, LinkedListIterator<AnyType> p) {
        if (p != null && p.current != null) {
            p.current.next = new ListNode<AnyType>(x, p.current.next);
        }
    }

    /**
     * Return iterator corresponding to the first node containing an item.
     *
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public LinkedListIterator<AnyType> find(AnyType x) {
        ListNode<AnyType> itr = header.next;

        while (itr != null && !itr.element.equals(x)) {
            itr = itr.next;
        }

        return new LinkedListIterator<AnyType>(itr);
    }

    /**
     * Return iterator corresponding to the first node containing an item.
     *
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public LinkedListIterator<AnyType> findLast(AnyType x) {
        ListNode<AnyType> itr = header.next;
        ListNode<AnyType> lastItr = new ListNode<AnyType>(x);
        //int counter = 0;
        //int pos = 0;
        while (itr != null) {
            if (itr.element.equals(x)) {
                lastItr = itr;
                //  pos = counter;
            }
            //counter++;
            itr = itr.next;
        }

        return new LinkedListIterator<AnyType>(itr);
    }

    /**
     * Return iterator prior to the first node containing an item.
     *
     * @param x the item to search for.
     * @return appropriate iterator if the item is found. Otherwise, the
     * iterator corresponding to the last element in the list is returned.
     */
    public LinkedListIterator<AnyType> findPrevious(AnyType x) {
        ListNode<AnyType> itr = header;

        while (itr.next != null && !itr.next.element.equals(x)) {
            itr = itr.next;
        }

        return new LinkedListIterator<AnyType>(itr);
    }

    /**
     * Remove the first occurrence of an item.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        LinkedListIterator<AnyType> p = findPrevious(x);

        if (p.current.next != null) {
            p.current.next = p.current.next.next;  // Bypass deleted node
        }
    }

    // Simple print method
    public static <AnyType> void printList(LinkedList<AnyType> theList) {
        if (theList.isEmpty()) {
            System.out.print("Empty list");
        } else {
            LinkedListIterator<AnyType> itr = theList.first();
            for (; itr.isValid(); itr.advance()) {
                System.out.print(itr.retrieve() + " ");
            }
        }

        System.out.println();
    }

    private ListNode<AnyType> header;

    // In this routine, LinkedList and LinkedListIterator are the
    // classes written in Section 17.2.
    public static <AnyType> int listSize(LinkedList<AnyType> theList) {
        LinkedListIterator<AnyType> itr;
        int size = 0;

        for (itr = theList.first(); itr.isValid(); itr.advance()) {
            size++;
        }

        return size;
    }

    public static void main(String[] args) {
        LinkedList<Integer> theList = new LinkedList<Integer>();
        LinkedList<Integer> theListClone = new LinkedList<Integer>();
        LinkedListIterator<Integer> theItr;
        int i;

        theItr = theList.zeroth();
        printList(theList);

        for (i = 0; i < 20; i++) {
            theList.insert(i, theItr);
//            printList(theList);
            theItr.advance();
        }
        theList.insert(-2, theItr);
//        for (i = 0; i < 1; i++) {
//            theList.insert(i, theItr);
////            printList(theList);
//            theItr.advance();
//        }
        System.out.println("Size was: " + listSize(theList));

//        for (i = 0; i < 10; i += 2) {
//            theList.remove(i);
//        }
//  delete element 2
        System.out.println("Printing the list to remove 2");
        printList(theList);
        theList.removeAll(2);

//        for (i = 0; i < 10; i++) {
//            if ((i % 2 == 0) == (theList.find(i).isValid())) {
//                System.out.println("Find fails!");
//            }
//        }
//
//        System.out.println("Finished deletions");
        printList(theList);
        final LinkedListIterator<Integer> last = theList.findLast(3);
        if (last.isValid()) {
            System.out.println("Findlast: " + last.current.element);
        }

        theListClone = theList.clone();
        System.out.println("Printing the Cloned list");
        printList(theListClone);
        System.out.println("Printing the minimum list");
        LinkedList<Book> booksMin = new LinkedList<Book>();
        LinkedListIterator<Book> booksItr = booksMin.zeroth();
        booksMin.insert(new Book("a", 123), booksItr);
        booksItr.advance();
        booksMin.insert(new Book("b", 456), booksItr);
        booksItr.advance();
        booksMin.insert(new Book("c", 789), booksItr);

     
      Book myFoundBook = booksMin.findMinimum(new BookComparator<Book>());
        System.out.println(" myMinimumBook: " + myFoundBook);
    
    }

    //Clone method
    public LinkedList<AnyType> clone() {
        LinkedList<AnyType> clone = new LinkedList<AnyType>();
        LinkedListIterator<AnyType> itr = this.first();
        LinkedListIterator<AnyType> cloneitr = clone.zeroth();
        if (!this.isEmpty()) {
            while (itr.current.next != null) {
                clone.insert(itr.current.element, cloneitr);
                itr.advance();
                cloneitr.advance();
            }
            clone.insert(itr.current.element, cloneitr);
        }
        return clone;
    }

    public void removeAll(AnyType x) {
        LinkedListIterator<AnyType> p = first();
        LinkedListIterator<AnyType> previous = zeroth();
        while (p.current.next != null) {
            if (p.current.element.equals(x)) {
                previous.current.next = previous.current.next.next;  // Bypass deleted node
            }
            //System.out.println(previous.current.element);
            p.advance();
            previous.advance();
        }

    }

    public AnyType findMinimum(Comparator<AnyType> cmp) {
        LinkedListIterator<AnyType> itr = first();
        AnyType minimum = itr.current.element;
        while (itr.current.next != null) {
            //System.out.println(front.element);
            if (cmp.compare(itr.current.next.element, minimum) < 0) {
                minimum = itr.current.next.element;
            }
            itr.advance();
        }
        return minimum;
    }

    public LinkedListIterator<AnyType> findMinimumItr(Comparator<AnyType> cmp) {
        LinkedListIterator<AnyType> itr = zeroth();
        LinkedListIterator<AnyType> minimum = first();
        //int counter = 0;
        //int pos = 0;
        while (itr.current.next != null) {
            if (cmp.compare(itr.current.next.element, minimum.current.element) < 0) {
                minimum = itr;
                //  pos = counter;
            }
            //counter++;
            itr.advance();
        }

        return minimum;

    }

}
