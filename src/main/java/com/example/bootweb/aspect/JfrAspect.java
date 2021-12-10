package com.example.bootweb.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
public class JfrAspect {

    private final HttpServletRequest request;

    @Pointcut("@annotation(com.example.bootweb.annotation.JfrProfile)")
    public void jfrProfileAnnotationMethods() {
    }

    @Around("jfrProfileAnnotationMethods()")
    public Object jfrProfile (ProceedingJoinPoint thisJoinPoint) throws Throwable
    {
        String method = request.getMethod();
        String requestURL = request.getRequestURI();
        long contentLengthLong = request.getContentLengthLong();

        //logic before

        Object result = thisJoinPoint.proceed();

        //logic after


        return result;
    }
}
