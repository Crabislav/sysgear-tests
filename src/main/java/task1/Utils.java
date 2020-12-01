package task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import task1.exceptions.BadRequestException;

public class Utils {
    static final String RULES_PATH = "conversionRules.json";
    private static ObjectMapper mapper;

    private Utils() {
    }

    public static ObjectMapper getObjectMapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    static void validateValues(String from, String to, double value) throws BadRequestException {
        if (from == null || from.isEmpty()) {
            throw new BadRequestException("\"From\" can't be null or empty. Your input is: " + from);
        }

        if (to == null || to.isEmpty()) {
            throw new BadRequestException("\"To\" can't be null or empty. Your input is: " + to);
        }

        if (value <= 0) {
            throw new BadRequestException("\"Value\" must be greater than 0. Your input is: " + value);
        }
    }
}
