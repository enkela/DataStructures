package DS;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User 
 * *
 */
public class Book {

   private String title;
   private int price;

    public Book(String tit, int pric) {

        this.title = tit;
        this.price = pric;
    }



    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void printbook() {
        System.out.println(title);
    }

        @Override
    public String toString(){
        return "Title: " + title + " " + "Price: " + price;
    }

}
