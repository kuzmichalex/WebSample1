package com.htp.controller;

import com.htp.dao.hibernate.HibernateTrainingRepository;
import com.htp.domain.hibernate.HibernateTraining;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hibernate/trainings")
@Validated
public class HibernateTrainingController {

	private final HibernateTrainingRepository hibernateTrainingRepository;

	public HibernateTrainingController(HibernateTrainingRepository hibernateTrainingRepository) {
		this.hibernateTrainingRepository = hibernateTrainingRepository;
	}


	@ApiOperation(value = "Finding trainings CB test")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Server error")
	})
	/*@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "page size", example = "10", required = true, dataType = "int", paramType = "path")
	})*/
	@GetMapping
	public ResponseEntity<List<HibernateTraining>> findByCriteria() {
		return new ResponseEntity<>(hibernateTrainingRepository.criteriaFind(), HttpStatus.OK);
	}
}
