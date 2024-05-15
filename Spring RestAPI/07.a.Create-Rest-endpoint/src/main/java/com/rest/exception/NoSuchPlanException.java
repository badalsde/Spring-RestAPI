package com.rest.exception;

public class NoSuchPlanException extends Exception{
    public NoSuchPlanException(){ }
    public NoSuchPlanException(String msg){
        super(msg);
    }
}
