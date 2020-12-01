package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.*;

public class SetSplitter {
    public static void main(String[] args) {
    }

    @SuppressWarnings("unchecked")
    public static ObjectNode getSets(String src) throws IOException {
        JsonNode setValuesNode = Utils.getObjectMapperInstance().readTree(src).get("set");
        List<Integer> values = Utils.getObjectMapperInstance().treeToValue(setValuesNode, List.class);

        List<List<Integer>> sets = findSets(values);

        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode result = factory.objectNode();

        for (int i = 0; i < sets.size(); i++) {
            JsonNode set = Utils.getObjectMapperInstance().valueToTree(sets.get(i));
            result.set("set_" + (i + 1), set);
        }

        return result;
    }

    private static List<List<Integer>> findSets(List<Integer> inputSet) {
        int tempSum = 0;
        int weight = getSum(inputSet) / 2;

        inputSet.sort(Comparator.reverseOrder());

        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();

        for (Integer currValue : inputSet) {
            if (tempSum + currValue > weight) {
                set1.add(currValue);
            } else {
                set2.add(currValue);
                tempSum += currValue;
            }
        }

        Collections.sort(set1);
        Collections.sort(set2);

        return new ArrayList<>(Arrays.asList(set1, set2));
    }

    private static int getSum(List<Integer> input) {
        int sum = 0;
        for (Integer value : input) {
            sum += value;
        }
        return sum;
    }
}
