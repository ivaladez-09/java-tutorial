package Problems.RPN;

import java.util.*;

public class RPN {
    private static final List<Character> numbers = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));


    public String valueOf(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder rpn = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char character = infix.charAt(i);

            if (isNumber(character)) {
                rpn.append(character);
                continue;
            }
            else {
                if (character == ')') {
                    while (stack.peek() != '(') {
                        rpn.append(stack.pop());
                    }
                    stack.pop(); // poo '('
                    continue;
                }
                stack.push(character);
            }
        }

        while (!stack.isEmpty()) {
            rpn.append(stack.pop());
        }

        return rpn.toString();
    }

    public static boolean isNumber(char c) {
        return numbers.stream()
                .anyMatch(num -> num == c);
    }
}
