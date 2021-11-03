package com.company;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class ParserServiceImpl implements ParserService{
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
        Optional<Operator> optionalOperator = (BinaryOperator.of(token));
        if (optionalOperator.isPresent()) {
            return optionalOperator.get();
        }
        Optional<Lexeme> optionalBrackets = Brackets.of(token);
        if (optionalBrackets.isPresent()) {
            return optionalBrackets.get();
        }

        if (validationServer.canBuildNumber(token)) {
            return (Lexeme) ValueLexeme.build(token);
        }
        throw new IllegalOperationException("Unsupported token: " + token);
    }
}