package com.go2.classes.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.UserCart;

@Controller
@RequestMapping("/")
public class CartController extends BaseController {

    @Resource
    private TimeTableService timeTableService; // Injected by Spring

    @Resource
    private UserCartService userCartService; // Injected by Spring

    @RequestMapping(value = "/my-cart")
    public String openMyCart(Model model, HttpSession session) {
	Long userId = (Long) session.getAttribute("userId");
	model.addAttribute("userCartClasses", timeTableService.getAllClassesInCart(userId));
	model.addAttribute("total", "HKD" + userCartService.getToatlFees(userId));
	session.setAttribute("userCartSize", timeTableService.getUserCartSize(userId));
	return "my-cart";
    }

    @RequestMapping(value = "/addToCart")
    public void addToCart(Model model, @RequestParam(name = "classId") Long classId, HttpServletResponse response, HttpSession session) throws IOException {
	Long userId = (Long) session.getAttribute("userId");
	Double classFees = timeTableService.findFeesFromClases(classId);
	userCartService.create(new UserCart(userId, classId, classFees));
	response.sendRedirect("my-cart");
    }

    @RequestMapping(value = "/removeFromCart")
    public void removeFromCart(Model model, @RequestParam(name = "userCartId") Long userCartId, HttpServletResponse response) throws IOException {
	userCartService.delete(userCartId);
	response.sendRedirect("my-cart");
    }

    @RequestMapping(value = "/applyCoupon", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> applyCoupon(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String couponCode = request.getParameter("couponCode");
	Long userCartId = Long.parseLong(request.getParameter("userCartId"));
	return userCartService.applyCoupon(userCartId, couponCode);
    }
}
