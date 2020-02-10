package com.domain.fednot_demo_huisbieder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
class Performance {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.domain.fednot_demo_huisbieder.aop.PointcutExpressions.services())")
    Object schrijfPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long voor = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long duurtijd = System.nanoTime() - voor;
            logger.info("{} duurde {} nanoseconden", joinPoint.getSignature().toLongString(), duurtijd);
        }
    }
}
