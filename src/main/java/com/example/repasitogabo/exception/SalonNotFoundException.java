package com.example.repasitogabo.exception;

public class SalonNotFoundException extends RuntimeException{
    public SalonNotFoundException(String message){
        super(message);
    }
}
