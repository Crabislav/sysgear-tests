package task1.model;

public class Distance {
    private String unit;
    private double value;

    public Distance() {
    }

    public Distance(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }
}
