package task2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import task2.exceptions.BadRequestException;
import task2.exceptions.RuleNotFoundException;
import task2.rules.Rule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataProcessor {
    public static void main(String[] args) {
        //demo usage
    }


    static String processData(File input) throws IOException, RuleNotFoundException, BadRequestException {
        JsonNode root = Utils.getObjectMapperInstance().readTree(input);

        List<Object> data = getData(root);
        List<Rule> rules = getRules(root);

        rules.forEach(rule -> rule.applyRule(data));

        //Return data as Json
        // TODO: 30.11.2020 change output result
        String result = "";
        return result;
    }

    private static List<Object> getData(JsonNode root) throws BadRequestException {
        if (root == null) {
            throw new BadRequestException("Input node can't be null");
        }

        Utils.getObjectMapperInstance().convertValue(root.get("data"), new TypeReference<List<Object>>() {
        });

        // TODO: 30.11.2020 add logic for filling up data list
        List<Object> data = new ArrayList<>();
        return data;
    }

    private static List<Rule> getRules(JsonNode root) throws RuleNotFoundException, BadRequestException {
        if (root == null) {
            throw new BadRequestException("Input node can't be null");
        }

        List<Rule> rules = new ArrayList<>();

        Map<String, Object> parsedRulesValues = Utils.getObjectMapperInstance()
                .convertValue(root.get("condition"), new TypeReference<>() {
                });

        Set<String> ruleNames = parsedRulesValues.keySet();

        for (String ruleName : ruleNames) {
            Rule rule = Rule.getRuleInstance(ruleName);
            rule.setValue(parsedRulesValues.get(ruleName));
            rules.add(rule);
        }
        return rules;
    }
}
