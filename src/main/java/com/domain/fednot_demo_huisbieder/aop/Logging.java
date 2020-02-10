package com.domain.fednot_demo_huisbieder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
class Logging {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterThrowing(pointcut = "execution(* com.domain.fednot_demo_huisbieder.aop.PointcutExpressions.services())", throwing = "ex")
    void schijfException(JoinPoint joinPoint, Throwable ex) {
        StringBuilder builder = new StringBuilder("Tijdstip\t").append(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }

        builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
        Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));
        logger.error(builder.toString());
    }
}