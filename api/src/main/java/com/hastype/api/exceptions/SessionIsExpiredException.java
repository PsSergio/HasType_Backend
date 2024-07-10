package com.hastype.api.exceptions;

public class SessionIsExpiredException extends RuntimeException{

    public SessionIsExpiredException() {
        super("Sessão está expirada!");
    }
}
