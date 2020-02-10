package com.domain.fednot_demo_huisbieder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Aspect
@Component
@Order(1)
class Auditing {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(pointcut = "execution(* com.domain.fednot_demo_huisbieder.aop.PointcutExpressions.services())", returning = "returnValue")
    void schrijfAudit(JoinPoint joinPoint, Object returnValue) {
        StringBuilder builder = new StringBuilder("Tijdstip\t").append(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }

        builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
        Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));

        if (returnValue != null) {
            builder.append("\nReturn\t\t");
            if (returnValue instanceof Collection) {
                builder.append(((Collection<?>) returnValue).size()).append(" objects");
            }
            else {
                builder.append(returnValue.toString());
            }
        }

        logger.info(builder.toString());
    }
}
