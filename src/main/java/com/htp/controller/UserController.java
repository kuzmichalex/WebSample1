package com.htp.controller;

import com.htp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	//userService заавтовайрится, тк класс UserServiceImpl аннотирован @Service
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String findAll(ModelMap modelMap) {
		System.out.println("UserController");
		modelMap.addAttribute("users", userService.findAll());
		return "users/users";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
		System.out.println("UserController /{id} " + userId);
		modelMap.addAttribute("user", userService.findOne(userId));
		return "users/user";
	}

	@GetMapping("/search")
	public String searchUser(@RequestParam("query") String query, ModelMap modelMap) {
		System.out.println("UserController /search");
		modelMap.addAttribute("users", userService.search(query));
		return "users/users";
	}
}
