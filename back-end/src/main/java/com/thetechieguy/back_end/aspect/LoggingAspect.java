package com.thetechieguy.back_end.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Around("execution(* com.thetechieguy.back_end.controller.*.*(..))")
	public Object logControllersMethods(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		log.info("Entering method: {}", methodName);
		long start = System.currentTimeMillis();

		try {
			Object result = pjp.proceed();
			log.info("Exiting method: {} (took {} ms)", methodName, System.currentTimeMillis() - start);
			return result;
		} catch (Exception ex) {
			log.error("Exception in method {}: {}", methodName, ex.getMessage());
			throw ex;
		}
	}
}
