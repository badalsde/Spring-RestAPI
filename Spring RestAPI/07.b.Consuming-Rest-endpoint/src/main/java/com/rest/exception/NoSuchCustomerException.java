package com.rest.exception;

public class NoSuchCustomerException extends Exception {
    public NoSuchCustomerException(){ }
    public NoSuchCustomerException(String msg){
        super(msg);
    }
}
