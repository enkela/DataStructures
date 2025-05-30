package weiss.util;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class LinkedList<AnyType> extends AbstractCollection<AnyType> implements List<AnyType>, Queue<AnyType> {

    /**
     * Construct an empty LinkedList.
     */
    public LinkedList() {
        clear();
    }

    /**
     * Construct a LinkedList with same items as another Collection.
     */
    public LinkedList(Collection<? extends AnyType> other) {
        clear();
        for (AnyType val : other) {
            add(val);
        }
    }

    /**
     * Change the size of this collection to zero.
     */
    public void clear() {
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    /**
     * Tests if some item is in this collection.
     *
     * @param x any object.
     * @return true if this collection contains an item equal to x.
     */
    public boolean contains(Object x) {
        return findPos(x) != NOT_FOUND;
    }

    /**
     * Returns the position of first item matching x in this collection, or
     * NOT_FOUND if not found.
     *
     * @param x any object.
     * @return the position of first item matching x in this collection, or
     * NOT_FOUND if not found.
     */
    private Node<AnyType> findPos(Object x) {
        for (Node<AnyType> p = beginMarker.next; p != endMarker; p = p.next) {
            if (x == null) {
                if (p.data == null) {
                    return p;
                }
            } else if (x.equals(p.data)) {
                return p;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        addLast(x);
        return true;
    }

    /**
     * Adds an item to this collection, at specified position. Items at or after
     * that position are slid one position higher.
     *
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(),
     * inclusive.
     */
    public void add(int idx, AnyType x) {
        Node<AnyType> p = getNode(idx, 0, size());
        Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * Adds an item to this collection, at front. Other items are slid one
     * position higher.
     *
     * @param x any object.
     */
    public void addFirst(AnyType x) {
        add(0, x);
    }

    /**
     * Adds an item to this collection, at end.
     *
     * @param x any object.
     */
    public void addLast(AnyType x) {
        add(size(), x);
    }

    /**
     * Returns the front item in the list.
     *
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType element() {
        return getFirst();
    }

    /**
     * Returns the first item in the list.
     *
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(0).data;
    }

    /**
     * Returns the last item in the list.
     *
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(size() - 1).data;
    }

    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Removes the front item in the queue.
     *
     * @return the front item.
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType remove() {
        return removeFirst();
    }

    /**
     * Removes the first item in the list.
     *
     * @return the item was removed from the collection.
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(getNode(0));
    }

    /**
     * Removes the last item in the list.
     *
     * @return the item was removed from the collection.
     * @throws NoSuchElementException if the list is empty.
     */
    public AnyType removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(getNode(size() - 1));
    }

    /**
     * Removes an item from this collection.
     *
     * @param x any object.
     * @return true if this item was removed from the collection.
     */
    public boolean remove(Object x) {
        Node<AnyType> pos = findPos(x);

        if (pos == NOT_FOUND) {
            return false;
        } else {
            remove(pos);
            return true;
        }
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( )-1.
     *
     * @param idx index to search at.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size()-1,
     * inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper,
     * inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());
        }

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator(0);
    }

    /**
     * Obtains a ListIterator object used to traverse the collection
     * bidirectionally.
     *
     * @return an iterator positioned prior to the requested element.
     * @param idx the index to start the iterator. Use size() to do complete
     * reverse traversal. Use 0 to do complete forward traversal.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(),
     * inclusive.
     */
    public ListIterator<AnyType> listIterator(int idx) {
        return new LinkedListIterator(idx);
    }

    public int findOccurrences(AnyType x, Comparator<AnyType> cmp) {
        Node<AnyType> currentPosition = beginMarker.next;
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (cmp.compare(currentPosition.data, x) == 0) {
                count++;
            }
            currentPosition = currentPosition.next;
        }
        return count;
    }

    public boolean binarySearch(AnyType x, Comparator<AnyType> cmp) {
        int low = 0;
        int mid = 0;
        int high = theSize - 1;
        while (low < high) {
            mid = (low + high) / 2;
            if (cmp.compare(x, getNode(mid).data) > 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
            if (low == size() - 1 || cmp.compare(x, getNode(mid).data) != 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * This is the implementation of the LinkedListIterator. It maintains a
     * notion of a current position and of course the implicit reference to the
     * LinkedList.
     */
    private class LinkedListIterator implements ListIterator<AnyType> {

        private Node<AnyType> current;
        private Node<AnyType> lastVisited = null;
        private boolean lastMoveWasPrev = false;
        private int expectedModCount = modCount;

        public LinkedListIterator(int idx) {
            current = getNode(idx, 0, size());
        }

        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return current != endMarker;
        }

        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            AnyType nextItem = current.data;
            lastVisited = current;
            current = current.next;
            lastMoveWasPrev = false;
            return nextItem;
        }

        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastVisited == null) {
                throw new IllegalStateException();
            }

            LinkedList.this.remove(lastVisited);
            lastVisited = null;
            if (lastMoveWasPrev) {
                current = current.next;
            }
            expectedModCount++;
        }

        public boolean hasPrevious() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return current != beginMarker.next;
        }

        public AnyType previous() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            current = current.prev;
            lastVisited = current;
            lastMoveWasPrev = true;
            return current.data;
        }
    }

    public void moveAtTheFront(AnyType x, Comparator<AnyType> cmp) {
        for (int i = 0; i < this.theSize; i++) {
            if (cmp.compare(getNode(i).data, x) == 0) {
                remove(i);
                addFirst(x);
            }
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {

        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    private final Node<AnyType> NOT_FOUND = null;

    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    private int modCount = 0;

    public void showList() {
        for (int i = 0; i < this.theSize; i++) {
            System.out.println(getNode(i).data.toString());
        }
    }

    public void showList2() {
        Node<AnyType> currentPosition = beginMarker.next;
        for (int i = 0; i < this.theSize; i++) {
            AnyType el = currentPosition.data;
            System.out.println(el);
            currentPosition = currentPosition.next;
        }
    }

    public void showInverseList1() {

        for (int i = (this.theSize) - 1; i > -1; i--) {

            System.out.println(getNode(i).data.toString());

        }
    }

    public void showInverseList() {
        Node<AnyType> currentPosition = endMarker.prev;
        for (int i = 0; i < this.theSize; i++) {
            AnyType el = currentPosition.data;
            System.out.println(el);
            currentPosition = currentPosition.prev;
        }
    }

    public void removeDuplicates(Comparator<AnyType> cmp) {
        Node<AnyType> currentPosition = beginMarker.next;
        Node<AnyType> nextPosition;
        while (currentPosition != null) {
            nextPosition = currentPosition;
            if (nextPosition.next != null && nextPosition.next.data != null
                    && cmp.compare(nextPosition.next.data, currentPosition.data) == 0) {

                nextPosition.next = nextPosition.next.next;
                nextPosition.next.prev = nextPosition;
                theSize--;
            } else {
                nextPosition = nextPosition.next;
            }
            currentPosition = currentPosition.next;

        }
    }

//LAb 5 EXERCISES
//exercise 1
    public void substitute(AnyType x, AnyType y, Comparator<AnyType> cmp) {
        for (int i = 0; i < this.theSize; i++) {
            Node<AnyType> p = getNode(i);
            if (cmp.compare(p.data, x) == 0) {
                p.data = y;
            }
        }
    }

    public void remove(int idx, int idx2) {
        for (int i = idx; i <= idx2; i++) {
            remove(idx);
        }
    }

    public void swap(int index1, int index2) {
        AnyType d1 = getNode(index1).data;
        AnyType d2 = getNode(index2).data;
        this.set(index1, d2);
        this.set(index2, d1);

    }

    public void copyAndPaste(int idx, int idx2, int idx3) {
        if (idx3 > theSize) {
            idx3 = theSize;
        }
        if (idx3 > idx2) {
            for (int i = idx; i <= idx2; i++) {

                this.add(idx3, this.getNode(i).data);
                idx3++;
            }
        } else {
            for (int i = idx; i <= idx2; i = i + 2) {

                this.add(idx3, this.getNode(i).data);
                idx3++;
                idx2++;
            }
        }

    }

    public void cutAndPaste(int idx, int idx2, int idx3) {
        if (idx3 > theSize) {
            idx3 = theSize;
        }
        if (idx3 > idx2) {
            copyAndPaste(idx, idx2, idx3);
            remove(idx, idx2);
        } else {
            int count = idx2 - idx + 1;
            copyAndPaste(idx, idx2, idx3);
            remove(idx + count, idx2 + count);
        }

    }
//Exercise 6

    public void cutAndPasteIntoList(int idx1, int idx2, int idx3, LinkedList<AnyType> list2) {
        for (int i = idx1; i <= idx2; i = i++) {

            list2.add(idx3, getNode(i).data);
        }
        remove(idx1, idx2);
    }

    //Exercise 7
    public void cutAndPasteIntoListReverse(int idx1, int idx2, int idx3, LinkedList<AnyType> list2) {
        idx3++;
        for (int i = idx1; i <= idx2; i = i++) {
            list2.add(idx3, getNode(i).data);
        }
        remove(idx1, idx2);
    }
    //Exercise 8

    public void moveAtTheEnd(AnyType x, Comparator<AnyType> cmp) {
        int initialSize = this.theSize;
        int index = 0;
        for (int i = 0; i < initialSize - 1; i = index) {
            if (cmp.compare(getNode(i).data, x) == 0) {
                this.cutAndPaste(i, i, this.theSize - 1);
                initialSize--;
                index = i;
                continue;
            }
            index++;
        }
    }

    //Exercise 9
    public void moveLargerAtTheFront(AnyType x, Comparator<AnyType> cmp) {
        for (int i = 0; i < this.theSize; i++) {
            if (cmp.compare(getNode(i).data, x) > 0) {
                AnyType temp = getNode(i).data;
                remove(i);
                addFirst(temp);
            }
        }
    }
    //Exercise 10

    public void surround(AnyType x, AnyType y, AnyType z, Comparator<AnyType> cmp) {
        int index = 0;
        for (int i = 0; i < this.theSize; i = index) {
            if (cmp.compare(getNode(i).data, x) == 0) {
                add(i, y);
                add(i + 2, z);
                index = i + 3; /// jump one element
                continue;
            }
            index++;
        }
    }

    public void cutAndPasteIntoListQosja(int index1, int index2, int index3) {
        LinkedList<AnyType> temp = new LinkedList<>();
        for (int i = index1; i < index2; i++) {
            temp.add(getNode(i).data);
        }
//    Node<AnyType> newNode = new Node<>();  
//    insertAfter.next = newNode;  
//    insertBefore.prev = newNode;  
//    insertAfter = newNode;  
//    list2.theSize++;  
        // }  
    }

//    public void moveLargerAtTheFrontQosja(AnyType  list, Comparator<AnyType> cmp) {
//        Iterator<AnyType> itr = this.iterator();
//        LinkedList<AnyType> larger = new LinkedList<>();
//        while (itr.hasNext()) {
//            AnyType current = itr.next();
//            if (cmp.compare(current, list) > 0) {
//                larger.add(current);
//            }
//            itr.remove();
//        }
//        this.addLast( larger);
//    }
//}

public void swapQosja(int index1, int index2) {  
    AnyType data1 = getNode(index1).data;  
    Node<AnyType> node2 = getNode(index2);  
    this.set(index1, node2.data);  
  
}
 public void MoveLargerAtTheFrontAlushi(AnyType x, Comparator<AnyType> cmp) {
    Node<AnyType> current = beginMarker;
    while (current != null) {
        Node next = current.next;
        if (cmp.compare(current.data, x) > 0) {
            remove(current);
            addFirst(current.data);
        }
        current = next;
    }
}
 public void swapUkperaj(int index1, int index2) {
    if (index1 == index2) return;
    Node<AnyType> node1 = getNode(index1);
    Node<AnyType> node2 = getNode(index2);
    if (node1 == null || node2 == null) return;
    AnyType temp = node1.data;
    node1.data = node2.data;
    node2.data = temp;
}
 public void moveLargerAtTheFrontUKPERAJ(AnyType x, Comparator<AnyType> cmp) {
    Node<AnyType> current = beginMarker;
    while (current != null) {
        Node next = current.next;
        if (cmp.compare(current.data, x) > 0) {
            if (current.prev != null) current.prev.next = current.next;
            if (current.next != null) current.next.prev = current.prev;
            if (current == endMarker) endMarker = current.prev;

            current.next = beginMarker;
            current.prev = null;
            if (beginMarker != null) beginMarker.prev = current;
            beginMarker = current;
        }
        current = next;
    }
}
}
