/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.util;

import DS.Book;
import DS.BookComparator;
import weiss.nonstandard.ListStack;

/**
 *
 * @author CS
 */
public class TestingLinkedList {

    public static void main(String[] args) {
        testLinkedList();
    }

    private static void testLinkedList() {
        PriorityQueue<Book> myBooksQueue = new PriorityQueue<Book>();
        LinkedList<Book> myBooks = new LinkedList<Book>();
        Book b1 = new Book("B1", 9000);
        Book b2 = new Book("B2", 1000);
        Book b3 = new Book("B3", 8000);
        Book b4 = new Book("B4", 4000);
        Book b5 = new Book("B5", 9000);
        Book b6 = new Book("B6", 1000);
        Book b7 = new Book("B7", 8000);
        Book b8 = new Book("B8", 4000);

        myBooks.addFirst(b4);
        myBooks.addFirst(b3);
        myBooks.addFirst(b2);
        myBooks.addFirst(b1);
        System.out.println("Show Elements of linked list");

//        System.out.println("Show Elements of linked list after substitute two elemnts of the list");
//        System.out.println("----------------------------------------------------");
//        myBooks.substitute(b1, b4, new BookComparator<Book>());
//        myBooks.showList();
//        System.out.println("----------------------------------------------------");
//        System.out.println("Show Elements of linked list in Inverse Order");
//        myBooks.showInverseList();
        myBooks.add(b5);
        myBooks.add(b6);
        myBooks.add(b7);
        myBooks.add(b8);
        myBooks.showList();
        System.out.println("Show Elements of linked list after removin from first to third,3 elemnts of the list");
        System.out.println("----------------------------------------------------");
        myBooks.remove(1, 3);
        myBooks.showList();
//        System.out.println("----------------------------------------------------");
//        System.out.println("Swapin book b6 with b7");
//        myBooks.swap(3,4);
//        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        System.out.println("ListAfterCopyAnd past .from 1 to 3 after 5");
        myBooks.copyAndPaste(1,3,4);
        myBooks.showList();
        System.out.println(" after cutand paste----------------------------------------------------");
         myBooks.cutAndPaste(4,6,1);
        myBooks.showList();
        System.out.println("----------------------------------------------------");
//        LinkedList<Book> myBooks1 = new LinkedList<Book>(myBooks);
//        System.out.println("Show Elements of linked list using sec method");
//        myBooks.showList2();
//        System.out.println("Show Elements of linked list in Inverse Order by using sec method");
//        myBooks.showInverseList();
//        myBooks.addFirst(b4);
        //      int a = myBooks.findOccurrences(b4, new BookComparator());
//        myBooks.remove(b4);
//        System.out.println("Show count of the occurrences: " + a);

//        System.out.println("Minimum: " + b.getTitle());
//        System.out.println("Show first Elements of linked list: " + myBooks.first().current.element);
    }

}
