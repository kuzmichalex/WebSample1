package com.htp.service;

import java.util.Map;

public interface StatisticsService {
	Map<String, Integer> getStatistics();

	void clear();
}
