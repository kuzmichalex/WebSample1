package com.htp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("daoInvokeStatisticsAspect")
@Aspect
public class DaoInvokeCountAspect {
	private static final ConcurrentHashMap<String, Integer> methodInvocationsCounter = new ConcurrentHashMap<>();

	public static Map<String, Integer> getMethodInvocationsCounter() {
		return new HashMap<>(methodInvocationsCounter);
	}

	//Указываем, куда будет внедряться логика. В данном случае в DAO-методы
	@Pointcut("execution(* com.htp.dao.implementation.*.*(..))")
	public void beforeDaoPointcut() { /* Pointcut methods should have empty body! */ }

	@Before("beforeDaoPointcut()")
	public void logBefore(JoinPoint joinPoint) {
		final String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		methodInvocationsCounter.merge(methodName, 1, Integer::sum);
	}
}

