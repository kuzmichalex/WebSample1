package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.controller.request.UserUpdateRequest;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.security.util.PrincipalUtil;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class JdbcUserController {
	private final UserService userService;

	public JdbcUserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Find all users") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok. All users found"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Something's wrong. Users ran away") //Сообщение, что всё плохо
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping
	public ResponseEntity<List<com.htp.domain.User>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Finding user by id")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Server error :(")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "User database id", example = "1", required = true, dataType = "long", paramType = "path")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping("/{id}")
	public com.htp.domain.User findById(@PathVariable("id") Long userId) {
		final Optional<User> byId = userService.findById(userId);
		if (byId.isEmpty()) throw new EntityNotFoundException("User id = '" + userId + "' not found");
		return byId.get();
	}

	@ApiOperation(value = "Search user by login")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading user"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "login", value = "Search query user login", example = "admin", required = true, dataType = "string", paramType = "query")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping("/search")
	public com.htp.domain.User searchUser(@RequestParam("login") String login) {
		//modelMap.addAttribute("users", userService.search(login));]
		final Optional<com.htp.domain.User> byLogin = userService.findByLogin(login);
		return byLogin.orElse(null);
	}

	@ApiOperation(value = "Create user")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	public com.htp.domain.User create(@Valid @RequestBody UserCreateRequest createRequest, Principal principal) {

		log.info("User " + PrincipalUtil.getUserLogin(principal) + " create " + createRequest.getLogin());
		com.htp.domain.User user = new com.htp.domain.User();
		user.setLogin(createRequest.getLogin());
		user.setName(createRequest.getName());
		user.setBirthDate(createRequest.getBirthDate());
		user.setPassword(createRequest.getPassword());
		return userService.save(user);
	}

	@ApiOperation(value = "Update user")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful updating user"),
			@ApiResponse(code = 422, message = "Failed user updating properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@PostMapping("/update")
	public User update(@Valid @RequestBody UserUpdateRequest updateRequest) {
		User user = new User();
		user.setId(updateRequest.getId());
		user.setLogin(updateRequest.getLogin());
		user.setName(updateRequest.getName());
		user.setBirthDate(updateRequest.getBirthDate());
		user.setPassword(updateRequest.getPassword());
		return userService.update(user);
	}

	@ApiOperation(value = "Find user roles") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok. All users roles found"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Something's wrong. User roles ran away") //Сообщение, что всё плохо
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "Search user roles", example = "1", required = true, dataType = "long", paramType = "query")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> findUserRoles(@RequestParam("userId") Long userId) {
		return new ResponseEntity<>(userService.getUserRoles(userId), HttpStatus.OK);
	}

}
