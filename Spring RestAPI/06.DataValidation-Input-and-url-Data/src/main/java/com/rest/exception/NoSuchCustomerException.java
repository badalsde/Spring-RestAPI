package com.rest.exception;

import java.io.Serial;

public class NoSuchCustomerException  extends Exception {
    @Serial
    private static final long serialVersionUID =1L;
    public NoSuchCustomerException(){

    }
    public NoSuchCustomerException(String msg){
        super(msg);
    }
}
