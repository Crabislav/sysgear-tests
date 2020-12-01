package task2;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private static ObjectMapper mapper = getObjectMapperInstance();

    public static ObjectMapper getObjectMapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}
