package org.mindswap.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.mindswap.controller.*.*(..))")
    public void logBeforeAdd(JoinPoint joinPoint){
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
    }

    @AfterReturning(pointcut = "execution(* org.mindswap.controller.*.*(..))", returning = "result")
    public void logAfterHelloList(JoinPoint joinPoint, Object result) {
        logger.info("After " + joinPoint.getSignature().getName() + " method call");
        logger.info("Response: " + result);
    }


    @AfterThrowing(pointcut = "execution(* org.mindswap.controller.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in " + joinPoint.getSignature().getName() + " method call");
        logger.error("Exception: " + exception);
    }

    @Before("execution(* org.mindswap.security.config.LogoutService.*(..))")
    public void logBeforeLogout(JoinPoint joinPoint){
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
    }
}
