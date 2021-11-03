package com.company;

import java.util.Optional;

public enum BinaryOperator implements Operator{
    ARITHMETIC_MULTIPLICATION("*"),
    ARITHMETIC_DIVISION("/"),
    ARITHMETIC_ADDITION("+"),
    ARITHMETIC_SUBTRACTION("-");

    private final String code;

    BinaryOperator(String s) {
        this.code = s;
    }

    public static Optional<Operator> of(final String code) {
        for (final BinaryOperator operator : values()) {
            if (operator.getCode().equals(code)) {
                return Optional.of(operator);
            }
        }
        return Optional.empty();
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
