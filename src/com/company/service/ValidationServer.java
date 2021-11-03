package com.company.service;

import java.util.Iterator;
import java.util.ListIterator;

public interface ValidationServer {
    boolean canBuildNumber(String token);

    boolean validateBracketsSequence(Iterator<String> listIterator);

    boolean validateUnaryOperatorBetweenBrackets(ListIterator<String> listIterator,
                                                 String nextToken,
                                                 String currentToken);
}
