package exceptions;

import org.junit.Test;

import java.io.IOException;

public class TestExceptions {

    public class MyCheckedException extends Exception {

    }
    public class MyUncheckedException extends RuntimeException {

    }

    void throwSomeCheckedExceptions(int num) throws IOException, ClassNotFoundException, MyCheckedException {
        if(num==1)
            throw new IOException("IOException Occurred");
        else if( num == 2 )
            throw new MyCheckedException();
        else
            throw new ClassNotFoundException("ClassNotFoundException");
    }

    public void throwSomeUncheckedExceptions( int choice ) {
        switch( choice ) {
            case 1:
                throw new ArithmeticException();
            case 2:
                throw new IndexOutOfBoundsException();
            case 3:
                throw new MyUncheckedException();
            default:
        }
    }
    @Test
    public void testException() {
        throwSomeUncheckedExceptions( 4 );
        // throwSomeCheckedExceptions( 17 ); /* Does not compile */
        try {
            throwSomeCheckedExceptions( 4 );

        } catch( MyCheckedException e ) {
            System.out.println("e.getMessage() = " + e.getMessage());
        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        //int num=Integer.parseInt ("XYZ") ;
    }
}
