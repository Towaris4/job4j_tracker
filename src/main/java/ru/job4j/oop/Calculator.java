package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return x - y;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperation(int y) {
        return sum(y) + divide(y) + minus(y) + multiply(y);
    }

    public int multiply(int a) {
        return x * a;
    }

    public static void main(String[] args) {
        int result = sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        result = calculator.multiply(5);
        System.out.println(result);
        result = calculator.divide(1);
        System.out.println(result);
        result = minus(1);
        System.out.println(result);
        result = calculator.sumAllOperation(3);
        System.out.println(result);
    }
}