package com.zzuag.common_module.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationAspect {

    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(com.zzuag.common_module.aop.annotation.PassportAuth)")
    public Object authenticate(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("authenticate");
        return joinPoint.proceed();
    }
}
