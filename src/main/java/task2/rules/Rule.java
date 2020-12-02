package task2.rules;

import task2.exceptions.RuleNotFoundException;

import java.util.List;

public abstract class Rule {
    private Object value;

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

    public abstract void applyRule(List<Object> rawData);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "value=" + value +
                '}';
    }
}
