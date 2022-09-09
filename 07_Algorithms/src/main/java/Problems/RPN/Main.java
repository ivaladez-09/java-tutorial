package Problems.RPN;

public class Main {
    public static void main(String[] args) {
        var rpnCalculator = new RPNCalculator();

        String[] tokens = { "10", "6", "9",  "3", "+", "-11", "*", "/",  "*", "17", "+", "5", "+" };
        System.out.println(rpnCalculator.calculate(tokens));


        var rpn = new RPN();
        String infix = "1+2*(3-4)";
        System.out.println(rpn.valueOf(infix));

        System.out.println(rpn.valueOf("1+2*(3-(8%4)+5)*6"));
    }
}
