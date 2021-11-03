package com.company;

import com.company.service.CalculatorService;
import com.company.service.ConsoleService;
import com.company.service.impl.CalculatorServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CalculatorService service = new CalculatorServiceImpl();

        ConsoleService.writeMessage("""
                Приветсвует калькулятор.
                Каждый токен должен быть разделен пробелом, иначе выражение не выполнится :(
                Введите ваше выражение""");

        String valueExpression = service.execute(ConsoleService.readLine());

        ConsoleService.writeMessage(String.format("Полученное значение: %s", valueExpression));
//        System.out.println(service.execute("( -2 ) - ( ( -4 ) * 3.5 ) "));
//        System.out.println(service.execute("( ( -4.2 ) + 2 ) * ( 3 / 3 - 6.1 ) "));
//        System.out.println(service.execute("0 / 5"));
//        System.out.println(service.execute("5 * 20"));
//        System.out.println(service.execute("5"));
//        System.out.println(service.execute("5 / 0"));
    }
}
