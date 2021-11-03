package com.company.exeptions;

public class SyntaxErrorCalculator extends RuntimeException{
    public SyntaxErrorCalculator(String message) {
        super(message);
    }
}
