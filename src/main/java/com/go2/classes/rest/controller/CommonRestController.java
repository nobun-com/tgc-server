package com.go2.classes.rest.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.go2.classes.business.service.AdminService;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.TeacherService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.common.Utilities;
import com.go2.classes.data.repository.jpa.FilesJpaRepository;
import com.go2.classes.models.Admin;
import com.go2.classes.models.Teacher;
import com.go2.classes.models.jpa.FilesEntity;

@Controller
public class CommonRestController extends BaseController {

	@Value("${uploadedimages}")
	private String uploadedImagesPath = "";

	@Resource
	FilesJpaRepository filesJpaRepository;

	@Resource
	private AdminService adminService; // Injected by Spring

	@Resource
	private TeacherService teacherService; // Injected by Spring

	@Resource
	private CenterService centerService; // Injected by Spring

	@Resource
	private UserCartService userCartService; // Injected by Spring

	@Resource
	private ClassesService classesService; // Injected by Spring

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String saveFile(@RequestParam("logo") MultipartFile file) {
		FilesEntity filesEntity = Utilities.saveImage(file, uploadedImagesPath);
		filesJpaRepository.save(filesEntity);
		return filesEntity.getUuid();
	}

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adminLogin(@RequestBody Map<String, String> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		String email = data.get("email");
		String password = data.get("password");

		Admin admin = adminService.findByEmail(email);
		String token = "";
		if (Objects.isNull(admin)) {
			result.put("message", "user not found");
		} else {
			if (password.equals(admin.getPassword())) {
				token = getToken(admin.getEmail(), admin.getId());
				result.put("message", "login success");
				result.put("token", token);
				result.put("admin", admin);
			} else {
				result.put("message", "login failed");
			}
		}
		return result;
	}

	@RequestMapping(value = "/teacherLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> teacherLogin(@RequestBody Map<String, String> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		String email = data.get("email");
		String password = data.get("password");

		Teacher teacher = teacherService.findByEmail(email);
		String token = "";
		if (Objects.isNull(teacher)) {
			result.put("message", "teacher not found");
		} else {
			if (password.equals(teacher.getPassword())) {
				token = getToken(teacher.getEmail(), teacher.getId());
				result.put("message", "login success");
				result.put("token", token);
				result.put("teacher", teacher);
			} else {
				result.put("message", "login failed");
			}
		}
		return result;
	}

	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> adminDashboardData() {
		Map<String, Object> result = new HashMap<String, Object>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date fromDate = cal.getTime();
		Date toDate = new Date();

		Calendar start = Calendar.getInstance();
		start.setTime(fromDate);
		Calendar end = Calendar.getInstance();
		end.setTime(toDate);

		List<Object> lst = userCartService.getLastMonthBookings();
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		Iterator it = lst.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			map1.put(formatter.format(o[0]) + "", Integer.parseInt(o[1] + ""));
		}
		/*
		 * System.out.println("map1"); for (Map.Entry<String, Integer> entry :
		 * map1.entrySet()) { System.out.println("Key = " + entry.getKey() +
		 * ", Value = " + entry.getValue()); }
		 */ 
		
		LinkedHashMap<String, Integer> finalResult = new LinkedHashMap<String, Integer>();
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			if (map1.containsKey(formatter.format(date))) {
				finalResult.put(formatter.format(date), map1.get(formatter.format(date)));
			} else {
				finalResult.put(formatter.format(date), 0);
			}
		}

		result.put("centersCount", centerService.getCentersCount());
		result.put("teachersCount", teacherService.getTeachersCount());
		result.put("bookingsCount", userCartService.getBookingsCount());
		result.put("activeClassesCount", classesService.getActiveClassesCount());
		result.put("barChartData", finalResult);

		return result;
	}

	@RequestMapping(value = "/educatorDashboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> educatorDashboardData(@PathVariable("id") Long teacherId) {

		Map<String, Object> result = new HashMap<String, Object>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date fromDate = cal.getTime();
		Date toDate = new Date();

		Calendar start = Calendar.getInstance();
		start.setTime(fromDate);
		Calendar end = Calendar.getInstance();
		end.setTime(toDate);

		List<Object> lst = userCartService.getLastMonthBookingsByEducator(teacherId);
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		Iterator it = lst.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			map1.put(formatter.format(o[0]) + "", Integer.parseInt(o[1] + ""));
		}
		/*
		 * System.out.println("map1"); for (Map.Entry<String, Integer> entry :
		 * map1.entrySet()) { System.out.println("Key = " + entry.getKey() +
		 * ", Value = " + entry.getValue()); }
		 */

		LinkedHashMap<String, Integer> finalResult = new LinkedHashMap<String, Integer>();
		for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			if (map1.containsKey(formatter.format(date))) {
				finalResult.put(formatter.format(date), map1.get(formatter.format(date)));
			} else {
				finalResult.put(formatter.format(date), 0);
			}
		}

		result.put("bookingsCount", userCartService.getBookingsCountByEducator(teacherId));
		result.put("activeClassesCount", classesService.getActiveClassesCountByEducator(teacherId));
		result.put("barChartData", finalResult);

		return result;
	}

	@RequestMapping(value = "/getImage/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getImage(@PathVariable("uuid") String uuid) {
		FilesEntity filesEntity = filesJpaRepository.findOne(uuid);
		if (Objects.isNull(filesEntity)) {
			return null;
		}
		return new FileSystemResource(Utilities.getImage(filesEntity.getPath()));
	}

}
