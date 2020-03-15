package containers;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestPriorityQueue {

    public class MyComparator implements Comparator<String>
    {
        @Override
        public int compare(String x, String y)
        {
            return x.length() - y.length();
        }
    }

    @Test
    public void testPQ() {
        Queue<String> q = new PriorityQueue<>();

        Queue<String> queue =
                new PriorityQueue<String>( new MyComparator() );
        queue.add("Tyrion Lannister");
        queue.add("Daenerys Targaryen");
        queue.add("Arya Stark");
        queue.add("Tony Stark");
        queue.add("Arya Stark");
        queue.add("Petyr 'Littlefinger' Baelish");

        /*
         * What I am doing here is removing the highest
         * priority element from Queue and displaying it.
         * The priority I have set is based on the string
         * length. The logic for it is written in Comparator
         */
        while (queue.size() != 0)
        {
            System.out.println(queue.poll());

        }
    }
}
