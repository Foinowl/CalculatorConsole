package com.company;

import com.company.service.CalculatorService;
import com.company.service.impl.CalculatorServiceImpl;

public class Main {

    public static void main(String[] args) {
        CalculatorService service = new CalculatorServiceImpl();
        System.out.println(service.execute("( -2 ) - ( ( -4 ) * 3.5 ) "));
        System.out.println(service.execute("( ( -4.2 ) + 2 ) * ( 3 / 3 - 6.1 ) "));
        System.out.println(service.execute("0 / 5"));
        System.out.println(service.execute("5 * 20"));
        System.out.println(service.execute("5 / 0"));
    }
}
