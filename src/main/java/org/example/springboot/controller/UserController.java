package org.example.springboot.controller;

import org.example.springboot.model.User;
import org.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class UserController {

	private final UserService userService;

	@Autowired()
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String users(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}

	@GetMapping("/user")
	public String getUser(@RequestParam("id") long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "user";
	}

	@GetMapping("/new")
	public String addUser(User user) {
		return "create";
	}

	@PostMapping("/new")
	public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "create";
		} else {
			userService.addUser(user);
			return "redirect:/";
		}
	}

	@GetMapping("/edit")
	public String updateUser(@RequestParam("id") long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit";
	}

	@PostMapping("/edit")
	public String update(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit";
		} else {
			userService.updateUser(user);
			return "redirect:/";
		}
	}
	@PostMapping("/delete")
	public String delete(@RequestParam("id") long id) {
		userService.removeUser(id);
		return "redirect:/";
	}
}