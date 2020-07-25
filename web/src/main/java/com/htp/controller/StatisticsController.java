package com.htp.controller;

import com.htp.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	private final StatisticsService statisticsService;

	public StatisticsController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}


	@GetMapping
	public ResponseEntity<Map<String, Integer>> statistics() {
		return new ResponseEntity<>(statisticsService.getStatistics(), HttpStatus.OK);
	}
}