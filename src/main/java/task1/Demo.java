package task1;

public class Demo {
    public static void main(String[] args) {
        String test1 = "{\"distance\": {\"unit\": \"m\", \"value\": 100}, \"convert_to\": \"cm\"}";
        String test2 = "{\"distance\": {\"unit\": \"cm\", \"value\": 100}, \"convert_to\": \"m\"}";

        String test3 = "{\"distance\": {\"unit\": \"ft\", \"value\": 0.5}, \"convert_to\": \"cm\"}";
        String test4 = "{\"distance\": {\"unit\": \"ft\", \"value\": 0.5}, \"convert_to\": \"cm\"}";

        String[] testValues = {test1, test2, test3, test4};

        for (String testValue : testValues) {
            try {
                System.out.println(Converter.convert(testValue));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

//
//        try {
//            RuleProcessor.addRule("cm", "m", 100);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//
//        try {
//            RuleProcessor.addRule("m", "cm", 100);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

    }
}
