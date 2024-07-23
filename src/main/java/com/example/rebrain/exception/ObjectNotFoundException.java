package com.example.rebrain.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String reason) {
        super(reason);
    }
}
