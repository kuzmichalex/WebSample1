package com.htp.controller;

import com.htp.controller.response.MyPageInfo;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.security.util.PrincipalUtil;
import com.htp.service.MyPageService;
import com.htp.service.springdata.SpringDataUserService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/myPage")
public class MyPageController {
	private final SpringDataUserService userService;
	private final MyPageService myPageService;

	public MyPageController(SpringDataUserService userService, MyPageService myPageService) {
		this.userService = userService;
		this.myPageService = myPageService;
	}

	private HibernateUser getUserFromPrincipal(Principal principal) {
		final String userLogin = PrincipalUtil.getUserLogin(principal);
		final Optional<HibernateUser> user = userService.findByLogin(userLogin);
		if (user.isEmpty()) throw new EntityNotFoundException("User that you logged-in does not exists");
		return user.get();
	}


	@ApiOperation(value = "My Page") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Server upal") //Сообщение, что всё плохо
	})

	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping
	public ResponseEntity<MyPageInfo> myPage(@ApiIgnore Principal principal) {
		final HibernateUser user = getUserFromPrincipal(principal);
		return myPageService.getMyPage(user);
	}

	@ApiOperation(value = "Enter group") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Server upal") //Сообщение, что всё плохо
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "group", value = "group ID", example = "1", defaultValue = "1", dataType = "int", paramType = "query"),
	})
	@PutMapping("/EnterGroup")
	public ResponseEntity<MyPageInfo> enterGroup(@ApiIgnore Principal principal, Long group) {
		final HibernateUser user = getUserFromPrincipal(principal);
		myPageService.enterGroup(user, group);
		return myPageService.getMyPage(user);
	}

	@ApiOperation(value = "Leave group") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Server upal") //Сообщение, что всё плохо
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "group", value = "group ID", example = "1", defaultValue = "1", dataType = "int", paramType = "query"),
	})
	@PutMapping("/LeaveGroup")
	public ResponseEntity<MyPageInfo> leaveGroup(@ApiIgnore Principal principal, Long group) {
		final HibernateUser user = getUserFromPrincipal(principal);
		myPageService.leaveGroup(user, group);
		return myPageService.getMyPage(user);
	}

	@ApiOperation(value = "Add training to my plans") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Server upal") //Сообщение, что всё плохо
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "training", value = "training ID", example = "1", defaultValue = "1", dataType = "int", paramType = "query"),
	})
	@PutMapping("/addToActivity")
	public ResponseEntity<MyPageInfo> addToActivity(@ApiIgnore Principal principal, Long training) {
		final HibernateUser user = getUserFromPrincipal(principal);
		myPageService.addToActivity(user, training);
		return myPageService.getMyPage(user);
	}

}
