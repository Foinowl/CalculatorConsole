package com.company.lexeme;

import com.company.exeptions.IllegalOperationException;

public class ValueLexeme implements ConstantValueLexeme {
    private final Double value;

    private ValueLexeme(Double value) {
        if (value == null) {
            throw new IllegalOperationException("Null не может участвовать в выражение");
        }

        this.value = value;
    }

    private static ValueLexeme of(Double value) {
        return new ValueLexeme(value);
    }

    public static ValueLexeme build(String value) {
        try {
            return ValueLexeme.of(Double.parseDouble(value));
        } catch (NumberFormatException doubleException) {
            throw new IllegalOperationException("Число не поддерживается");
        }
    }

    public static ValueLexeme build(double value) {
        try {
            return ValueLexeme.of(value);
        } catch (NumberFormatException doubleException) {
            throw new IllegalOperationException("Число не поддерживается");
        }
    }

    @Override
    public Double getValue() {
        return value;
    }
}
