package containers;

import org.junit.Test;

import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLinkedHashMap {

    public class Mappable{
        String thing1;
        String thing2;

        @Override
        public int hashCode() {

            return thing1.hashCode() + thing2.hashCode() + Integer.MAX_VALUE;
        }
    }
    @Test
    public void testLHM() {
        HashSet<Mappable> mappables = new HashSet<>();
        Mappable m = new Mappable();
        m.thing1 = "before1";
        m.thing2 = "before2";
        System.out.println("m.hashCode() = " + m.hashCode() );
        mappables.add(m);
        assertFalse( mappables.add(m));
        m.thing1 = "after1";
        m.thing2 = "after2";
        System.out.println("m.hashCode() = " + m.hashCode() );
        assertFalse( mappables.contains( m ) );
        assertTrue( mappables.add(m));
        System.out.println("mappables.iterator().next().thing1 = " + mappables.iterator().next().thing1 );
        System.out.println("mappables.iterator().next().thing2 = " + mappables.iterator().next().thing2 );

        // TreeSet of String Type
        TreeSet<String> tset = new TreeSet<String>();

        // Adding elements to TreeSet<String>
        tset.add("aBC");
        tset.add("String");
        tset.add("Test");
        tset.add("Pen");
        tset.add("Ink");
        tset.add("Jack");

        //Displaying TreeSet
        System.out.println(tset);

        System.out.println(".floor(\"Jack\") = " + tset.floor("Jacj"));

        // TreeSet of Integer Type
        TreeSet<Integer> tset2 = new TreeSet<Integer>();

        // Adding elements to TreeSet<Integer>
        tset2.add(88);
        tset2.add(7);
        tset2.add(101);
        tset2.add(0);
        tset2.add(3);
        tset2.add(222);
        System.out.println(tset2);
    }
}
