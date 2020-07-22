package com.htp.controller;

import com.htp.dao.springdata.UserRepository;
import com.htp.domain.hibernate.HibernateUser;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Slf4j
@RestController
@RequestMapping("/springData/users")
public class SpringDataUserController {
	private final UserRepository userRepository;

	public SpringDataUserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@ApiOperation(value = "Search with pagination")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading users"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "Page number", example = "0", defaultValue = "0", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "Items per page", example = "3", defaultValue = "3", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "Field to sort", example = "0", defaultValue = "id", dataType = "string", paramType = "query")
	})
	@GetMapping
	public ResponseEntity<Page<HibernateUser>> findAllAliveUsers(@ApiIgnore Pageable pageable) {
		Page<HibernateUser> usersPage = userRepository.findAll(pageable);
		return new ResponseEntity<>(usersPage, HttpStatus.OK);
	}

	@ApiOperation(value = "Finding user by login")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful search"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "login", value = "User login", example = "Admin", required = true, dataType = "String", paramType = "path")
	})
	@GetMapping("/{login}")
	public ResponseEntity<HibernateUser> getUserById(@PathVariable String login) {
		return new ResponseEntity<>(userRepository.findByLoginEquals(login), HttpStatus.OK);
	}


}
