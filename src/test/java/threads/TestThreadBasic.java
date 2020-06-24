package threads;

import org.junit.Test;

import static org.junit.Assert.*;

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

            t = new Thread( new Runnable() {
                @Override
                public void run() {
                    System.out.println("Runnable implementation running");
                }
            } );
            t.start();
            t.join();

        } catch (InterruptedException e) {
            fail( e.getMessage() );
        }
    }

    @Test
    public void testSleep() {
        try {
            Thread.sleep( 50 );
        } catch (InterruptedException e) {
            fail( e.getMessage() );
        }
    }

    @Test
    public void testInterrupt() {
        Thread sleepLate = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch ( InterruptedException expected ) {
                    return;
                }
                fail( "Should have been interrupted");
            }
        };
        sleepLate.start();
        sleepLate.interrupt();
        try {
            sleepLate.join();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }

    }

    public interface InterruptChecker {
        public boolean isInterrupted();
    }

    public boolean doInterruptibleBusyWork( Integer ignored, InterruptChecker interruptChecker ) {
        for (int i = 0; i < 100000; i++) {
            ignored = ignored + i + 1;
            ignored = ignored / 2;
            ignored = i + ignored;
            if (interruptChecker.isInterrupted()) {
                System.out.println("Didn't finish");
                return false;
            }
        }
        System.out.println("Finished");
        return true;
    }

    @Test
    public void testBusyThreadsNotInterruptible() {
        final boolean[] finished = {false};
        Thread uninterruptable = new Thread() {
            @Override
            public void run() {
                finished[0] = doInterruptibleBusyWork(0, () -> false );
            }
        };
        uninterruptable.start();
        uninterruptable.interrupt();
        assertFalse("Thread finished before interrupt", finished[0] );
        try {
            uninterruptable.join();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertTrue( finished[0] );
    }

    @Test
    public void testBusyThreadsCheckForInterrupts() {
        final boolean[] finished = {false};
        Thread interruptable = new Thread() {
            @Override
            public void run() {
                finished[0] = doInterruptibleBusyWork( 0, () -> this.isInterrupted() );
            }
        };
        interruptable.start();;
        interruptable.interrupt();
        assertFalse( finished[0] );
        try {
            interruptable.join();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertFalse( finished[0] );
    }

    @Test
    public void testThreadsThatPauseInterruptable() {
        final boolean[] finished = {false};
        Thread interruptable = new Thread() {
            @Override
            public void run() {
                finished[0] = doInterruptibleBusyWork( 0, () -> {
                   try {
                       Thread.sleep(10);
                   } catch( InterruptedException expected ) {
                       return false;
                   }
                   return true;
                } );
            }
        };
        interruptable.start();;
        interruptable.interrupt();
        assertFalse( finished[0] );
        try {
            interruptable.join();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        assertFalse( finished[0] );
    }

    @Test
    public void testJoinedThreadInterruptedIsNotException() {
        Thread sleeper  = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch( InterruptedException expected ) {}
            }
        };

        Thread joiner = new Thread() {
            @Override
            public void run() {
                try {
                    sleeper.join();
                } catch (InterruptedException e) {
                    fail( "Interruptions do not get passed on: " + e.getMessage() );
                }
            }
        };

        sleeper.start();
        joiner.start();
        sleeper.interrupt();
        try {
            joiner.join();
        } catch (InterruptedException e) {
            fail( "Unexpected: " + e.getMessage() );
        }
    }

    @Test
    public void testJoiningThreadInterruptedIsException() {
        Thread sleeper  = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch( InterruptedException e ) {
                    fail( "Unexpected: " + e.getMessage() );
                }
            }
        };

        Thread joiner = new Thread() {
            @Override
            public void run() {
                try {
                    sleeper.join();
                } catch ( InterruptedException expected ) {}
            }
        };

        sleeper.start();
        joiner.start();
        joiner.interrupt();
        try {
            joiner.join();
        } catch (InterruptedException e) {
            fail( "Unexpected: " + e.getMessage() );
        }
    }
}
