package task2.rules;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class SortBy extends Rule {
    @Override
    public void applyRule(List<LinkedHashMap<String, String>> rawData) {
        List<Object> keysForSortBy = getValue();

        if (keysForSortBy.isEmpty()) {
            return;
        }

        for (Object key : keysForSortBy) {
            rawData.sort(Comparator.comparing((LinkedHashMap<String, String> a) -> a.get(key)));
        }
    }
}
