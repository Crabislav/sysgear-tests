package task2;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import task2.exceptions.BadRequestException;
import task2.exceptions.RuleNotFoundException;
import task2.rules.Rule;

import java.io.IOException;
import java.util.*;

public class DataProcessor {
    private DataProcessor() {
    }

    public static void main(String[] args) {
        //demo usage
    }

    public static JsonNode processData(String input) throws IOException, RuleNotFoundException,
            BadRequestException {
        JsonNode root = getRoot(input);

        if (root.get("condition") == null) {
            return root;
        }

        List<LinkedHashMap<Object, Object>> data = Utils.getObjectMapperInstance()
                .convertValue(root.get("data"), new TypeReference<>() {
                });

        List<Rule> rules = getRules(root);
        rules.forEach(rule -> rule.applyRule(data));

        JsonNodeFactory factory = new JsonNodeFactory(false);

        ObjectNode result = factory.objectNode();
        result.set("result", Utils.getObjectMapperInstance().valueToTree(data));

        return result;
    }

    private static JsonNode getRoot(String input) throws JsonProcessingException, BadRequestException {
        JsonNode root;
        try {
            root = Utils.getObjectMapperInstance().readTree(input);
        } catch (JsonParseException e) {
            throw new BadRequestException("Invalid input");
        }

        if (root.get("data") == null) {
            throw new BadRequestException("Input must have \"data\" block");
        }
        return root;
    }

    private static List<Rule> getRules(JsonNode root) throws RuleNotFoundException, BadRequestException {
        if (root == null) {
            throw new BadRequestException("Input node can't be null");
        }

        List<Rule> rules = new ArrayList<>();

        Map<Object, Object> parsedRulesValues = Utils.getObjectMapperInstance()
                .convertValue(root.get("condition"), new TypeReference<>() {
                });

        Set<Object> ruleNames = parsedRulesValues.keySet();

        for (Object ruleName : ruleNames) {
            Rule rule = Rule.getRuleInstance((String) ruleName);
            rule.setValue((List<Object>) parsedRulesValues.get(ruleName));
            rules.add(rule);
        }
        return rules;
    }
}
