package com.htp.controller;

import com.htp.dao.springdata.RoleRepository;
import com.htp.domain.hibernate.HibernateRole;
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
@RequestMapping("/springData/roles")
public class SpringDataRoleController {
	private final RoleRepository roleRepository;

	public SpringDataRoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@ApiOperation(value = "Finding all roles")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@GetMapping
	public ResponseEntity<List<HibernateRole>> findAll() {
		return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
	}

}
