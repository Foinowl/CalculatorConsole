package com.company.service;

import com.company.lexeme.Lexeme;

import java.util.List;

public class CalculatorServiceImpl implements CalculatorService {

    private final String line;

    private String postfixNotationExpression;
    private ParserService parserService = new ParserServiceImpl();

    public CalculatorServiceImpl(String line) {
        this.line = line;
    }

    public String execute() {
        List<Lexeme> listLexeme = parserService.parse(this.line);

        setPostfixNotationExpression(buildPostfixNotation(listLexeme));
        return "2";
    }

    private String buildPostfixNotation(final List<Lexeme> expressionLexeme) {
//        Let's start a stack, initially it is empty. We will move from left to right according to the expression in reverse Polish notation;
//        if the current element is a number or variable, then we put its value on the top of the stack;
//        if the current element is an operation, then we take out the top two elements from the stack (or one if the operation is unary),
//        apply the operation to them, and put the result back on the stack.
//        In the end, there will be exactly one element left in the stack - the value of the expression.
        return null;
    }


    private String getPostfixNotationExpression() {
        return postfixNotationExpression;
    }

    private void setPostfixNotationExpression(String postfixNotationExpression) {
        this.postfixNotationExpression = postfixNotationExpression;
    }

}
