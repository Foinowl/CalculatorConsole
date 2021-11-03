package com.company.service.impl;

import com.company.exeptions.IllegalOperationException;
import com.company.exeptions.SyntaxErrorCalculator;
import com.company.lexeme.*;
import com.company.service.ParserService;
import com.company.service.ValidationServer;

import java.util.*;

public class ParserServiceImpl implements ParserService {
    private final ValidationServer validationServer = new ValidationServerImpl();

    @Override
    public List<Lexeme> parse(String line) {
        List<String> listExpression = removeEitherSpaceFromLine(line);

        if (!validationServer.validateBracketsSequence(listExpression.iterator())) {
            throw new SyntaxErrorCalculator("Проверьте правильную последовательность скобок");
        }

        List<Lexeme> listLexemes = new ArrayList<>();

        ListIterator<String> iteratorListExpression = listExpression.listIterator();
        while (iteratorListExpression.hasNext()) {
            listLexemes.add(buildExpressionOfLexemes(iteratorListExpression, iteratorListExpression.next()));
        }

        return listLexemes;
    }

    private List<String> removeEitherSpaceFromLine(String line) {
        return List.of(line.split("\\s+"));
    }

    private Lexeme buildExpressionOfLexemes(ListIterator<String> iteratorListExpression, String token) {
        Optional<BinaryOperator> optionalOperator = (BinaryOperator.of(token));
        if (optionalOperator.isPresent()) {
            return optionalOperator.get();
        }
        Optional<Brackets> optionalBrackets = Brackets.of(token);
        if (optionalBrackets.isPresent()) {
            return optionalBrackets.get();
        }

        if (validationServer.canBuildNumber(token)) {
            return ValueLexeme.build(token);
        }


        String nextToken = iteratorListExpression.next();
        if (validationServer.validateUnaryOperatorBetweenBrackets(iteratorListExpression, nextToken, token)) {
            iteratorListExpression.previous();
            return ValueLexeme.build(token);
        }
        throw new IllegalOperationException("Неподдерживаемый токен: " + token);
    }
}
