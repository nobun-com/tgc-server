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

import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Student;


@Controller
@RequestMapping("/")
public class UserController extends BaseController{

	@Resource
    private StudentService studentService; // Injected by Spring
	
	@Resource
    private UserCartService userCartService; // Injected by Spring
	
	@Resource
    private ChildService childService; // Injected by Spring
	
	@Resource
    private TimeTableService timeTableService; // Injected by Spring

	@RequestMapping(value="/profile")
	public String openMyCart(Model model, HttpSession session) {
		Long userId=(Long) session.getAttribute("userId");
		model.addAttribute("user", studentService.findById(userId));
		model.addAttribute("childs", childService.getAllChildsByStudent(userId));
		return "profile";
	}
	
	@RequestMapping(value="/updateStudent", method=RequestMethod.POST)
	public void updateStudent(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String refrralCode = request.getParameter("refrralCode");
		
		Student student = studentService.findById(id);
		
		if(Objects.isNull(student)) {
	        model.addAttribute("message", "User not found");
		} else {
				student.setEmail(email);
				student.setName(name);
				student.setGender(gender);
				student.setPhone(mobile);
				student.setRefrralCode(refrralCode);
				student = studentService.update(student);
				if(Objects.isNull(student)) {
			        model.addAttribute("message", "Unable to update user");
				} else {
					session.setAttribute("userName", student.getName());					
				}
		}
		
		response.sendRedirect("profile");
	}
}
