package com.htp.controller;

import com.htp.controller.request.RoleCreateRequest;
import com.htp.controller.request.RoleUpdateRequest;
import com.htp.domain.Role;
import com.htp.service.RoleService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@ApiOperation(value = "Find all roles") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok. All roles found"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Something's wrong. Roles ran away") //Сообщение, что всё плохо
	})
	@GetMapping
	public ResponseEntity<List<Role>> findAll(){
		return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Finding Role by id")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading user"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Role database id", example = "1", required = true, dataType = "long", paramType = "path")
	})
	@GetMapping("/{id}")
	public Role findById(@PathVariable("id") Long userId, ModelMap modelMap) {
		return roleService.findOne(userId);
	}


	@ApiOperation(value = "Search role by name")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading user"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "Search query role name", example = "ROLE_ADMINISTRATOR", required = true, dataType = "string", paramType = "query")
	})
	@GetMapping("/search")
	public Role searchRole(@RequestParam("login") String login, ModelMap modelMap) {
		//modelMap.addAttribute("users", userService.search(login));]
		final Optional<Role> byRoleName = roleService.findByRoleName(login);
		return byRoleName.orElse(null);
	}

	@ApiOperation(value = "Search role by user ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful loading user"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "Search query role user roles", example = "1", required = true, dataType = "long", paramType = "query")
	})
	@GetMapping("/user_roles")
	public List<Role> searchRolesByUserId(@RequestParam("userId") long userId, ModelMap modelMap) {
		return roleService.findRolesByUser(userId);
	}

	@ApiOperation(value = "Create role")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@PutMapping("/create")
	public Role create(@Valid @RequestBody RoleCreateRequest createRequest) {
		Role role = new Role();
		role.setRoleName(createRequest.getRoleName());
		return roleService.save(role);
	}

	@ApiOperation(value = "Update role")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Successful creation user"),
			@ApiResponse(code = 422, message = "Failed user creation properties validation"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	@PutMapping("/update")
	public Role update(@Valid @RequestBody RoleUpdateRequest updateRequest) {
		Role role = new Role();
		role.setId(updateRequest.getId());
		role.setRoleName(updateRequest.getRoleName());
		return roleService.update(role);
	}
}
