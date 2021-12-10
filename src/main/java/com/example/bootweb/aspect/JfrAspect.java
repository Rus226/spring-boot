package com.example.bootweb.aspect;

import com.example.bootweb.jfr.event.RequestEvent;
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
    public Object jfrProfile (ProceedingJoinPoint thisJoinPoint) throws Throwable {
        final var evt = createEvent(request);
        evt.begin();
        var result = thisJoinPoint.proceed();
        evt.commit();
        return result;
    }

    protected RequestEvent createEvent(HttpServletRequest request) {
        final var evt = new RequestEvent();
        var contentLengthLong = request.getContentLengthLong();
        evt.size = contentLengthLong == -1 ? 0L : contentLengthLong;
        evt.method = request.getMethod();
        evt.uri = request.getRequestURI();

        return evt;
    }
}
