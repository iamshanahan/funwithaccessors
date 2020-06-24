package threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSynchronized {

    public static final int enoughThreadsToEnsureInterference = 6145;
    public static final int enoughTimeForThreadsToFinish = 1500;
    class Counter {
        private int c = 0;
        public void increment() {
            c++;
        }
        public void decrement() {
            c--;
        }
        public int value() {
            return c;
        }
    }

    class SynchronizedCounter {
        private int c = 0;
        public synchronized void increment() {
            c++;
        }
        public synchronized void decrement() {
            c--;
        }
        public synchronized int value() {
            return c;
        }
    }

    @Test
    public void testInterference() {
        Thread  t[] = new Thread[enoughThreadsToEnsureInterference];
        Counter c = new Counter();
        for( int i = 0; i<enoughThreadsToEnsureInterference; i++ ) {
            t[i] = new Thread() {
                @Override
                public void run() {
                    c.increment();
                }
            };
        }
        for( int i = 0; i<enoughThreadsToEnsureInterference; i++ ) {
            t[i].start();
        }
        try {
            Thread.sleep(enoughTimeForThreadsToFinish);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertFalse( c.value() == enoughThreadsToEnsureInterference );
        System.out.println("c.value() = " + c.value());
    }
    @Test
    public void testSynchronizedPreventsInterference() {
        Thread  t[] = new Thread[enoughThreadsToEnsureInterference];
        SynchronizedCounter c = new SynchronizedCounter();
        for( int i = 0; i<enoughThreadsToEnsureInterference; i++ ) {
            t[i] = new Thread() {
                @Override
                public void run() {
                    c.increment();
                }
            };
        }
        for( int i = 0; i<enoughThreadsToEnsureInterference; i++ ) {
            t[i].start();
        }
        try {
            Thread.sleep(enoughTimeForThreadsToFinish);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertTrue( c.value() == enoughThreadsToEnsureInterference );
        System.out.println("c.value() = " + c.value());
    }

    Integer[] events = new Integer[4];
    int eventIndex=0;
    synchronized void recordEvents( int startEvent, int sleepTime, int endEvent ) {
        events[eventIndex++] = startEvent;
        try {
            Thread.sleep( sleepTime );
        } catch (InterruptedException e) {
            fail( e.getMessage() );
        }
        events[eventIndex++] = endEvent;
    }
    @Test
    public void testSynchronizedForcesOrder() {
        Thread slowThread = new Thread() {
            public void run() {
                recordEvents( 1, 1000, 2 );
            }
        };
        Thread fastThread = new Thread() {
            public void run() {
                recordEvents( 3, 0, 4 );
            }
        };
        slowThread.start();
        fastThread.start();
        try {
            fastThread.join();
            slowThread.join();
        } catch (InterruptedException e) {
            fail( e.getMessage() );
        }
        assertArrayEquals( new Integer[]{1,2,3,4},events);
    }

    //semaphores?

}
