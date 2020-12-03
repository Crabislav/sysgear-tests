package task2.rules;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.LinkedHashMap;
import java.util.List;

public class SortBy extends Rule {
    @Override
    public void applyRule(List<LinkedHashMap<Object, Object>> rawData) {
        List<Object> keysForSortBy = getValue();

        if (keysForSortBy.isEmpty()) {
            return;
        }

        rawData.sort((o1, o2) -> {
            CompareToBuilder compareToBuilder = new CompareToBuilder();
            keysForSortBy.forEach(key -> compareToBuilder.append(o1.get(key), o2.get(key)));
            return compareToBuilder.toComparison();
        });


    }
}
