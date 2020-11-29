package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class SetSplitter {
    public static void main(String[] args) throws IOException {
        String test = "{\"set\": [4, 5, 6, 7, 8]}";
        System.out.println(getSets(test));
    }

    public static ObjectNode getSets(String src) throws IOException {
        Integer[] values = Utils.fromJson(Utils.parse(src).get("set"), Integer[].class);

        Pair sets = findSets(Arrays.asList(values));

        JsonNode set1 = Utils.toJson(sets.getValue());
        JsonNode set2 = Utils.toJson(sets.getKey());

        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode result = factory.objectNode();
        result.set("set_1", set1);
        result.set("set_2", set2);

        return result;
    }

    private static Pair<List<Integer>, List<Integer>> findSets(List<Integer> inputSet) {
        int tempSum = 0;
        int weight = getSum(inputSet) / 2;

        inputSet.sort(Comparator.reverseOrder());

        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();

        for (Integer currValue : inputSet) {
            if (tempSum + currValue > weight) {
                set2.add(currValue);
            } else {
                set1.add(currValue);
                tempSum += currValue;
            }
        }

        Collections.sort(set1);
        Collections.sort(set2);

        return new Pair<>(set1, set2);
    }

    private static int getSum(List<Integer> input) {
        int sum = 0;
        for (Integer value : input) {
            sum += value;
        }
        return sum;
    }
}
