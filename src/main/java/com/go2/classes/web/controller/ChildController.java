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

import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.StudentService;
import com.go2.classes.common.BaseController;
import com.go2.classes.models.Child;
import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.Student;


@Controller
@RequestMapping("/")
public class ChildController extends BaseController{

	@Resource
    private ChildService childService; // Injected by Spring
	
	@Resource
    private StudentService studentService; // Injected by Spring

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
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		Date date = df.parse(dateOfBirth);
		Long studentId = (Long) session.getAttribute("userId");
		
		Student student = studentService.findById(studentId);
		
		Child child =new Child();
		child.setName(name);
		child.setLocation(location);
		child.setGender(gender);
		child.setStudent(student);
		child.setDateOfBirth(date);
		
		child = childService.create(child);
		
        response.sendRedirect("profile");
	}
}
