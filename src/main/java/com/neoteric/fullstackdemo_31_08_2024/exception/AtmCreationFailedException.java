package com.neoteric.fullstackdemo_31_08_2024.exception;

public class AtmCreationFailedException extends Exception{

    public String message;
    public AtmCreationFailedException(String message) {
        this.message = message;
    }
}
