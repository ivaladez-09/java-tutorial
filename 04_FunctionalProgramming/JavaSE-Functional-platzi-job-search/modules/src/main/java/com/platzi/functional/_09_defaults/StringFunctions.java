package com.platzi.functional._09_defaults;

public class StringFunctions {
    public static void main(String[] args) {
        StringOperation six = () -> 6;
        six.operate("Alumno");

        DoOperation operateFive = text -> System.out.println(text);
        operateFive.execute(5, "Platzi");
    }

    @FunctionalInterface
    interface StringOperation {
        int getAmaount();
        default void operate(String text){
            int x = getAmaount();
            while (x-- > 0){
                System.out.println(text);
            }
        }
    }

    @FunctionalInterface
    interface DoOperation {
        void take(String text);
        default void execute(int x, String text){
            while (x-- > 0){
                take(text);
            }
        }
    }
}
