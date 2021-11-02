package com.company;

public enum Brackets implements Lexeme{
    OPEN_BRACKET(true),
    CLOSE_BRACKET(false);

    private final boolean open;

    Brackets(boolean open) {
        this.open = open;
    }
}
