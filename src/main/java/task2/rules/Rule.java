package task2.rules;

import task2.exceptions.RuleNotFoundException;

import java.util.List;

public abstract class Rule {
    private String name;
    private Object value;

    public static Rule getRuleInstance(String ruleName) throws RuleNotFoundException {
        Rule rule;
        switch (ruleName) {
            case "include":
                rule = new Include();
                break;
            case "sort_by":
                rule = new SortBy();
                break;
            case "exclude":
                rule = new Exclude();
                break;
            default:
                throw new RuleNotFoundException("Rule " + ruleName + " was not found");
        }

        rule.setName(ruleName);
        return rule;
    }

    public abstract void applyRule(List<Object> rawData);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
