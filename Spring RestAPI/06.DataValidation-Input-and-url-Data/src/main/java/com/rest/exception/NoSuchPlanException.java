package com.rest.exception;

import java.io.Serial;

public class NoSuchPlanException extends Exception{
    @Serial
    private static final long serialVersionUID =1L;
    public NoSuchPlanException(){ }
    public NoSuchPlanException(String msg){
        super(msg);
    }

}
