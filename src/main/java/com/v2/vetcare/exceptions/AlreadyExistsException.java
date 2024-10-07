package com.v2.vetcare.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message){
        super(message);
    }
}
