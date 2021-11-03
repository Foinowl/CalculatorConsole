package com.company.service;

import com.company.lexeme.*;

import java.util.*;

public class CalculatorServiceImpl implements CalculatorService {

    private ParserService parserService = new ParserServiceImpl();
    private PostfixNotationService postfixNotationService = new ExpressionPostfixNotationImpl();

    public CalculatorServiceImpl() {
    }

    public String execute(String line) {
        List<Lexeme> listLexeme = parserService.parse(line);

        List<Lexeme> listPostfixNotation = postfixNotationService.buildPostfixNotation(listLexeme);
        return String.valueOf(postfixNotationService.getAndCalculateResultByPostfixNotation(listPostfixNotation));
    }

}
