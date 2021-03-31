package org.judy.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	@Around("execution(* org.judy..*.*Service*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {

		long start = System.currentTimeMillis();

		log.info(pjp.getSignature());
		log.info(pjp.getArgs());
		log.info(pjp.getThis());
		log.info(pjp.getClass());
		log.info("Target: " + pjp.getTarget());
		log.info("Param: " + Arrays.toString(pjp.getArgs()));

		Object result = null;

		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		log.info("Time: " + (end - start));

		return result;

	}

	@Before("execution(* org.judy..*.*Controller*.*(..))")
	public void aopController() {
		log.info("Controller------------------------------");
	}

}
