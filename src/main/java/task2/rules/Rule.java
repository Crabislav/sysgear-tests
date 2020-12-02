package task2.rules;

import task2.exceptions.RuleNotFoundException;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class Rule {
    private List<Object> value;

    public static Rule getRuleInstance(String ruleName) throws RuleNotFoundException {
        switch (ruleName) {
            case "include":
                return new Include();
            case "sort_by":
                return new SortBy();
            case "exclude":
                return new Exclude();
            default:
                throw new RuleNotFoundException("Rule " + ruleName + " was not found");
        }
    }

    public abstract void applyRule(List<LinkedHashMap<String, Object>> rawData);

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "value=" + value +
                '}';
    }
}
