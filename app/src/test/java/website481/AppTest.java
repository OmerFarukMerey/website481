/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package website481;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class AppTest {
    @Test
    public void testFound() {
       ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
       assertTrue(App.searchForMedianOfThreeInt(array, 1,2,3));
     }
 
     @Test
     public void testNotFound() {
       ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
       assertFalse(App.searchForMedianOfThreeInt(array, 100, 103, 105));
     }
 
     @Test
     public void testEmptyArray() {
       ArrayList<Integer> array = new ArrayList<>();
       assertFalse(App.searchForMedianOfThreeInt(array, 4,5,6));
     }
 
     @Test
     public void testNull() {
       assertFalse(App.searchForMedianOfThreeInt(null, 1,2,3));
     }
 
}
