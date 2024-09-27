package com.neoteric.fullstackdemo_31_08_2024.exception;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(String message){
        super(message);
    }
}
