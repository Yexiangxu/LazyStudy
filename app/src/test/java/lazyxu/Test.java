package lazyxu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * User:Lazy_xu
 * Date:2024/01/16
 * Description:
 * FIXME
 */


public class Test {
    private static List<String> titleDefList = new ArrayList<>(
            Arrays.asList(new String[]{"逆袭", "家庭", "热血", "总裁", "爱情", "赘婿"}));

    private static List<String> dealTitle(List<String> resultList) {
        List<String> list = new ArrayList<>(titleDefList);
        for (String title : titleDefList) {
            if (!resultList.contains(title)) {
                list.remove(title);
                for (String newTitle : resultList) {
                    if (!titleDefList.contains(newTitle)) {
                        list.add(newTitle);
                        break;
                    }
                }
            }
        }
        list.add(0, "精选");
        return list;
    }


    private static final Map<String, BinaryOperator<Double>> operationTable = new HashMap<>();

    static {
        operationTable.put("+", (a, b) -> a + b);
        operationTable.put("-", (a, b) -> a - b);
        operationTable.put("*", (a, b) -> a * b);
        operationTable.put("/", (a, b) -> a / b);
    }

    public static double calculate(double operand1, String operator, double operand2) {
        BinaryOperator<Double> operation = operationTable.get(operator);
        if (operation != null) {
            return operation.apply(operand1, operand2);
        } else {
            throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public static void main(String[] args) {
        double result1 = calculate(5, "+", 3);
        System.out.println("5 + 3 = " + result1);

        double result2 = calculate(8, "*", 2);
        System.out.println("8 * 2 = " + result2);

        try {
            double result3 = calculate(10, "%", 2); // Unsupported operator
            System.out.println("Result: " + result3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

