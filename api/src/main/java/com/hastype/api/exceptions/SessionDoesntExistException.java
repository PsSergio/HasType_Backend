package com.hastype.api.exceptions;

public class SessionDoesntExistException extends RuntimeException{

    public SessionDoesntExistException() {
        super("Sessão não encontrada!");
    }
}
