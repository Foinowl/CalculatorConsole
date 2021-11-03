package com.company.lexeme;

import com.company.exeptions.IllegalOperationException;

import java.util.Set;

public class ValueLexeme implements ConstantValueLexeme {
    private static final Set<Class<?>> AVAILABLE_CLASS = Set.of(Integer.class, Double.class);


    private Object value;

    private ValueLexeme(Object value) {
        if (value == null) {
            throw new IllegalOperationException("Null can't be participate in the expression");
        } else if (!AVAILABLE_CLASS.contains(value.getClass())) {
            throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getName());
        }
        this.value = value;
    }

    private static ValueLexeme of(final Object value) {
        return new ValueLexeme(value);
    }

    public static ValueLexeme build(String value) {
        try{
            return ValueLexeme.of(Integer.parseInt(value));
        } catch (NumberFormatException integerException) {
            try {
                return ValueLexeme.of(Double.parseDouble(value));
            } catch (NumberFormatException doubleException) {
                return ValueLexeme.of(value);
            }

        }
    }

    @Override
    public Object getValue() {
        return null;
    }
}
