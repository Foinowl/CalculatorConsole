package com.company;

import com.company.service.CalculatorService;
import com.company.service.CalculatorServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        CalculatorService service = new CalculatorServiceImpl();
        System.out.println(service.execute("( -2 ) - ( ( -4 ) * 3.5 ) "));
//        System.out.println(service.execute("( 4.2 + 2 ) * ( 3 / 3 - 6.1 )"));
    }
}
