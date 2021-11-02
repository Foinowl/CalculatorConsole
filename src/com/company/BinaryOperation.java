package com.company;

public enum BinaryOperation implements Operator{
    ARITHMETIC_MULTIPLICATION("*"),
    ARITHMETIC_DIVISION("/"),
    ARITHMETIC_ADDITION("+"),
    ARITHMETIC_SUBTRACTION("-");

    private final String code;

    BinaryOperation(String s) {
        this.code = s;
    }

    @Override
    public String getType() {
        return "binary";
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
