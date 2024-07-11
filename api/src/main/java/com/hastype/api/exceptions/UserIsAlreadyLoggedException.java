package com.hastype.api.exceptions;

public class UserIsAlreadyLoggedException extends RuntimeException{

    public UserIsAlreadyLoggedException() {
        super("Este usuário já está logado!");
    }
}
