//JD18 Hibernate

package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.service.hibernate.HibernateUserService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/hibernate/users")
@Validated
public class HibernateUserController {

	private final HibernateUserService hibernateUserService;

	public HibernateUserController(HibernateUserService hibernateUserService) {
		this.hibernateUserService = hibernateUserService;
	}

	@ApiOperation(value = "Finding all users")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Users are founded"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "start page", example = "0", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "page", value = "page size", example = "10", required = true, dataType = "int", paramType = "path")
	})
	@GetMapping("/page={page}/size={size}")
	public ResponseEntity<List<HibernateUser>> findAll(@PathVariable @Min(0) Integer page,
	                                                   @PathVariable @Min(1) @Max(100) Integer size) {
		return new ResponseEntity<>(hibernateUserService.findAll(page, size), HttpStatus.OK);
	}

	@ApiOperation(value = "Finding user by id")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Successful search"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "User database id", example = "1", required = true, dataType = "long", paramType = "path")
	})
	@GetMapping("/{id}")
	public ResponseEntity<HibernateUser> getUserById(@PathVariable Long id) {
		return new ResponseEntity<>(hibernateUserService.findById(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Create user")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@PostMapping
	public HibernateUser create(@Valid @RequestBody UserCreateRequest createRequest) {

		HibernateUser user = new HibernateUser();
		user.setName(createRequest.getName());
		user.setLogin(createRequest.getLogin());
		user.setBirthDate(createRequest.getBirthDate());
		user.setPassword(createRequest.getPassword());

		return hibernateUserService.save(user);
	}

}
