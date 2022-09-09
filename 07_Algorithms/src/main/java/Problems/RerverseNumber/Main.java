package Problems.RerverseNumber;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverse(12345));
        reverseRecursively(12345);
        System.out.println(rev);
    }

    public static int reverse(int num) {
        if (num == 0) {
            return 0;
        }

        int reverse = 0;
        while (num != 0) {
            int remainder = num % 10;
            reverse = (reverse * 10) + remainder;
            num = num / 10;
        }

        return reverse;
    }

    public static int rev = 0;
    public static void reverseRecursively(int num) {
        if (num <= 0) {
            return ;
        }

        int remainder = num % 10;
        rev = (rev * 10) + remainder;
        reverseRecursively(num / 10);

    }
}
