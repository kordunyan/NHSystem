package com.kordunyan.controller;

import com.kordunyan.domain.Task;
import com.kordunyan.domain.User;
import com.kordunyan.service.TaskService;
import com.kordunyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@GetMapping("/addtask")
	public String taskForm(@RequestParam("email") String email, Model model, HttpSession session) {
		session.setAttribute("email", email);
		model.addAttribute("task", new Task());
		return "views/taskForm";
	}

	@PostMapping("/addtask")
	public String addTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "views/taskForm";
		}
		String email = (String) session.getAttribute("email");
		taskService.addTask(task, userService.findOne(email));
		return "redirect:/users";
	}

}
