package task1.model;

public class Rule {
    private String from;
    private String to;
    private double coefficient;

    public Rule() {
    }

    public Rule(String from, String to, double coefficient) {
        this.from = from;
        this.to = to;
        this.coefficient = coefficient;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!from.equals(rule.from) && !from.equals(rule.to)) return false;
        return to.equals(rule.to) || to.equals(rule.from);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}
