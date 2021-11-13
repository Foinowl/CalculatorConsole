package com.company.service.impl;

import com.company.lexeme.*;
import com.company.service.*;

import java.util.*;

public class CalculatorServiceImpl implements CalculatorService {

    private final ParserService parserService = new ParserServiceImpl();
    private final PostfixNotationService postfixNotationService = new ExpressionPostfixNotationImpl();

    public CalculatorServiceImpl() {
    }

    public double execute(String line) throws Exception {
        List<Lexeme> listLexeme = parserService.parse(line);

        List<Lexeme> listPostfixNotation = postfixNotationService.buildPostfixNotation(listLexeme);
        return postfixNotationService.getAndCalculateResultByPostfixNotation(listPostfixNotation);
    }
}