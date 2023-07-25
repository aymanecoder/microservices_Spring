package com.virus7x.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not Found in Server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
