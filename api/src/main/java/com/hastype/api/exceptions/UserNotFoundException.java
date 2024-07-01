package com.hastype.api.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Usuário não encontrado!");
    }

}
