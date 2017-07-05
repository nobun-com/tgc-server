package com.go2.classes.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.UserCart;


@Controller
@RequestMapping("/")
public class CartController extends BaseController{

	@Resource
    private TimeTableService timeTableService; // Injected by Spring
	
	@Resource
    private UserCartService userCartService; // Injected by Spring

	@RequestMapping(value="/my-cart")
	public String openMyCart(Model model, HttpSession session) {
		Long userId=(Long) session.getAttribute("userId");
		model.addAttribute("userCartClasses", timeTableService.getAllClassesInCart(userId));
		model.addAttribute("total", "$" + userCartService.getToatlFees(userId));
        session.setAttribute("userCartSize",timeTableService.getUserCartSize(userId));
		return "my-cart";
	}
	
	@RequestMapping(value="/addToCart")
	public void addToCart(Model model, @RequestParam(name="classId") Long classId, HttpServletResponse response, HttpSession session) throws IOException {
		Long userId=(Long) session.getAttribute("userId");
		userCartService.create(new UserCart(userId, classId));
		response.sendRedirect("my-cart");
	}
	
	@RequestMapping(value="/bookCart")
	public void bookCart(Model model, HttpServletResponse response, HttpSession session) throws IOException {
		Long userId=(Long) session.getAttribute("userId");
		Integer count = userCartService.bookAllCarts(userId);
		response.sendRedirect("my-cart");
	}
	
	@RequestMapping(value="/removeFromCart")
	public void removeFromCart(Model model, @RequestParam(name="userCartId") Long userCartId, HttpServletResponse response) throws IOException {
		userCartService.delete(userCartId);
		response.sendRedirect("my-cart");
	}
}
