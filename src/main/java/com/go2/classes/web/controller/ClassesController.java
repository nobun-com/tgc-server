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
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.NotificationService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Classes;
import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.TimeTable;

@Controller
@RequestMapping("/")
public class ClassesController extends BaseController {

	@Resource
	private NotificationService notifyService;

	@Resource
	private AddressService addressService; // Injected by Spring

	@Resource
	private CenterService centerService; // Injected by Spring

	@Resource
	private TimeTableService timeTableService; // Injected by Spring
	
	@Resource
	private ClassesService classesService; // Injected by Spring

	public ClassesController() {
	}

	@RequestMapping(value = "/centers")
	public String openCenters(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("centers", centerService.findAll());
		return "centers";
	}

	@RequestMapping(value = "/center-classes")
	public String openClassesByCenter(Model model, @RequestParam(name = "centerId") Long centerId) {
		model.addAttribute("center", centerService.findById(centerId));
		model.addAttribute("classes", timeTableService.findAllClassInstancesByCenter(centerId));
		return "center-classes";
	}

	@RequestMapping(value = "/center-classes-search")
	public String openSearchClassesByCenter(Model model, @RequestParam(name = "centerId") Long centerId,
			HttpSession session) {
		ClassesSearch classesSearch = (ClassesSearch) session.getAttribute("classesSearch");
		model.addAttribute("center", centerService.findById(centerId));
		model.addAttribute("classes", timeTableService.getClassesSearchResult(classesSearch, centerId));
		return "center-classes";
	}

	@RequestMapping(value = "/search-classes")
	public String openClasses(Model model, HttpSession session) {
		ClassesSearch classesSearch = new ClassesSearch();
		session.setAttribute("classesSearch", classesSearch);
		return "search-classes";
	}

	@RequestMapping(value = "/searchCenters", method = RequestMethod.POST)
	public String searchClasses(Model model, @ModelAttribute ClassesSearch classesSearch, HttpSession session) {
		model.addAttribute("centers", centerService.getCentersSearchResult(classesSearch));
		session.setAttribute("classesSearch", classesSearch);
		return "search-classes";
	}

	@RequestMapping(value = "/class-details")
	public String showClasseDetails(Model model, @RequestParam(name = "timeTableId") Long timeTableId) {
		TimeTable timeTable = timeTableService.findById(timeTableId);
		Classes classObj = classesService.findById(timeTable.getClasses().getId());
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("classObj", classObj);
		model.addAttribute("center", centerService.findById(classObj.getCenterId()));
		return "class-details";
	}

}
