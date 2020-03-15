package regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRegex {
    @Test
    public void testRegex() {
        String content = "This is Chaitanya " +
                "from Beginnersbook.com.";

        assertTrue( Pattern.matches(".*book.*", content) );


        String text = "ThisIsChaitanya.ItISMyWebsite";
        // Pattern for delimiter
        String patternString = "is";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        String[] myStrings = pattern.split(text);
        Assert.assertArrayEquals( new String[]{"Th","","Chaitanya.It","MyWebsite"}, myStrings );
        Matcher matcher = pattern.matcher(text);
        while( matcher.find() ) {
            System.out.println("matcher.start() = " + matcher.start());
            System.out.println("matcher.end() = " + matcher.end());
        }

    }

    @Test
    public void testPatterns() {
        assertFalse( Pattern.matches("[pqr]", "abcd") );
        assertTrue( Pattern.matches("[pqr]", "r") ); //Return true as r is found
        assertFalse( Pattern.matches("[pqrst]", "pq") ); // Return false as any one of them can be in text not both.


        // It would return true if string matches exactly "tom"
        assertFalse(
                Pattern.matches("tom", "Tom")); //False

        /* returns true if the string matches exactly
         * "tom" or "Tom"
         */
        assertTrue(
                Pattern.matches("[Tt]om", "Tom")); //True

        /* Returns true if the string matches exactly "tim"
         * or "Tim" or "jin" or "Jin"
         */
        assertTrue(
                Pattern.matches("[tT]im|[jJ]in", "Tim"));//True
        assertTrue(
                Pattern.matches("[tT]im|[jJ]in", "jin"));//True

        /* returns true if the string contains "abc" at
         * any place
         */
        assertTrue(
                Pattern.matches(".*abc.*", "deabcpq"));//True

        /* returns true if the string does not have a
         * number at the beginning
         */
        assertFalse(
                Pattern.matches("^[^\\d].*", "123abc")); //False
        assertTrue(
                Pattern.matches("^[^\\d].*", "abc123")); //True

        // returns true if the string contains of three letters
        assertTrue(
                Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "aPz"));//True
        assertTrue(
                Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "aAA"));//True
        assertFalse(
                Pattern.matches("[a-zA-Z][a-zA-Z][a-zA-Z]", "apZx"));//False

        // returns true if the string contains 0 or more non-digits
        assertTrue(
                Pattern.matches("\\D*", "abcde")); //True
        assertFalse(
                Pattern.matches("\\D*", "abcde123")); //False

        /* Boundary Matchers example
         * ^ denotes start of the line
         * $ denotes end of the line
         */
        assertFalse(
                Pattern.matches("^This$", "This is Chaitanya")); //False
        assertTrue(
                Pattern.matches("^This$", "This")); //True
        assertFalse(
                Pattern.matches("^This$", "Is This Chaitanya")); //False
    }
}
