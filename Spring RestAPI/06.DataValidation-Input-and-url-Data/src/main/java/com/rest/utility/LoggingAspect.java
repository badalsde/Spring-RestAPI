package com.rest.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.rest.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(Exception exception) throws Exception {
        logger.error(exception.getMessage(), exception);
    }

}
