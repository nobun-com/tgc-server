package com.go2.classes.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.go2.classes.business.service.AdminService;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.EducatorService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.BaseController;
import com.go2.classes.common.Utilities;
import com.go2.classes.models.Admin;
import com.go2.classes.models.Educator;

@CrossOrigin("*")
@Controller
public class CommonRestController extends BaseController {

	@Resource
	private AdminService adminService; // Injected by Spring

	@Resource
	private EducatorService educatorService; // Injected by Spring

	@Resource
	private CenterService centerService; // Injected by Spring

	@Resource
	private UserCartService userCartService; // Injected by Spring

	@Resource
	private ClassesService classesService; // Injected by Spring

	@Value("${awscredentialsaccesskey}")
	private String awsCredentialsAccessKey = "";

	@Value("${awscredentialssecretkey}")
	private String awsCredentialsSecretKey = "";

	@Value("${awscredentialsbucketname}")
	private String awsCredentialsBucketName = "";

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String saveFile(@RequestParam("logo") MultipartFile receivedFile) {
		String uuid = UUID.randomUUID().toString();
		String path = "images/" + Utilities.date.format(new Date()) + "/";
		File file;
		try {
			file = File.createTempFile(uuid, "." + FilenameUtils.getExtension(receivedFile.getOriginalFilename()));
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(receivedFile.getBytes());
			fos.close();
		} catch (Exception e) {
			throw new IllegalStateException("unable to upload file");
		}

		AWSCredentials credentials = new BasicAWSCredentials(awsCredentialsAccessKey, awsCredentialsSecretKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		s3client.putObject(new PutObjectRequest(awsCredentialsBucketName, path + file.getName(), file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		URL url = s3client.getUrl(awsCredentialsBucketName, path + file.getName());
		return url.toExternalForm();
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

	@RequestMapping(value = "/educatorLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> educatorLogin(@RequestBody Map<String, String> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		String email = data.get("email");
		String password = data.get("password");

		Educator educator = educatorService.findByEmail(email);
		String token = "";
		if (Objects.isNull(educator)) {
			result.put("message", "educator not found");
		} else {
			if (password.equals(educator.getPassword())) {
				token = getToken(educator.getEmail(), educator.getId());
				result.put("message", "login success");
				result.put("token", token);
				result.put("educator", educator);
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
		result.put("educatorsCount", educatorService.getEducatorsCount());
		result.put("bookingsCount", userCartService.getBookingsCount());
		result.put("activeClassesCount", classesService.getActiveClassesCount());
		result.put("barChartData", finalResult);

		return result;
	}

	@RequestMapping(value = "/educatorDashboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> educatorDashboardData(@PathVariable("id") Long educatorId) {

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

		List<Object> lst = userCartService.getLastMonthBookingsByEducator(educatorId);
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

		result.put("bookingsCount", userCartService.getBookingsCountByEducator(educatorId));
		result.put("activeClassesCount", classesService.getActiveClassesCountByEducator(educatorId));
		result.put("barChartData", finalResult);

		return result;
	}

}
