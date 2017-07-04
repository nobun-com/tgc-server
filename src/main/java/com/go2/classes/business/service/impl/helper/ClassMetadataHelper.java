package com.go2.classes.business.service.impl.helper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.models.Classes;
import com.go2.classes.models.TimeTable;
import com.go2.classes.rest.common.Utilities;

@Component
@Transactional
public class ClassMetadataHelper {

	private Classes classMetadata = null;
	Date startDate = null;
	Date endDate = null;
	private int dayCount = 0;
	private int occurrence = 0;
	
	@Resource
	private TimeTableService timeTableService;

	private void init(Classes classMetadataObj) {
		this.classMetadata = classMetadataObj;
		dayCount = 0;
		occurrence = 0;
		startDate = null;
		endDate = null;
		try {
			if(!Objects.isNull(classMetadata.getOccurrence())) {
				occurrence = classMetadata.getOccurrence();
			}
			if(!Objects.isNull(classMetadata.getStartDate())) {
				startDate = Utilities.dateWithoutTime.parse(Utilities.dateWithoutTime.format(classMetadata.getStartDate()));
			}
			if(!Objects.isNull(classMetadata.getEndDate())) {
				endDate = Utilities.dateWithoutTime.parse(Utilities.dateWithoutTime.format(classMetadata.getEndDate()));
			}
		} catch (Exception e) {
			throw new IllegalStateException("start date and end date not found");
		}
		if(!isValid(startDate)) {
			throw new IllegalStateException("end date should gretter than start date or occurrence shoud gretter than zero");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parseClassesMetaData(Classes classMetadataObj){

		init(classMetadataObj);
		Map<String, Object> frequencyMetadata = null;
		try {
			frequencyMetadata = jsonToMap(classMetadata.getFrequencyMetadata());
			Map<String, Map<String, Integer>> schedule = null;
			if(frequencyMetadata.containsKey("daily")) {
				schedule = getDailySchedule((Map)(frequencyMetadata.get("daily")));
			} else {
				schedule = (Map)frequencyMetadata.get("weekly");
			}
			createClasses(schedule);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to parse frequency_metadate");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	private void createClasses(Map<String, Map<String, Integer>> schedule) {
		Date date = startDate;
		String day = "";
		while(isValid(date)) {
			day = Utilities.dayOnly.format(date);
			if(schedule.containsKey(day)) {
				Map<String, Integer> daySchedule = schedule.get(day);
				Integer startTime = daySchedule.get("startTime");
				Integer endTime = daySchedule.get("endTime");
				
				Date start = DateUtils.addMinutes(date, startTime);
				Date end = DateUtils.addMinutes(date, endTime);
				createClass(start, end);
			}
			date = DateUtils.addDays(date, 1);
		}
		saveClassMetaData(date);
	}
		
	private void saveClassMetaData(Date date) {
		classMetadata.setEndDate(date);
		classMetadata.setOccurrence(occurrence);
	}

	private boolean isValid(Date date) {
		if(Objects.isNull(endDate)) {
			return dayCount < occurrence;
		}
		return date.before(endDate) || DateUtils.isSameDay(date, endDate);
	}

	private void createClass(Date start, Date end) {
		timeTableService.create(new TimeTable(classMetadata, start, end));
//		System.out.println(new TimeTable(classMetadata.getId(), start, end));
		dayCount++;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> jsonToMap(String json) throws JsonParseException, JsonMappingException, IOException {
		json = json.replaceAll("'", "\\\"");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Map.class);
	}
	
	private Map<String, Map<String, Integer>> getDailySchedule(Map<String, Object> dailySchedule) {
		Integer startTime = Integer.parseInt(dailySchedule.get("startTime")+"");
		Integer endTime = Integer.parseInt(dailySchedule.get("endTime")+"");
		
		if(startTime >= endTime) {
			throw new IllegalStateException("end time should gretter than start time");
		}
		Map<String, Map<String, Integer>> result = new LinkedHashMap<String, Map<String, Integer>>();
		Map<String, Integer> timeing = new LinkedHashMap<String, Integer>();
		timeing.put("startTime", startTime);
		timeing.put("endTime", endTime);
		String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for(String day : days) {
			result.put(day, timeing);
		}
		return result;
	}

	public static void main(String args[]) throws ParseException, JsonParseException, JsonMappingException, IOException {
		Classes classMetadata = getClasses();
		new ClassMetadataHelper().parseClassesMetaData(classMetadata);
	}

	private static Classes getClasses() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String str = "{\"id\": 21,    \"className\": \"mathematics\",    \"about\": \"m3 mech cse it entc\",    \"teacherId\": 3,    \"createdTime\": 1493942400000,    \"startDate\": \"2017-05-02\",        \"fee\": 6500.5,    \"occurrence\": 1,    \"frequency\": \"Daily\",    \"frequencyMetadata\": \"{'daily': {'startTime': 70,'endTime': 80}}\",    \"logoUrl\": null,    \"rrule\": null}";
		return mapper.readValue(str, Classes.class);
	}
	
}