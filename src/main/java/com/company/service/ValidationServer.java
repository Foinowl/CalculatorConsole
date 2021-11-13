package com.company.service;

import java.util.Iterator;
import java.util.List;

public interface ValidationServer {
    boolean canBuildNumber(String token);

    boolean validateBracketsSequence(Iterator<String> listIterator);

    boolean validateUnaryOperatorBetweenBrackets(
                                                 String nextToken,
                                                 String currentToken);

    boolean validateLengthExpression(List<String> list);
}
