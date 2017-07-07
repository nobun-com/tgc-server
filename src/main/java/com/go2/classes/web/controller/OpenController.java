package com.go2.classes.web.controller;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Student;


@Controller
@RequestMapping("/")
public class OpenController extends BaseController{

	@Resource
    private StudentService studentService; // Injected by Spring
	
	@Resource
    private TimeTableService timeTableService; // Injected by Spring

	@RequestMapping(value="/openLogin", method=RequestMethod.POST)
	public String openLogin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String redirectUrl = request.getParameter("redirectUrl");
		Student student = studentService.findByEmail(email);
		if(Objects.isNull(student)) {
	        model.addAttribute("message", "user not found");
		} else {
			if(password.equals(student.getPassword())) {
		        session.setAttribute("userName", student.getName());
		        session.setAttribute("userId", student.getId());
		        session.setAttribute("isLoggedIn", true);
		        session.setAttribute("userCartSize",timeTableService.getUserCartSize(student.getId()));
		        response.sendRedirect(redirectUrl);
			} else {
		        model.addAttribute("message", "login failed");
			}
		}
		
		model.addAttribute("popup", "LOGIN");
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		
		return "index";
	}

	@RequestMapping(value="/openRegister", method=RequestMethod.POST)
	public String openRegister(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String mobile = request.getParameter("mobile");
		String refrralCode = request.getParameter("refrralCode");
		
		Student student = studentService.findByEmail(email);
		
		if(!Objects.isNull(student)) {
	        model.addAttribute("message", "user alredy registerd with email " + email);
		} else {
			if(password.equals(confirmPassword)) {
				student = new Student();
				student.setEmail(email);
				student.setName(name);
				student.setPassword(password);
				student.setGender(gender);
				student.setPhone(mobile);
				student.setRefrralCode(refrralCode);
				student = studentService.create(student);
				if(Objects.isNull(student)) {
			        model.addAttribute("message", "Unable to registred user");
				} else {
					session.setAttribute("userName", student.getName());
			        session.setAttribute("userId", student.getId());
			        session.setAttribute("isLoggedIn", true);
			        session.setAttribute("userCartSize",timeTableService.getUserCartSize(student.getId()));
			        response.sendRedirect("/");
				}
			} else {
				model.addAttribute("message", "Password and confirm password doesn't match");
			}
		}
		model.addAttribute("popup", "SIGNUP");
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("confirmPassword", confirmPassword);
		model.addAttribute("mobile", mobile);
		model.addAttribute("refrralCode", refrralCode);
        return "index";
	}

	@RequestMapping(value="/logout")
	public void addToCart(Model model,HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.sendRedirect("/");
	}
}
