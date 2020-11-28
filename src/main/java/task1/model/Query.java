package task1.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Query {
    private Distance distance;
    private String convertTo;

    public Distance getDistance() {
        return distance;
    }

    @JsonGetter("convert_to")
    public String getConvertTo() {
        return convertTo;
    }

}
