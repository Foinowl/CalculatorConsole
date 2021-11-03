package com.company.service;

import com.company.exeptions.IllegalOperationException;
import com.company.lexeme.*;

import java.util.*;

public class ParserServiceImpl implements ParserService {
    private ValidationServer validationServer = new ValidationServerImpl();

    @Override
    public List<Lexeme> parse(String line) {
        List<String> listExpression = removeEitherSpaceFromLine(line);
        List<Lexeme> listLexemes = new ArrayList<>();

        for (String symbol : listExpression) {
            listLexemes.add(buildExpressionOfLexemes(symbol));
        }

        return listLexemes;
    }

    private List<String> removeEitherSpaceFromLine(String line) {
//        the case when all tokens are separated by a space
        return List.of(line.split("\\s+"));
    }

    private Lexeme buildExpressionOfLexemes(String token) {
        Optional<BinaryOperator> optionalOperator = (BinaryOperator.of(token));
        if (optionalOperator.isPresent()) {
            return optionalOperator.get();
        }
        Optional<Brackets> optionalBrackets = Brackets.of(token);
        if (optionalBrackets.isPresent()) {
            return optionalBrackets.get();
        }

        if (validationServer.canBuildNumber(token)) {
            return (Lexeme) ValueLexeme.build(token);
        }
        throw new IllegalOperationException("Unsupported token: " + token);
    }
}
