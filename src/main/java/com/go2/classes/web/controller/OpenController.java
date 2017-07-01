package com.go2.classes.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.Student;
import com.go2.classes.business.service.AddressService;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.TimeTableService;


@Controller
@RequestMapping("/")
public class OpenController {


	@Resource
    private AddressService addressService; // Injected by Spring

	@Resource
    private CenterService centerService; // Injected by Spring

	@Resource
    private TimeTableService timeTableService; // Injected by Spring

	@Resource
    private StudentService studentService; // Injected by Spring

	public OpenController() {
	}

	@RequestMapping()
	public String list(Model model) {
		return "index";
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

	@RequestMapping(value="/map")
	public String openMap(Model model, @RequestParam(name="centerId") Long centerId) {
        model.addAttribute("address", centerService.findById(centerId).getAddress());
		return "map";
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

	@RequestMapping(value="/openLogin", method=RequestMethod.POST)
	public String openLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Student student = studentService.findByEmail(email);
		String token = "";
		if(Objects.isNull(student)) {
	        model.addAttribute("message", "user not found");
		} else {
			if(password.equals(student.getPassword())) {
				token = getToken(student.getEmail(), student.getId());
		        model.addAttribute("token", token);
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
	public String openRegister(Model model, HttpServletRequest request, HttpServletResponse response) {

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
					model.addAttribute("message", "User registred");
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

	public String getToken(String email, Long id){
		String token = Jwts.builder().setSubject(email).setId(id+"").signWith(SignatureAlgorithm.HS512, "GO2CLASSES.COM").compact();
		return token;
	};

}
