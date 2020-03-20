package threads;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TestThreadBasic {

    @Test
    public void testLaunch() {

        try {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread subclass running");
            }
        };
        t.start();
        t.join();

        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println("Runnable implementation running");
            }
        };
        t = new Thread(r);
        t.start();
        t.join();

        } catch (InterruptedException e) {
            fail( e.getMessage() );
        }
    }
}
