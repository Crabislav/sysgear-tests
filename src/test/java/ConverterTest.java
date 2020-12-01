import org.junit.Test;
import task1.Converter;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
    @Test
    public void convertFromOneMeterToCmShouldReturn100() throws Exception {
        String testValue = "{\"distance\": {\"unit\": \"m\", \"value\": 1}, \"convert_to\": \"cm\"}";
        String expectedResult = "{\"unit\":\"cm\",\"value\":100.0}";
        assertEquals(expectedResult, Converter.convertValues(testValue));
    }
}
