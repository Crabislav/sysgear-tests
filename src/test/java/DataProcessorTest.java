import org.junit.Test;
import task2.DataProcessor;
import task2.exceptions.BadRequestException;
import task2.exceptions.RuleNotFoundException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DataProcessorTest {
    @Test
    public void processDataShouldApplyExcludeRuleCorrectly() throws RuleNotFoundException, BadRequestException, IOException {
        String jsonWithConditionAndExcludeRule = "{\"data\":[{\"user\":\"mike@mail.com\",\"rating\":\"20\",\"disabled\":\"false\"}," +
                "{\"user\":\"greg@mail.com\",\"rating\":\"14\",\"disabled\":\"false\"}," +
                "{\"user\":\"john@mail.com\",\"rating\":\"25\",\"disabled\":\"true\"}]," +
                "\"condition\":{\"exclude\":[{\"disabled\":\"true\"}],\"sort_by\":[\"rating\"]}}";

        String expected = "{\"result\":[{\"user\":\"greg@mail.com\",\"rating\":\"14\",\"disabled\":\"false\"}," +
                "{\"user\":\"mike@mail.com\",\"rating\":\"20\",\"disabled\":\"false\"}]}";

        String actual = DataProcessor.processData(jsonWithConditionAndExcludeRule).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void processDataValidInputNoConditionShouldReturnTheSameData() throws RuleNotFoundException, BadRequestException, IOException {
        String jsonWithoutCondition = "{\"data\":[{\"user\":\"mike@mail.com\",\"rating\":\"20\",\"disabled\":\"false\"}," +
                "{\"user\":\"greg@mail.com\",\"rating\":\"14\",\"disabled\":\"false\"}," +
                "{\"user\":\"john@mail.com\",\"rating\":\"25\",\"disabled\":\"true\"}]}";

        String actual = DataProcessor.processData(jsonWithoutCondition).toString();
        assertEquals(jsonWithoutCondition, actual);
    }

    @Test
    public void processDataShouldApplyIncludeRuleCorrectly() throws RuleNotFoundException, BadRequestException, IOException {
        String jsonWithIncludeRule = "{\"data\":[{\"name\":\"John\",\"email\":\"john2@mail.com\"},{\"name\":\"John\",\"email\":\"john1@mail.com\"}," +
                "{\"name\":\"Jane\",\"email\":\"jane@mail.com\"}],\"" +
                "condition\":{\"include\":[{\"name\":\"John\"}],\"sort_by\":[\"email\"]}}";

        String expected = "{\"result\":[{\"name\":\"John\",\"email\":\"john1@mail.com\"}," +
                "{\"name\":\"John\",\"email\":\"john2@mail.com\"}]}";
        String actual = DataProcessor.processData(jsonWithIncludeRule).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void sortByRuleShouldSortCorrectly() throws RuleNotFoundException, BadRequestException, IOException {
        String jsonWithSortByRule = "{\"data\":[{\"name\":\"John\",\"email\":\"john2@mail.com\"},{\"name\":\"John\",\"email\":\"john1@mail.com\"}," +
                "{\"name\":\"Jane\",\"email\":\"jane@mail.com\"}],\"" +
                "condition\":{\"sort_by\":[\"email\"]}}";

        String actual = DataProcessor.processData(jsonWithSortByRule).toString();
        String expected = "{\"result\":[{\"name\":\"Jane\",\"email\":\"jane@mail.com\"}," +
                "{\"name\":\"John\",\"email\":\"john1@mail.com\"},{\"name\":\"John\",\"email\":\"john2@mail.com\"}]}";

        assertEquals(expected, actual);
    }

    @Test(expected = BadRequestException.class)
    public void processDataMustThrowBadRequestException() throws RuleNotFoundException, BadRequestException, IOException {
        String invalidString = "I'm an invalid sting";
        DataProcessor.processData(invalidString);
    }
}
