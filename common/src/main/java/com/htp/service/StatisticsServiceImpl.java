package com.htp.service;

import com.htp.aspect.DaoInvokeCountAspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService{

	private final DaoInvokeCountAspect invokeCountAspect;

	public StatisticsServiceImpl(@Qualifier("daoInvokeStatisticsAspect") DaoInvokeCountAspect daoInvokeCountAspect) {
		invokeCountAspect = daoInvokeCountAspect;
	}
	@Override
	public Map<String, Integer> getStatistics() {
		return invokeCountAspect.getMethodInvocationsCounter();
	}
}
