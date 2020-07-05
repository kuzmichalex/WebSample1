//security
package com.htp.controller.auth;

import com.htp.controller.request.AuthRequest;
import com.htp.controller.request.AuthResponse;
import com.htp.security.util.TokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	/*Наш TokenUtils - создание, разбор токена и тд*/
	private TokenUtils tokenUtils;

	/* Аутентификацией будет заниматься AuthenticationManager - наш или дефолтовый */
	private AuthenticationManager authenticationManager;

	/* Наша имплементация UserDetailService */
	private UserDetailsService userDetailsService;

	public AuthController(TokenUtils tokenUtils, AuthenticationManager authenticationManager,
	                      @Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService) {
		this.tokenUtils = tokenUtils;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	/* Аннотации @Valid и @RequestBody означают, что
		на входе мы ожидаем некий объект, который будем валидировать и преобразовывать в authRequest*/
	@ApiOperation(value = "login")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful login"),
			@ApiResponse(code = 500, message = "Server error, something wrong")
	})
	//@ApiImplicitParams не нужен; параметры передаются в body
	@GetMapping
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
		/*Валидация переданных параметров будет произведена самим спрингом*/

		/*Если этот метод проборосит AuthenticationException, его отлоивит DefaultExceptionHandler */
		final Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequest.getUserName(),
						authRequest.getPassword())
		);

		/*Всё хорошо и поэтому мы сохраняем информации о залогинивании в секьюрити контексте*/
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		return new ResponseEntity<>(
				AuthResponse.builder()
							.login(authRequest.getUserName())
							.jwtToken(tokenUtils.generateToken(userDetailsService.loadUserByUsername(authRequest.getUserName())))
							.build()
				, HttpStatus.OK);
	}

}