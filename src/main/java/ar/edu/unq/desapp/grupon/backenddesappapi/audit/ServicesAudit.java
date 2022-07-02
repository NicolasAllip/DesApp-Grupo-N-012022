package ar.edu.unq.desapp.grupon.backenddesappapi.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class ServicesAudit {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    Timestamp timestamp_ini = new Timestamp(System.currentTimeMillis());

    @Before("execution(* ar.edu.unq.desapp.grupon.backenddesappapi.service.*.*(..))")
    public void logMethodCallBefore(JoinPoint joinPoint){
        Timestamp timestamp_before = new Timestamp(System.currentTimeMillis());
        List<Object> arguments = Arrays.asList(joinPoint.getArgs());
        logger.info("Before: ");
        logger.info("Method: " + joinPoint.getSignature().toShortString() + ", " + " Arguments: " + arguments.toString() + ", " + "Timestamp: " + timestamp_before);
    }

    @After("execution(* ar.edu.unq.desapp.grupon.backenddesappapi.service.*.*(..))")
    public void logMethodCallAfter(JoinPoint joinPoint){
        Timestamp timestamp_after = new Timestamp(System.currentTimeMillis());
        List<Object> arguments = Arrays.asList(joinPoint.getArgs());
        logger.info("After: ");
        logger.info("Method: " + joinPoint.getSignature().toShortString() + ", " + " Arguments: " + arguments.toString() + ", " + "Timestamp: " + timestamp_after);
    }

}