package task1;

import com.fasterxml.jackson.core.JsonParseException;
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
    public static void main(String[] args) {
        //demo usage
    }

    private Converter() {
    }

    public static String convertValues(String src) throws Exception {
        Rule[] rules = getRules();

        Query query;
        try {
            query = Utils.getObjectMapperInstance().readValue(src, Query.class);
        } catch (JsonParseException | JsonMappingException e) {
            throw new BadRequestException("Can't create query from  (" + src + ")");
        }
        checkQueryRuleExistence(rules, query);
        Utils.validateValues(query.getDistance().getUnit(), query.getConvertTo(), query.getDistance().getValue());

        Distance result = new Distance(query.getConvertTo(), getConvertedValue(query, rules));
        return Utils.getObjectMapperInstance().writeValueAsString(result);
    }

    private static Rule[] getRules() throws EmptyFileException, IOException, NoRulesException {
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
        return rules;
    }

    private static void checkQueryRuleExistence(Rule[] rules, Query query) throws NoRulesException {
        Rule requiredRule = new Rule(query.getDistance().getUnit(), query.getConvertTo());

        for (Rule rule : rules) {
            if (rule.equals(requiredRule)) {
                return;
            }
        }
        throw new NoRulesException("Can't find suitable rule for query");
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
