package com.company.service.impl;

import com.company.service.ValidationServer;

import java.util.Iterator;
import java.util.ListIterator;

public class ValidationServerImpl implements ValidationServer {
    @Override
    public boolean canBuildNumber(String token) {
        return token.matches("\\d*\\d*\\d+\\.?\\d*");
    }

    private boolean canBuildNumberWithUnarPrefix(String token) {
        return token.matches("^(0$|-?[1-9]\\d*(\\.\\d*[1-9]$)?|-?0\\.\\d*[1-9])$");
    }

    public boolean validateBracketsSequence(Iterator<String> listIterator) {
        int bracketsCount = 0;

        while (listIterator.hasNext()) {
            String token = listIterator.next();
            if (token.equals("(")) {
                bracketsCount++;
            } else if (token.equals(")")) {
                bracketsCount--;
            }
        }

        return bracketsCount == 0;
    }

    public boolean validateUnaryOperatorBetweenBrackets(ListIterator<String> listIterator,
                                                        String nextToken,
                                                        String currentToken) {

        if (nextToken.equals(")")) {
            return canBuildNumberWithUnarPrefix(currentToken);
        }
        return false;
    }
}
