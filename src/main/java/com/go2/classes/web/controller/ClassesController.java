package com.go2.classes.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.go2.classes.business.service.AddressService;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.NotificationService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.ClassesSearch;


@Controller
@RequestMapping("/")
public class ClassesController extends BaseController{

	@Resource
	private NotificationService notifyService;

	@Resource
    private AddressService addressService; // Injected by Spring

	@Resource
    private CenterService centerService; // Injected by Spring

	@Resource
    private TimeTableService timeTableService; // Injected by Spring

	public ClassesController() {
	}

	@RequestMapping(value="/centers")
	public String openCenters(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("centers", centerService.findAll());
		return "centers";
	}

	@RequestMapping(value="/center-classes")
	public String openClassesByCenter(Model model, @RequestParam(name="centerId") Long centerId) {
        model.addAttribute("center", centerService.findById(centerId));
        model.addAttribute("classes", timeTableService.findAllClassInstancesByCenter(centerId));
		return "center-classes";
	}

	@RequestMapping(value="/center-classes-search")
	public String openSearchClassesByCenter(Model model, @RequestParam(name="centerId") Long centerId, HttpSession session) {
        ClassesSearch classesSearch = (ClassesSearch) session.getAttribute("classesSearchObj");
        model.addAttribute("center", centerService.findById(centerId));
        model.addAttribute("classes", timeTableService.getClassesSearchResult(classesSearch, centerId));
		return "center-classes";
	}

	@RequestMapping(value="/search-classes")
	public String openClasses(Model model) {
		return "search-classes";
	}

	@RequestMapping(value="/searchCenters", method=RequestMethod.POST)
	public String searchClasses(Model model, @ModelAttribute ClassesSearch classesSearch, HttpSession session) {
        model.addAttribute("centers", centerService.getCentersSearchResult(classesSearch));
        session.setAttribute("classesSearchObj", classesSearch);
		return "search-classes";
	}

}
