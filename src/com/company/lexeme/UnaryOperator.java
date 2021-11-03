package com.company.lexeme;

import java.util.Optional;

public enum UnaryOperator implements Operator{
    ARITHMETIC_UNARY_MINUS("-");

    private final String code;

    UnaryOperator(final String code) {
        this.code = code;
    }

    public static Optional<UnaryOperator> of(final String code) {
        for (final UnaryOperator operator : values()) {
            if (operator.getCode().equals(code)) {
                return Optional.of(operator);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getType() {
        return "unary";
    }

    @Override
    public String toString() {
        return code;
    }
}
