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
	private final ConcurrentHashMap<String, Integer> methodInvocationsCounter = new ConcurrentHashMap<>();

	public Map<String, Integer> getMethodInvocationsCounter() {
		return new HashMap<>(this.methodInvocationsCounter);
	}

	//Указываем, куда будет внедряться логика. В данном случае в DAO-методы
	@Pointcut("execution(* com.htp.dao.*.*.*(..))")
	public void beforeDaoPointcut() { /* Pointcut methods should have empty body! */ }

	@Before("beforeDaoPointcut()")
	public void logBefore(JoinPoint joinPoint) {
		final String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		methodInvocationsCounter.merge(methodName, 1, Integer::sum);
	}

	public void clear() {
		methodInvocationsCounter.clear();
	}
}

