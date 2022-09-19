package com.darkneees.discordbackend.exception;

public class NoEntityException extends Exception {

    public NoEntityException(String message) {
        super("Don't find entity with guild id: " + message);
    }
}
