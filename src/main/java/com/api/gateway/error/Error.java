package com.api.gateway.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "RequestId already used")
public class Error extends RuntimeException{
    public Error(String message){
        super((message));

    }
}
