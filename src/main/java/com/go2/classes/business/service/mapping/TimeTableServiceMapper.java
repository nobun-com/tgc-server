package com.go2.classes.business.service.mapping;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.TimeTable;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.TimeTableEntity;
import com.go2.classes.rest.common.Utilities;

@Component
public class TimeTableServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	@Resource
	private ClassesServiceMapper classesServiceMapper;
	
	public TimeTableServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public TimeTable mapTimeTableEntityToTimeTable(TimeTableEntity timeTableEntity) {
		if(timeTableEntity == null) {
			return null;
		}

		//--- Generic mapping 
		TimeTable timeTable = map(timeTableEntity, TimeTable.class);

		if(timeTableEntity.getClasses() != null) {
			timeTable.setClasses(classesServiceMapper.mapClassesEntityToClasses(timeTableEntity.getClasses()));
		}

		return timeTable;
	}
	
	public Map<String, Object> mapTimeTableEntityToJSONMap(TimeTableEntity timeTableEntity) {

		if(timeTableEntity == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", timeTableEntity.getId());
		map.put("day", Utilities.dayOnly.format(timeTableEntity.getStartTime()));
		map.put("time", Utilities.timeOnly.format(timeTableEntity.getStartTime()) + " to " + Utilities.timeOnly.format(timeTableEntity.getEndTime()));
		map.put("date", Utilities.dateWithoutTime.format(timeTableEntity.getStartTime()));
		map.put("fee", "$" + timeTableEntity.getClasses().getFee());
		map.put("slots", timeTableEntity.getClasses().getMaxSlots() + " SLOTS LEFT");
		map.put("name", timeTableEntity.getClasses().getClassName());

		return map;
	}
	
	public void mapTimeTableToTimeTableEntity(TimeTable timeTable, TimeTableEntity timeTableEntity) {
		if(timeTable == null) {
			return;
		}

		if(hasLinkToClasses(timeTable)) {
			ClassesEntity classes = new ClassesEntity();
			classes.setId(timeTable.getClasses().getId());
			timeTableEntity.setClasses(classes);
		} else {
			timeTableEntity.setClasses(null);
		}

		//--- Generic mapping 
		map(timeTable, timeTableEntity);

	}
	
	private boolean hasLinkToClasses(TimeTable timeTable) {
		if(timeTable.getClass() != null) {
			return true;
		}
		return false;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
}