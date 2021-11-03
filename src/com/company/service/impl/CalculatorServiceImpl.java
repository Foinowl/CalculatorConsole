package com.company.service.impl;

import com.company.lexeme.*;
import com.company.service.*;

import java.util.*;

public class CalculatorServiceImpl implements CalculatorService {

    private final ParserService parserService = new ParserServiceImpl();
    private final PostfixNotationService postfixNotationService = new ExpressionPostfixNotationImpl();

    public CalculatorServiceImpl() {
    }

    public String execute(String line) {
        List<Lexeme> listLexeme = parserService.parse(line);

        List<Lexeme> listPostfixNotation = postfixNotationService.buildPostfixNotation(listLexeme);
        double resultValueFromExpression = postfixNotationService.getAndCalculateResultByPostfixNotation(listPostfixNotation);
        return String.format("%.2f", resultValueFromExpression);
    }
}