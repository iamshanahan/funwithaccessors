package containers;

import org.junit.Test;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestLists {
    @Test
    public void testArrayList() {
        List<String> mylist = new ArrayList<>();
        mylist.add( "foo" );
        Iterator<String> iter = mylist.iterator();
        while( iter.hasNext() ) {
            System.out.println("iter.next() = " + iter.next());
        }
        for( String s : mylist ) {
            System.out.println("s = " + s );
        }
    }

    @Test
    public void testLinkedList() {
        LinkedList<String> mylist = new LinkedList<>();
        mylist.add( "foo" );
        mylist.add( "bar" );
        Iterator<String> iter = mylist.descendingIterator();
        while( iter.hasNext() ) {
            System.out.println("iter.next() = " + iter.next());
        }
        for( String s : mylist ) {
            System.out.println("s = " + s );
        }
    }
}
