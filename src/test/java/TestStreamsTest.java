import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestStreamsTest {

    public class MyException extends RuntimeException {
    }

    interface Foo {
        int happy( int a );
    }

    interface Bar {
        int happy( int a );
    }

    public class Dog implements Foo,Bar {
        public int happy( int a ) {
            //throw new MyException();
            return a+1;
        }
    }

    @Test
    public void testStreams() {
        Set<String> setofstrings = new HashSet<>();
        setofstrings.add("foo");
        setofstrings.add("bar");
        setofstrings.add("");
        long countnum = setofstrings.stream().filter( String::isEmpty ).count();
        System.out.println("countnum = " + countnum);
        Dog d =  new Dog();
        assertEquals( 3, d.happy(2 ));
        LinkedList<String> list = new LinkedList<>();
        Deque<String> deque = list;
        list.pollLast();


    }

}