package com.company.lexeme;

import com.company.lexeme.Lexeme;

public interface Operator extends Lexeme {
    String getType();

    String getCode();
}
