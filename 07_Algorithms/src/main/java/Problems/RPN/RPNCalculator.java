package Problems.RPN;

import java.util.Stack;

public class RPNCalculator {

    public int calculate(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int numA, numB;
        String choice = "";

        for (int i = 0; i < tokens.length; i++) {

            // Push to Stack the numbers
            if (!isOperator(tokens[i])) {
                stack.push(tokens[i]);
                continue;
            }
            else {
                choice = tokens[i];
            }

            switch (choice) {
                case "+":
                    numA = Integer.parseInt(stack.pop());
                    numB = Integer.parseInt(stack.pop());
                    stack.push((numB + numA) + "");
                    break;
                case "-":
                    numA = Integer.parseInt(stack.pop());
                    numB = Integer.parseInt(stack.pop());
                    stack.push((numB - numA) + "");
                    break;
                case "*":
                    numA = Integer.parseInt(stack.pop());
                    numB = Integer.parseInt(stack.pop());
                    stack.push((numB * numA) + "");
                    break;
                case "/":
                    numA = Integer.parseInt(stack.pop());
                    numB = Integer.parseInt(stack.pop());
                    stack.push((numB / numA) + "");
                    break;
                default:
                    continue;
            }
        }
        // The last value must be the result
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String operator) {
        return operator.equals("+") || operator.equals("-") ||
                operator.equals("*") || operator.equals("/");
    }

}
