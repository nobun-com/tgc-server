package com.go2.classes.web.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.go2.classes.business.service.ChildInterestsService;
import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.ClassesCategoryService;
import com.go2.classes.business.service.NotificationService;
import com.go2.classes.business.service.UserService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Child;
import com.go2.classes.models.ClassesSearch;

@Controller
@RequestMapping("/")
public class ChildController extends BaseController {

	@Resource
	private ChildService childService; // Injected by Spring

	@Resource
	private ChildInterestsService childInterestsService; // Injected by Spring

	@Resource
	private UserService userService; // Injected by Spring

	@Resource
	private ClassesCategoryService classesCategoryService; // Injected by Spring

	@Resource
	private NotificationService notifyService;

	@RequestMapping(value = "/add-child")
	public String openChild(Model model, HttpSession session) {
		Long userId=(Long) session.getAttribute("userId");
		model.addAttribute("user", userService.findById(userId));
		return "add-child";
	}

	@RequestMapping(value = "/find-class")
	public String findClass(Model model, HttpSession session, HttpServletRequest request) {
		Long childId = Long.parseLong(request.getParameter("childId"));
		Child child = childService.findById(childId);

		ClassesSearch classesSearch = new ClassesSearch(child);
        session.setAttribute("classesSearch", classesSearch);
		return "search-classes";
	}

	@RequestMapping(value = "/createChild", method = RequestMethod.POST)
	public void createChild(@ModelAttribute Child child, HttpServletResponse response) throws IOException, ParseException {
		childService.create(child);
		notifyService.addInfoMessage(child.getName() + " added Successfully.");
		response.sendRedirect("profile");
	}

	@RequestMapping(value = "/editChild")
	public String updateChild(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Long childId = Long.parseLong(request.getParameter("childId"));
		model.addAttribute("child", childService.findById(childId));
		return "update-child";
	}

	@RequestMapping(value = "/removeChild")
	public String removeChild(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Long childId = Long.parseLong(request.getParameter("childId"));
		childService.delete(childId);
		return "profile";
	}

	@RequestMapping(value = "/updateChild", method = RequestMethod.POST)
	public void updateChild(@ModelAttribute Child child, HttpServletResponse response) throws IOException, ParseException {

		if (child != null) {
			child = childService.update(child);
			notifyService.addInfoMessage("Updated Successfully.");
		} else {
			notifyService.addErrorMessage("Something went wrong!");
		}
		response.sendRedirect("profile");
	}

}
