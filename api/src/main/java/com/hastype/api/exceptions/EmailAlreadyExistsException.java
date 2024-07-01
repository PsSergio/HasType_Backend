package com.hastype.api.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException() {
        super("Este email já existe!");
    }
}
