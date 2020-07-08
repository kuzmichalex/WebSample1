//JD18 Hibernate

package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.dao.hibernate.HibernateUserDao;
import com.htp.domain.hibernate.HibernateUser;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hibernate/users")
public class HibernateUserController {

	private final HibernateUserDao hibernateUserDao;

	public HibernateUserController(HibernateUserDao hibernateUserDao) {
		this.hibernateUserDao = hibernateUserDao;
	}

	@ApiOperation(value = "Finding all users")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading users"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@GetMapping
	public ResponseEntity<List<HibernateUser>> findAll() {
		return new ResponseEntity<>(hibernateUserDao.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Finding user by id")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading user"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "User database id", example = "1", required = true, dataType = "long", paramType = "path")
	})
	@GetMapping("/{id}")
	public ResponseEntity<HibernateUser> getUserById(@PathVariable Long id) {
		return new ResponseEntity<>(hibernateUserDao.findOne(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Create user")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@PostMapping
	public HibernateUser create(@Valid @RequestBody UserCreateRequest createRequest) {

		HibernateUser user = new HibernateUser();
		user.setName(createRequest.getName());
		user.setLogin(createRequest.getLogin());
		user.setBirthDate(createRequest.getBirthDate());
		user.setPassword(createRequest.getPassword());

		return hibernateUserDao.save(user);
	}

}
