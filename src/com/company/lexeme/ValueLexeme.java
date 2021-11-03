package com.company.lexeme;

import com.company.exeptions.IllegalOperationException;

import java.util.Set;

public class ValueLexeme implements ConstantValueLexeme {
    private static final Set<Class<?>> AVAILABLE_CLASS = Set.of(Integer.class, Double.class);


    private Double value;

    private ValueLexeme(Double value) {
        if (value == null) {
            throw new IllegalOperationException("Null can't be participate in the expression");
        } else if (!AVAILABLE_CLASS.contains(value.getClass())) {
            throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getName());
        }
        this.value = value;
    }

    private static ValueLexeme of(final Double value) {
        return new ValueLexeme(value);
    }

    public static ValueLexeme build(String value) {
        try{
            return ValueLexeme.of(Double.parseDouble(value));
        } catch (NumberFormatException doubleException) {
            throw new IllegalOperationException("Number is not supported");
        }
    }

    public static ValueLexeme build(double value) {
        try{
            return ValueLexeme.of(value);
        } catch (NumberFormatException doubleException) {
            throw new IllegalOperationException("Number is not supported");
        }
    }

    @Override
    public Double getValue() {
        return value;
    }
}
