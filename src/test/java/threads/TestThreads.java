package threads;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestThreads {

    public static void safeSleep( int millis ) {
        try { Thread.sleep( millis ); }
        catch( InterruptedException e ) { fail( e.getMessage() ); };
    }

    static class SleepThread extends Thread {
        int millis;
        public SleepThread( int millis ){
            this.millis = millis;
        }
        public void run() {
            safeSleep( millis );
            System.out.println( "Sleep thread: Done sleeping" );
        }
    }

    static class WaitThread extends Thread {
        enum State {
            init,
            joined,
            done
        }
        public State state;
        public Thread other;
        public WaitThread( Thread other ) {
            this.other = other;
            state = State.init;
        }

        public void run() {
            try {
                synchronized( this ) {
                    state = State.joined;
                    other.join();
                }
            } catch (InterruptedException e) {
                fail( e.getMessage() );
            }
            state = State.done;
        }
    }
    static class StateDemoThread extends Thread{
        public int threadStateToggle = 0;

        private void runForeverShowingProgress() {
            int dotCounter = 0;
            while( threadStateToggle == 0 ) {
                if (++dotCounter == 1024) {
                    //System.out.println('.');
                    dotCounter = 0;
                } else {
                    System.out.print('.');
                }
            }
            if( dotCounter != 0 ) System.out.println('.');
        }

        public StateDemoThread(){
            System.out.println( "Thread: I now exist");
        }
        public void run(){
            System.out.println("Thread: I am running.");
            // toggle := 1
            runForeverShowingProgress();
            System.out.println("Thread: I am changing state.");
            while( threadStateToggle == 1 ) {
                System.out.println("Thread: I am going to sleep.");
                safeSleep( 1000 );
            }
//            while( threadStateToggle == 2 ) {
//
//            }

            System.out.println("Exiting.");
        }
    }


    @Test
    public void testThread() throws InterruptedException {
        System.out.println( "Test: creating thread" );

        StateDemoThread obj=new StateDemoThread();
        safeSleep( 100 );
        assertTrue( obj.getState() == Thread.State.NEW );
        assertFalse( obj.isAlive() );

        System.out.println( "Test: starting thread" );
        obj.start();;
        safeSleep( 100 );
        assertTrue( obj.getState() == Thread.State.RUNNABLE );
        assertTrue( obj.isAlive() );

        System.out.println( "Test: telling thread to sleep" );
        obj.threadStateToggle = 1;
        safeSleep( 100 );
        System.out.println("Test: obj.getState() = " + obj.getState());
        assertTrue( obj.getState() == Thread.State.TIMED_WAITING );
        assertTrue( obj.isAlive() );

        System.out.println( "Test: waking thread up" );
        obj.threadStateToggle = 2;
        safeSleep( 1000 );
        System.out.println("Test: obj.getState() = " + obj.getState());
        assertTrue( obj.getState() == Thread.State.TERMINATED );
        assertFalse( obj.isAlive() );
        /*
        NEW – A thread that has not yet started is in this state.
RUNNABLE – A thread executing in the Java virtual machine is in this state.
BLOCKED – A thread that is blocked waiting for a monitor lock is in this state.
WAITING – A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
TIMED_WAITING – A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
TERMINATED – A thread that has exited is in this state.
A thread can be in only one state at a given point in time.
         */

    }

    @Test
    public void testJoin() {
        Thread thread = new SleepThread(1000);
        assertTrue( thread.getState().equals( Thread.State.NEW ));
        safeJoin( thread );
        System.out.println("Test: back from join.");

        thread.start();
        safeSleep( 100 );
        assertTrue( thread.getState().equals( Thread.State.TIMED_WAITING ));
        safeJoin(thread);
        System.out.println("Test: back from join.");

        assertTrue( thread.getState().equals( Thread.State.TERMINATED ));
        safeJoin( thread );
        System.out.println("Test: back from join.");
    }

    private static void safeJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testJoin2() {
        Thread sleepThread = new SleepThread(1000);
        Thread joinThread = new WaitThread(sleepThread);
        sleepThread.start();
        joinThread.start();
        safeSleep( 100 );
        assertTrue( joinThread.getState().equals( Thread.State.WAITING) );
        while( sleepThread.getState().equals( Thread.State.TIMED_WAITING ) ) safeSleep( 100 );
        safeSleep( 100 );
        assertTrue( joinThread.getState().equals( Thread.State.TERMINATED ) );
//
//        assertTrue( thread.getState().equals( Thread.State.NEW ));
//        safeJoin( thread );
//        System.out.println("Test: back from join.");
//
//        thread.start();
//        safeSleep( 100 );
//        assertTrue( thread.getState().equals( Thread.State.TIMED_WAITING ));
//        safeJoin(thread);
//        System.out.println("Test: back from join.");
//
//        assertTrue( thread.getState().equals( Thread.State.TERMINATED ));
//        safeJoin( thread );
//        System.out.println("Test: back from join.");

    }

}
