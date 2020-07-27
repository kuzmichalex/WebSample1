package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.dao.springdata.UserRepository;
import com.htp.domain.hibernate.HibernateUser;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.sql.SQLException;


@Slf4j
@RestController
@RequestMapping("/springData/users")
public class SpringDataUserController {
	private final UserRepository userRepository;
	private final ConversionService conversionService;

	public SpringDataUserController(UserRepository userRepository, ConversionService conversionService) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}

	@ApiOperation(value = "Search with pagination")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading users"),
			@ApiResponse(code = 400, message = "Invalid sort param"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "Page number", example = "0", defaultValue = "0", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "Items per page", example = "3", defaultValue = "3", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "Field to sort", example = "id", defaultValue = "id", dataType = "string", paramType = "query")
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

	@ApiOperation(value = "Create user")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@PostMapping
	public HibernateUser create(@Valid @RequestBody UserCreateRequest createRequest) throws SQLException {

		HibernateUser user = conversionService.convert(createRequest, HibernateUser.class);

		assert user != null;
		return userRepository.save(user);
	}
}


