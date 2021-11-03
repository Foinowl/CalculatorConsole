package com.company;

import java.util.Optional;

public enum Brackets implements Lexeme{
    OPEN_BRACKET(true),
    CLOSE_BRACKET(false);

    private final boolean open;

    Brackets(boolean open) {
        this.open = open;
    }

    public static Optional<Lexeme> of(final String value) {
        if ("(".equals(value)) {
            return Optional.of(OPEN_BRACKET);
        } else if (")".equals(value)) {
            return Optional.of(CLOSE_BRACKET);
        } else {
            return Optional.empty();
        }
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public String toString() {
        return open ? "(" : ")";
    }
}
