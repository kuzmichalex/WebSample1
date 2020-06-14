package com.htp.controller;

import com.htp.domain.Role;
import com.htp.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {
	private final RoleService roleService;

	//RoleService заавтовайрится, тк класс UserServiceImpl аннотирован @Service
	public RoleController(RoleService userService) {
		this.roleService = userService;
	}

	@GetMapping
	public String findAll(ModelMap modelMap){
		modelMap.addAttribute("roles", roleService.findAll());
		return "/roles/roles";
	}


	@GetMapping("/create")
	public String searchUser(@RequestParam("roleName") String roleName, ModelMap modelMap) {
		Role role = new Role();
		role.setRoleName(roleName);
		roleService.save(role);
		modelMap.addAttribute("roles", roleService.findAll());
		return "/roles/roles";

	}

}
