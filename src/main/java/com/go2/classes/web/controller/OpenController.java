package com.go2.classes.web.controller;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.go2.classes.business.service.PromoService;
import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Student;

@Controller
@RequestMapping("/")
public class OpenController extends BaseController {

    @Resource
    private StudentService studentService; // Injected by Spring

    @Resource
    private TimeTableService timeTableService; // Injected by Spring

    @Resource
    private PromoService promoService; // Injected by Spring

    @ResponseBody
    @RequestMapping(value = "/openLogin", method = RequestMethod.POST)
    public String openLogin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	Student student = studentService.findByEmail(email);
	if (Objects.isNull(student)) {
	    throw new IllegalStateException("User not exists");
	} else {
	    if (password.equals(student.getPassword())) {
		session.setAttribute("userName", student.getName());
		session.setAttribute("userId", student.getId());
		session.setAttribute("isLoggedIn", true);
		session.setAttribute("userCartSize", timeTableService.getUserCartSize(student.getId()));
		return "Login success";
	    } else {
		throw new IllegalStateException("Bad credentials");
	    }
	}
    }

    @ResponseBody
    @RequestMapping(value = "/openRegister", method = RequestMethod.POST)
    public String openRegister(HttpSession session, @ModelAttribute Student student) throws IOException {

	student = studentService.create(student);
	session.setAttribute("userName", student.getName());
	session.setAttribute("userId", student.getId());
	session.setAttribute("isLoggedIn", true);
	session.setAttribute("userCartSize", 0);
	return "User created";
    }

    @RequestMapping(value = "/logout")
    public void addToCart(Model model, HttpSession session, HttpServletResponse response) throws IOException {
	session.invalidate();
	response.sendRedirect("/");
    }
}
