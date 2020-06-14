package com.htp.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class DaoTimeExecAspect {
	private static final Logger log = Logger.getLogger(DaoTimeExecAspect.class);

	//Указываем, куда будет внедряться логика. В данном случае в DAO-методы
	@Pointcut("execution(* com.htp.dao.implementation.*.*(..))")
	public void aroundDaoImplPointcut() { /* Pointcut methods should have empty body! */ }

	@Around("aroundDaoImplPointcut()")
	public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		final String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		final long startTime = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		final long stopTime = System.currentTimeMillis();
		log.info("execution time (millis) " + methodName + " :" + (stopTime - startTime));
		return proceed;
	}
}
