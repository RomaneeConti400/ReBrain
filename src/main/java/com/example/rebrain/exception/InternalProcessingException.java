package com.example.rebrain.exception;

public class InternalProcessingException extends RuntimeException{
    public InternalProcessingException(String reason) {
        super(reason);
    }
}
