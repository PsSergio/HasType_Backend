package com.hastype.api.exceptions;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
        super("Credenciais incorretas!");
    }
}
