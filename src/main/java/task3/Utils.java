package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private static ObjectMapper mapper = getObjectMapperInstance();

    public static ObjectMapper getObjectMapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static JsonNode toJson(Object obj) {
        return mapper.valueToTree(obj);
    }
}
