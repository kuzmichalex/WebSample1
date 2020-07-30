package com.htp.controller;

import com.htp.controller.request.GroupCreateRequest;
import com.htp.domain.hibernate.HibernateGroup;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.security.util.PrincipalUtil;
import com.htp.service.springdata.SpringDataGroupService;
import com.htp.service.springdata.SpringDataUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/springData/groups")
public class SpringDataGroupController {
	private final SpringDataGroupService groupService;
	private final SpringDataUserService userService;

	public SpringDataGroupController(SpringDataGroupService groupService, SpringDataUserService userService) {
		this.groupService = groupService;
		this.userService = userService;
	}

	@ApiOperation(value = "Search")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@GetMapping
	public ResponseEntity<List<HibernateGroup>> findAll() {
		final List<HibernateGroup> groups = groupService.findAll();
		return new ResponseEntity<>(groups, HttpStatus.OK);
	}

	/**
	 * Create group
	 */
	@ApiOperation(value = "Create group")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Success"),
			@ApiResponse(code = 422, message = "Failed group creation properties validation"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	public HibernateGroup create(@Valid @RequestBody GroupCreateRequest createRequest, Principal principal) {
		final String userLogin = PrincipalUtil.getUserLogin(principal);
		final Optional<HibernateUser> user = userService.findByLogin(userLogin);
		if (user.isEmpty()) throw new EntityNotFoundException("You not user. What are you!?");
		HibernateGroup group = new HibernateGroup();
		group.setName(createRequest.getGroupName());
		group.setDescription(createRequest.getDescription());
		group.setDeleted(false);
		group.setUserFounder(user.get());
		group.setDateFoundation(Date.valueOf(LocalDate.now()));
		return groupService.save(group);
	}

}
