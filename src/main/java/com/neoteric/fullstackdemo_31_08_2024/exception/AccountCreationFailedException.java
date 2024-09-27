package com.neoteric.fullstackdemo_31_08_2024.exception;


public class AccountCreationFailedException extends Exception {

public String message;

public AccountCreationFailedException(String msg){
    this.message = msg;
     }
}
