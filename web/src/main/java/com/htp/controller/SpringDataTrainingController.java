package com.htp.controller;

import com.htp.dao.springdata.TrainingRepository;
import com.htp.domain.hibernate.HibernateTraining;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/springData/trainings")
public class SpringDataTrainingController {
	private final TrainingRepository trainingRepository;

	public SpringDataTrainingController(TrainingRepository trainingRepository) {
		this.trainingRepository = trainingRepository;
	}


	@ApiOperation(value = "Finding all trainings")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@GetMapping
	public ResponseEntity<List<HibernateTraining>> findAll() {
		return new ResponseEntity<>(trainingRepository.findAll(), HttpStatus.OK);
	}

}
