package com.company.service;

import com.company.lexeme.Lexeme;

import java.util.LinkedList;
import java.util.List;

public interface PostfixNotationService {
    List<Lexeme> buildPostfixNotation(List<Lexeme> expressionLexeme);
    Number getAndCalculateResultByPostfixNotation(List<Lexeme> postfixExpression);
}
