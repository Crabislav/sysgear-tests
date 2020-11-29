import org.junit.Test;
import task3.SetSplitter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SetSplitterTest {

    @Test
    public void getSetsShouldReturnTwoBalancedArrays1() throws IOException {
        String testValue = "{\"set\": [4,5,6,7,8]}";
        String expectedResult = "{\"set_1\":[4,5,6],\"set_2\":[7,8]}";
        assertEquals(expectedResult, SetSplitter.getSets(testValue).toString());
    }

    @Test
    public void getSetsShouldReturnTwoBalancedArrays2() throws IOException {
        String testValue = "{\"set\": [3,3,3,7,5]}";
        String expectedResult = "{\"set_1\":[3,3,5],\"set_2\":[3,7]}";
        assertEquals(expectedResult, SetSplitter.getSets(testValue).toString());
    }
}
