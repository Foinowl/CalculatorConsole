package com.company;

import com.company.service.CalculatorService;
import com.company.service.CalculatorServiceImpl;

public class Main {

    public static void main(String[] args) {
        CalculatorService service = new CalculatorServiceImpl("4.2 + ( 2 * 3 / 3 ) - 6.1");
        service.execute();
    }
}
