package com.example.demo;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class LogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.example.demo.controller..*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("*****before advice start*****");
        logger.info("do before " + joinPoint.getSignature().getName());
//        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println("*****before advice end*****");

    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("-----after advice start-----");
        logger.info("do after " + joinPoint.getSignature().getName());
        System.out.println("-----after advice end-----");

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====Around advice starts=====");

        long startTime = System.currentTimeMillis();

        // 呼叫proceed() 方法開始執行原方法
        Object result = joinPoint.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        logger.info(joinPoint.getSignature().getName() + " Time spent: " + spentTime);

        System.out.println("=====Around advice ends=====");

        return result;
    }


	@AfterReturning(pointcut = "pointcut()", returning = "result")
    public void logReturnResponse(Object result) { // 紀錄 Response
        System.out.println("=====after returning advice starts=====");
        if (result != null) {
            System.out.println(result.toString().toUpperCase());
        }
        System.out.println("=====after returning advice ends=====");
    }
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("=====after throwing advice starts=====");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(throwable);
        System.out.println("=====after throwing advice ends=====");
    }
}
