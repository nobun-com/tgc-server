package com.go2.classes.rest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.go2.classes.business.service.AdminService;
import com.go2.classes.business.service.TeacherService;
import com.go2.classes.data.repository.jpa.FilesJpaRepository;
import com.go2.classes.models.Admin;
import com.go2.classes.models.Teacher;
import com.go2.classes.models.jpa.FilesEntity;
import com.go2.classes.rest.common.Utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class CommonRestController {

	@Value("${uploadedimages}")
	private String uploadedImagesPath = "";

	@Resource
	FilesJpaRepository filesJpaRepository;

	@Resource
    private AdminService adminService; // Injected by Spring

	@Resource
    private TeacherService teacherService; // Injected by Spring

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String saveFile(@RequestParam("logo") MultipartFile file) {
		FilesEntity filesEntity = Utilities.saveImage(file, uploadedImagesPath);
		filesJpaRepository.save(filesEntity);
		return filesEntity.getUuid();
	}

	@RequestMapping(value="/adminLogin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminLogin(@RequestBody Map<String, String> data) {
		Map<String, String> result = new HashMap<String, String>();
		String email = data.get("email");
		String password = data.get("password");
		
		Admin admin = adminService.findByEmail(email);
		String token = "";
		if(Objects.isNull(admin)) {
	        result.put("message", "user not found");
		} else {
			if(password.equals(admin.getPassword())) {
				token = getToken(admin.getEmail(), admin.getId());
		        result.put("message", "login success");
				result.put("token", token);
			} else {
		        result.put("message", "login failed");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/teacherLogin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> teacherLogin(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Teacher teacher = teacherService.findByEmail(email);
		String token = "";
		if(Objects.isNull(teacher)) {
	        result.put("message", "teacher not found");
		} else {
			if(password.equals(teacher.getPassword())) {
				token = getToken(teacher.getEmail(), teacher.getId());
		        result.put("message", "login success");
				result.put("token", token);
			} else {
		        result.put("message", "login failed");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/getImage/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  getImage(@PathVariable("uuid") String uuid){
		FilesEntity filesEntity = filesJpaRepository.findOne(uuid);
		return  new FileSystemResource(Utilities.getImage(filesEntity.getPath()));
	}
	
	public String getToken(String email, Long id){
		String token = Jwts.builder().setSubject(email).setId(id+"").signWith(SignatureAlgorithm.HS512, "GO2CLASSES.COM").compact();
		return token;
	};
}
