package Problems.Parentheses;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"(){}[]", "(([{}]))", "(({[)}]", "", "{", "[]"};

        for (String s: strings) {
            System.out.println(isBalanced(s));
        }

    }

    public static boolean isBalanced(String s) {
        if (s == null || s.isBlank()) return true;

        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if (stack.isEmpty()) {
                return false;
            }
            else if ((stack.peek() == '(' && c == ')') ||
                    (stack.peek() == '{' && c == '}') ||
                    (stack.peek() == '[' && c == ']')) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
