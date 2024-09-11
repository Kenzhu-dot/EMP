package com.situ.ems_spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceLogAspect {
    Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.situ.springboot.service.impl.*.*(..))")
    public Object timeCheckLog(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("你的{}.{}代码启动了！！！！！！！！！！！",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        if (totalTime > 3000) {
            logger.error("========垃圾代码趁早玩完=========");
        }
        else if (totalTime > 2000) {
            logger.warn("========煞笔代码赶紧修改=========");
        }
        else {
            logger.info("========代码还行人不咋地=========");
        }
        return result;
    }
}
