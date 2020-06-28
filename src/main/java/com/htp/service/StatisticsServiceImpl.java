package com.htp.service;

import com.htp.aspect.DaoInvokeCountAspect;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService{
	@Override
	public Map<String, Integer> getStatistics() {
		return DaoInvokeCountAspect.getMethodInvocationsCounter();
	}
}
