package DS;
import weiss.util.Comparator;  /// for queues, stacks and other lists

public class BookComparator<Book> implements Comparator<Book>{
    public int compare(Book e, Book o) {
        //to be implemented
      return  (e.toString().compareTo(o.toString()));
    }

}
