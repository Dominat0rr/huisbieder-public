package com.domain.fednot_demo_huisbieder.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
class PointcutExpressions {

    @Pointcut("execution(* com.domain.fednot_demo_huisbieder.services.*.*(..))")
    void services() { }
}
