package task1;

import com.fasterxml.jackson.core.type.TypeReference;
import task1.exceptions.BadRequestException;
import task1.model.Rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RuleProcessor {
    public static Rule addRule(String from, String to, double coefficient) throws IOException, BadRequestException {
        Utils.validateValues(from, to, coefficient);

        File rulesFile = Paths.get(Utils.RULES_PATH).toFile();

        List<Rule> rules = new ArrayList<>();
        if (rulesFile.length() != 0) {
            rules = Utils.getObjectMapperInstance().readValue(rulesFile, new TypeReference<List<Rule>>() {});
        }

        Rule rule = new Rule(from, to, coefficient);
        if (rules.contains(rule)) {
            throw new BadRequestException(rule.toString() + " already exists");
        }

        rules.add(rule);
        Utils.getObjectMapperInstance().writerWithDefaultPrettyPrinter().writeValue(rulesFile, rules);

        return rule;
    }

}
