package com.zzuag.common_module.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzuag.common_module.passport.Passport;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationAspect {

    private final HttpServletRequest httpServletRequest;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final ThreadLocal<Passport> passportThreadLocal = new ThreadLocal<>();


    @Around("@annotation(com.zzuag.common_module.aop.annotation.PassportAuth)")
    public Object authenticate(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("========Execute AuthenticationAspect==========");
        try {
            final Optional<String> token = Optional.ofNullable(httpServletRequest.getHeader("X-Authorization"));
            if (token.isPresent()) {
                Passport passport = objectMapper.readValue(token.get(), Passport.class);
                passportThreadLocal.set(passport);
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Authentication failed", e);
            throw new IllegalArgumentException("Authentication failed");
        } finally {
            passportThreadLocal.remove();
        }
    }

    public static Passport getPassport() {
        Passport passport = passportThreadLocal.get();
        if (passport == null) {
            throw new IllegalStateException("No passport found in thread-local storage");
        }
        return passport;
    }
}
