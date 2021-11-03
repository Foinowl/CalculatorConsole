package com.company.exeptions;

import static java.util.Objects.requireNonNull;

public class NotFoundOperationException extends RuntimeException{
    public NotFoundOperationException(final String message) {
        super(requireNonNull(message));
    }
}

