package task1;

import com.fasterxml.jackson.databind.JsonMappingException;
import task1.exceptions.BadRequestException;
import task1.exceptions.EmptyFileException;
import task1.exceptions.NoRulesException;
import task1.model.Distance;
import task1.model.Query;
import task1.model.Rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Converter {
    public static String convert(String jsonObj) throws IOException, EmptyFileException, NoRulesException {
        //get rules
        File rulesFile = Paths.get(Utils.RULES_PATH).toFile();
        if (rulesFile.length() == 0) {
            throw new EmptyFileException("File (" + rulesFile.getName() + ") is empty");
        }

        Rule[] rules;
        try {
            rules = Utils.getObjectMapperInstance().readValue(rulesFile, Rule[].class);
        } catch (JsonMappingException e) {
            throw new NoRulesException("Can't get rules from file (" + rulesFile.getName() + ")");
        }

        //create query
        Query query = Utils.getObjectMapperInstance().readValue(jsonObj, Query.class);

//        String convertTo = query.getConvertTo();
//
//        for (Rule rule : rules) {
//            String ruleTo = rule.getTo();
//
//            if (!convertTo.equals(ruleTo) || !query.getDistance().getUnit().equals(rule.getFrom())) {
//                throw new NoRulesException("Can't find suitable conversion rule");
//            }
//        }

        Distance result = new Distance(query.getConvertTo(), getConvertedValue(query, rules));
        return Utils.getObjectMapperInstance().writeValueAsString(result);
    }

    private static double getConvertedValue(Query query, Rule[] rules) {
        double valueToConvert = query.getDistance().getValue();
        String convertFrom = query.getDistance().getUnit();
        String convertTo = query.getConvertTo();

        for (Rule rule : rules) {
            if (convertFrom.equals(rule.getFrom()) && convertTo.equals(rule.getTo())) {
                valueToConvert *= rule.getCoefficient();
                break;
            }

            if (convertFrom.equals(rule.getTo()) && convertTo.equals(rule.getFrom())) {
                valueToConvert /= rule.getCoefficient();
                break;
            }
        }
        return (double) Math.round(valueToConvert * 100) / 100;
    }
}
