package com.go2.classes.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;


@Controller
@RequestMapping("/")
public class UserController extends BaseController{

	@Resource
    private StudentService studentService; // Injected by Spring
	
	@Resource
    private UserCartService userCartService; // Injected by Spring

	@RequestMapping(value="/profile")
	public String openMyCart(Model model, HttpSession session) {
		Long userId=(Long) session.getAttribute("userId");
		model.addAttribute("user", studentService.findById(userId));
		return "profile";
	}
}
