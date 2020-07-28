package com.htp.controller;

import com.htp.controller.response.MyPageInfo;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.security.util.PrincipalUtil;
import com.htp.service.MyPageService;
import com.htp.service.springdata.SpringDataUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@ApiOperation(value = "My Page") //Наименование в документации
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),              //Сообщение об успешном поиске
			@ApiResponse(code = 500, message = "Server upal") //Сообщение, что всё плохо
	})

	@ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
	@GetMapping
	public ResponseEntity<MyPageInfo> myPage(@ApiIgnore Principal principal) {
		final String userLogin = PrincipalUtil.getUserLogin(principal);
		final Optional<HibernateUser> user = userService.findByLogin(userLogin);
		if (user.isEmpty()) throw new EntityNotFoundException("Wow! Something is wrong!");
		return myPageService.getMyPage(user.get());
	}

}
