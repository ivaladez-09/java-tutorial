package com.platzi.functional._10_chaining;

public class Encadenar {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola")
                .append("alumno")
                .append("platzi");

        Chainer chainer = new Chainer();
        chainer.sayHi().sayBye();
    }

    static class Chainer {
        public Chainer sayHi(){
            System.out.println("Hola");
            return this;
        }
        public Chainer sayBye(){
            System.out.println("Bye");
            return this;
        }
    }
}
