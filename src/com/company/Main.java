package com.company;

public class Main {

    public static void main(String[] args) {
        CalculatorService service = new CalculatorServiceImpl("4.2 + ( 2 * 3 / 3 ) - 6.1");
        service.execute();
    }
}
