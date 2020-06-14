package com.htp.controller;

import com.htp.aspect.DaoInvokeCountAspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

	@GetMapping
	public String statistics(ModelMap modelMap) {
		final Map<String, Integer> methodInvocationsCounter = DaoInvokeCountAspect.getMethodInvocationsCounter();
		StringBuilder statistics = new StringBuilder();
		for (Map.Entry<String, Integer> stringIntegerEntry : methodInvocationsCounter.entrySet()) {
			statistics.append(stringIntegerEntry.getKey()).append(" : ").append(stringIntegerEntry.getValue()).append("<br>");
			modelMap.addAttribute("statisticsAttribute", statistics.toString());
		}
		return "statistics";
	}
}