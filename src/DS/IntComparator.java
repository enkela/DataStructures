/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DS;

import weiss.util.Comparator;

/**
 *
 * @author enkela
 */
public class IntComparator<Integer> implements Comparator<Integer> {

     public int compare(Integer x, Integer y) {
        //to be implemented
      //  return java.lang.Integer.compare((int) e, (int) 0);
       return ((int)x < (int)y) ? -1 : ((x == y) ? 0 : 1);
    }

}
