package com.company.exeptions;

public class NotFoundOperationException extends Exception{
    public NotFoundOperationException(final String message) {
        super(message);
    }
}

