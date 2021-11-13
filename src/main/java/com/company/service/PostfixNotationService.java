package com.company.service;

import com.company.exeptions.NotFoundOperationException;
import com.company.exeptions.SyntaxErrorCalculator;
import com.company.lexeme.Lexeme;

import java.util.List;

public interface PostfixNotationService {
    List<Lexeme> buildPostfixNotation(List<Lexeme> expressionLexeme) throws SyntaxErrorCalculator, NotFoundOperationException;

    Double getAndCalculateResultByPostfixNotation(List<Lexeme> postfixExpression);
}
