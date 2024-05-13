package com.rest.exception;

import java.io.Serial;

public class NoSuchCallDetailsException extends Exception {
    @Serial
    private static final long serialVersionUID =1L;
    public NoSuchCallDetailsException(){
    }
    public NoSuchCallDetailsException(String msg){
        super(msg);
    }
}
