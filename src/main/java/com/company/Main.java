package com.company;

import com.company.service.CalculatorService;
import com.company.service.ConsoleService;
import com.company.service.impl.CalculatorServiceImpl;

public class Main {

    public static void main(String[] args) throws Exception {
        CalculatorService service = new CalculatorServiceImpl();

        ConsoleService.writeMessage("""
                Приветсвует калькулятор.
                Каждый токен должен быть разделен пробелом, иначе выражение не выполнится :(
                Введите ваше выражение""");

        double valueExpression = service.execute(ConsoleService.readLine());

        ConsoleService.writeMessage(String.format("Полученное значение: %s", String.format("%.2f", valueExpression)));

    }
}
