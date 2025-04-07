package com.recycle.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
public class CacheLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheLoggingAspect.class);

    @Around("@annotation(com.recycle.common.annotation.LoggingCache)")
    public Object logCacheOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if(result instanceof Optional<?> optionalResult) {
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String argsStr = Arrays.stream(args)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            if (optionalResult.isPresent()) {
                log.info("[CACHE HIT] Cache hit for method: {} with args: {}", methodName, argsStr);
            } else {
                log.info("[CACHE MISS] Cache miss for method: {} with args: {}", methodName, argsStr);
            }
        }

        return result;
    }
}
