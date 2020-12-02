package task2.rules;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exclude extends Rule {
    @Override
    public void applyRule(List<LinkedHashMap<String, Object>> rawData) {
        List<Object> mapsWithValuesForRule = getValue();

        if (rawData.isEmpty()) {
            return;
        }

        for (Object map : mapsWithValuesForRule) {
            if (!(map instanceof Map)) {
                continue;
            }

            Set<?> mapKeys = ((Map<?, ?>) map).keySet();
            mapKeys.forEach(key -> rawData.removeIf(data -> data.containsValue(((Map<?, ?>) map).get(key))));
        }
    }
}
