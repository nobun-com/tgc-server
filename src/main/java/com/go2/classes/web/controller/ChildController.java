package com.go2.classes.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.go2.classes.business.service.StudentService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Child;
import com.go2.classes.models.ChildInterests;
import com.go2.classes.models.ClassesCategory;
import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.Student;


@Controller
@RequestMapping("/")
public class ChildController extends BaseController{

	@Resource
    private ChildService childService; // Injected by Spring
	
	@Resource
    private ChildInterestsService childInterestsService; // Injected by Spring
	
	@Resource
    private StudentService studentService; // Injected by Spring
	
	@Resource
    private ClassesCategoryService classesCategoryService; // Injected by Spring
	
	@Resource
    private NotificationService notifyService;

	@RequestMapping(value="/add-child")
	public String openChild(Model model, HttpSession session) {
		return "add-child";
	}
	
	@RequestMapping(value="/createChild", method=RequestMethod.POST)
	public void createChild(Model model, @ModelAttribute ClassesSearch classesSearch, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ParseException {
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String location = request.getParameter("location");
		String dateOfBirth = request.getParameter("dateOfBirth");
		Long studentId = (Long) session.getAttribute("userId");
		
		Student student = studentService.findById(studentId);
		
		Child child =new Child();
		child.setName(name);
		child.setLocation(location);
		child.setGender(gender);
		child.setStudent(student);
		child.setDateOfBirth(dateOfBirth);
		
		child = childService.create(child);
		
		String[] interests = classesSearch.getInterest();
		for(int i=0; i < interests.length; i++){
			Long classesCategoryId = Long.parseLong(interests[i]);
			ClassesCategory classesCategory = classesCategoryService.findById(classesCategoryId);
			
			ChildInterests childInterests = new ChildInterests();
			childInterests.setChild(child);
			childInterests.setClassesCategory(classesCategory);
			
			childInterests = childInterestsService.create(childInterests);
			
		}
		
        response.sendRedirect("profile");
	}
	
	@RequestMapping(value="/editChild")
	public String updateChild(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Long childId = Long.parseLong(request.getParameter("childId"));
		model.addAttribute("child", childService.findById(childId));
		return "update-child";
	}
	
	@RequestMapping(value="/updateChild", method=RequestMethod.POST)
	public void updateChild(Model model, @ModelAttribute ClassesSearch classesSearch, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ParseException {
		
		Long childId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String location = request.getParameter("location");
		String dateOfBirth = request.getParameter("dateOfBirth");
		Long studentId = (Long) session.getAttribute("userId");
		
		Child child = childService.findById(childId);
		
		if(child!=null){
			
			Student student = studentService.findById(studentId);
			
			child.setName(name);
			child.setLocation(location);
			child.setGender(gender);
			child.setStudent(student);
			child.setDateOfBirth(dateOfBirth);
			
			child = childService.update(child);
			
			notifyService.addInfoMessage("Updated Successfully.");
		}else{
			notifyService.addErrorMessage("Something went wrong!");
		}
		
        response.sendRedirect("profile");
	}

}
