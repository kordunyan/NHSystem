package com.kordunyan.controller;

import com.kordunyan.domain.User;
import com.kordunyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "views/registerForm";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exists", true);
			return "views/registerForm";
		}
		userService.createUser(user);

		return "views/success";
	}

}
