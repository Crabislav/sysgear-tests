import org.junit.Test;
import task3.SetSplitter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SetSplitterTest {

    @Test
    public void getSetsShouldReturnTwoBalancedArrays() throws IOException {
        String testValue = "{\"set\": [4,5,6,7,8]}";
        String expectedResult = "{\"set_1\":[4,5,6],\"set_2\":[7,8]}";
        assertEquals(expectedResult, SetSplitter.getSets(testValue).toString());
    }
}
