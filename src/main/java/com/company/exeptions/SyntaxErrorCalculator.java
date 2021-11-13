package com.company.exeptions;

public class SyntaxErrorCalculator extends Exception{
    public SyntaxErrorCalculator(String message) {
        super(message);
    }
}
