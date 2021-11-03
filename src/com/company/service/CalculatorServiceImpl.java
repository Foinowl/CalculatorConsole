package com.company.service;

import com.company.lexeme.Brackets;
import com.company.lexeme.Lexeme;
import com.company.lexeme.Operator;

import java.util.*;

public class CalculatorServiceImpl implements CalculatorService {

    private final String line;

    private ParserService parserService = new ParserServiceImpl();

    public CalculatorServiceImpl(String line) {
        this.line = line;
    }

    public String execute() {
        List<Lexeme> listLexeme = parserService.parse(this.line);

        List<Lexeme> listPostfixNotation = buildPostfixNotation(listLexeme);
        return String.valueOf(calculatePostfixNotation(listPostfixNotation));
    }

    private List<Lexeme> buildPostfixNotation(final List<Lexeme> expressionLexeme) {
//        Let's start a stack, initially it is empty. We will move from left to right according to the expression in reverse Polish notation;
//        if the current element is a number or variable, then we put its value on the top of the stack;
//        if the current element is an operation, then we take out the top two elements from the stack (or one if the operation is unary),
//        apply the operation to them, and put the result back on the stack.
//        In the end, there will be exactly one element left in the stack - the value of the expression.
        List<Lexeme> result = new ArrayList<>();
        Deque<Lexeme> stack = new ArrayDeque<>();

        for (Lexeme lexeme : expressionLexeme) {
            if (lexeme instanceof Operator) {
//                TODO: take out the top two values from stack, if operation is not unary
            } else if (lexeme instanceof Brackets) {
                if (((Brackets) lexeme).isOpen()) {
                    stack.addFirst(lexeme);
                } else {
                    // pop from stack until first open bracket
                }
            } else {
                result.add(lexeme);
            }
        }

        return null;
    }

    private double calculatePostfixNotation(List<Lexeme> listExpression) {
        return 0.0;
    }
}
