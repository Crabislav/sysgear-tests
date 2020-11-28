package task1;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    static final String RULES_PATH = "conversionRules.json";
    private static ObjectMapper mapper;

    public static ObjectMapper getObjectMapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }


}
