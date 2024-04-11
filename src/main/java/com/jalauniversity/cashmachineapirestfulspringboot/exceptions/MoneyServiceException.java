package com.jalauniversity.cashmachineapirestfulspringboot.exceptions;

public class MoneyServiceException extends RuntimeException {
    public MoneyServiceException(String message){
        super(message);
    }
}
