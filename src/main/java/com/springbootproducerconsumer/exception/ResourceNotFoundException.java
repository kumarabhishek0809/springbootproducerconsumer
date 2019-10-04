package com.springbootproducerconsumer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Exception e,String customMessage, String... params){
        super(MessageFormat.format(e.getMessage(),customMessage, params));
    }

}
