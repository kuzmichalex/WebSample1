package com.htp.controller;

import com.htp.dao.hibernate.HibernateTrainingRepository;
import com.htp.domain.hibernate.HibernateTraining;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hibernate/trainings")
@Validated
public class HibernateTrainingController {

	private final HibernateTrainingRepository hibernateTrainingRepository;

	public HibernateTrainingController(HibernateTrainingRepository hibernateTrainingRepository) {
		this.hibernateTrainingRepository = hibernateTrainingRepository;
	}

	@ApiOperation(value = "Finding trainings using criteria builder")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Name", value = "Training name", example = "%", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "Feature", value = "Training feature", example = "%", dataType = "String", allowMultiple = true, paramType = "query")
	})
	@GetMapping
	public ResponseEntity<List<HibernateTraining>> findByCriteria(
			@RequestParam(value = "Name") String name,
			@RequestParam(value = "Feature") String[] feature) {
		return new ResponseEntity<>(hibernateTrainingRepository.criteriaFind(Optional.ofNullable(name), Optional.ofNullable(feature)), HttpStatus.OK);
	}
}
