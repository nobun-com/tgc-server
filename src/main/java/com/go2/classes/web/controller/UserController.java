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

import com.go2.classes.business.service.ChildInterestsService;
import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.UserService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.User;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Resource
    private UserService userService; // Injected by Spring

    @Resource
    private UserCartService userCartService; // Injected by Spring

    @Resource
    private ChildService childService; // Injected by Spring

    @Resource
    private ChildInterestsService childInterestsService; // Injected by Spring

    @Resource
    private TimeTableService timeTableService; // Injected by Spring

    @RequestMapping(value = "/profile")
    public String openMyCart(Model model, HttpSession session) {
	Long userId = (Long) session.getAttribute("userId");
	model.addAttribute("user", userService.findById(userId));
	model.addAttribute("childs", childService.getAllChildsByUser(userId));
	model.addAttribute("userBookings", userCartService.getAllUserBookings(userId));
	return "profile";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

	Long id = Long.parseLong(request.getParameter("id"));
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	String mobile = request.getParameter("mobile");
	String refrralCode = request.getParameter("refrralCode");

	User user = userService.findById(id);

	if (Objects.isNull(user)) {
	    model.addAttribute("message", "User not found");
	} else {
	    user.setEmail(email);
	    user.setName(name);
	    user.setGender(gender);
	    user.setPhone(mobile);
	    user.setRefrralCode(refrralCode);
	    user = userService.update(user);
	    if (Objects.isNull(user)) {
		model.addAttribute("message", "Unable to update user");
	    } else {
		session.setAttribute("userName", user.getName());
	    }
	}

	response.sendRedirect("profile");
    }
}
