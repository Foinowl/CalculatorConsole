package com.company.exeptions;

public class IllegalOperationException extends IllegalArgumentException{
    public IllegalOperationException(String message) {
        super(message);
    }
}
