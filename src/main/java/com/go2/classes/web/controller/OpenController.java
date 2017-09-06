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
import com.go2.classes.business.service.UserService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.User;

@Controller
@RequestMapping("/")
public class OpenController extends BaseController {

    @Resource
    private UserService userService; // Injected by Spring

    @Resource
    private TimeTableService timeTableService; // Injected by Spring

    @Resource
    private PromoService promoService; // Injected by Spring

    @ResponseBody
    @RequestMapping(value = "/openLogin", method = RequestMethod.POST)
    public String openLogin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	User user = userService.findByEmail(email);
	if (Objects.isNull(user)) {
	    throw new IllegalStateException("User not exists");
	} else {
	    if (password.equals(user.getPassword())) {
		session.setAttribute("userName", user.getName());
		session.setAttribute("userId", user.getId());
		session.setAttribute("isLoggedIn", true);
		session.setAttribute("userCartSize", timeTableService.getUserCartSize(user.getId()));
		return "Login success";
	    } else {
		throw new IllegalStateException("Bad credentials");
	    }
	}
    }

    @ResponseBody
    @RequestMapping(value = "/openRegister", method = RequestMethod.POST)
    public String openRegister(HttpSession session, @ModelAttribute User user) throws IOException {

	user = userService.create(user);
	session.setAttribute("userName", user.getName());
	session.setAttribute("userId", user.getId());
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
