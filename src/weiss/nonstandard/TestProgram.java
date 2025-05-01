/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.nonstandard;

import DS.Book;
import DS.BookComparator;

/**
 *
 * @author CS
 */
public class TestProgram {

    public static void main(String[] args) {

        /// Stack operations
        //System.out.println("Stack Operations");
        //System.out.println("Array Implementation");
        //testStackArray();
        //System.out.println("LinkedList Implementation");
        testStackList();
//testQueueArray();
        testQueueList();
        //testLinkedList();

        //testBinarySearchTree();
    }

    public static void testStackArray() {
        ArrayStack<Book> myBooks = new ArrayStack<Book>();

        Book b1 = new Book("Karamazov Brothers", 1000);
        Book b2 = new Book("The Idiot", 9000);
        Book b3 = new Book("Crime and Punishment", 8000);
        Book b4 = new Book("Notes from Undergroundt", 7000);

        myBooks.push(b1);
        myBooks.push(b2);
        myBooks.push(b3);
        myBooks.push(b4);

       

//        System.out.println("Showinverse");
//        myBooks.showInverse();
//
//        System.out.println("Clone");
//        ArrayStack<Book> cloneBooks = myBooks.Clone();
//        cloneBooks.showElements();
//
    }

    public static void testStackList() {
        ListStack<Book> myBooks = new ListStack<Book>();

        Book b1 = new Book("A", 1000);
        Book b2 = new Book("B", 9000);
        Book b3 = new Book("C", 8000);

        myBooks.push(b1);
        myBooks.push(b2);
        myBooks.push(b3);

        System.out.println("ShowElements");
        myBooks.showElements();
        //myBooks.showElements();
     //  ListStack<Book> myBooks1=decreasePriorityQosja(b2);
        System.out.println("Show el after decrease");
       myBooks.showElements();
//
//        System.out.println("Clone");
//        ListStack<Book> cloneBooks = myBooks.Clone();
//        cloneBooks.showElements();
//
//        System.out.println("Swap");
//        myBooks.swap();
//        myBooks.showElements();
    }

    public static void testQueueArray() {
        ArrayQueue<Book> myQueue = new ArrayQueue<Book>(10);
        Book b1 = new Book("A", 1000);
        Book b2 = new Book("B", 9000);
        Book b3 = new Book("C", 8000);

        myQueue.enqueue(b1);
        myQueue.enqueue(b2);
        myQueue.enqueue(b3);

        System.out.println("ShowElements");
        myQueue.showElements();
        System.out.println("ShowElements in Inverse....");
        myQueue.showInverse();
        System.out.println("ShowElements of the clone queue in two ways..");
        ArrayQueue<Book> myClone = myQueue.clone();
        myQueue.clone().showElements();
        myClone.showElements();
        BookComparator myBookComparator = new BookComparator();
        Book e = myQueue.findMinimum(myBookComparator);

    }

    private static void testQueueList() {
        ListQueue<Book> myBooks = new ListQueue<Book>();

        Book b1 = new Book("A", 1000);
        Book b2 = new Book("B", 9000);
        Book b3 = new Book("C", 8000);

        myBooks.enqueue(b1);
        myBooks.enqueue(b2);
        myBooks.enqueue(b3);

        System.out.println("ShowElements");
        myBooks.showElements();
        //myBooks.showElements();
//
        System.out.println("Showafter increase");
        //myBooks.listInverse();
        myBooks.increasePriority(b2);
        System.out.println("Showinverse");
        myBooks.showElements();
    }

    private static void testLinkedList() {
        LinkedList<Book> myBooks = new LinkedList<Book>();
        LinkedListIterator myBookitr= myBooks.first();
        Book b1 = new Book("B1", 9000);
        Book b2 = new Book("B2", 1000);
        Book b3 = new Book("B3", 8000);
        Book b4 = new Book("B4", 4000);
        myBooks.insert(b4,myBookitr);
        myBookitr.advance();
        myBooks.insert(b3,myBookitr);
        myBookitr.advance();
        myBooks.insert(b2,myBookitr);
        myBookitr.advance();
        myBooks.insert(b1,myBookitr);
        
        System.out.println("Show Elements of linked list");
      

 

    }

}
