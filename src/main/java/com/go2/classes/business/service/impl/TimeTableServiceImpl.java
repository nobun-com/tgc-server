package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.TimeTable;
import com.go2.classes.models.jpa.TimeTableEntity;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.mapping.TimeTableServiceMapper;
import com.go2.classes.data.repository.jpa.TimeTableJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TimeTableServiceImpl implements TimeTableService {

	@Resource
	private TimeTableJpaRepository timeTableJpaRepository;

	@Resource
	private TimeTableServiceMapper timeTableServiceMapper;
	
	@Override
	public TimeTable findById(Long id) {
		TimeTableEntity timeTableEntity = timeTableJpaRepository.findOne(id);
		return timeTableServiceMapper.mapTimeTableEntityToTimeTable(timeTableEntity);
	}

	@Override
	public List<TimeTable> findAll() {
		Iterable<TimeTableEntity> entities = timeTableJpaRepository.findAll();
		List<TimeTable> beans = new ArrayList<TimeTable>();
		for(TimeTableEntity timeTableEntity : entities) {
			beans.add(timeTableServiceMapper.mapTimeTableEntityToTimeTable(timeTableEntity));
		}
		return beans;
	}

	@Override
	public TimeTable save(TimeTable timeTable) {
		return update(timeTable) ;
	}

	@Override
	public TimeTable create(TimeTable timeTable) {
		TimeTableEntity timeTableEntity = null;
		if(!Objects.isNull(timeTable.getId())) {
			timeTableEntity = timeTableJpaRepository.findOne(timeTable.getId());
		}
		if(!Objects.isNull(timeTableEntity)) {
			throw new IllegalStateException("already.exists");
		}
		timeTableEntity = new TimeTableEntity();
		timeTableServiceMapper.mapTimeTableToTimeTableEntity(timeTable, timeTableEntity);
		TimeTableEntity timeTableEntitySaved = timeTableJpaRepository.save(timeTableEntity);
		return timeTableServiceMapper.mapTimeTableEntityToTimeTable(timeTableEntitySaved);
	}

	@Override
	public TimeTable update(TimeTable timeTable) {
		TimeTableEntity timeTableEntity = timeTableJpaRepository.findOne(timeTable.getId());
		timeTableServiceMapper.mapTimeTableToTimeTableEntity(timeTable, timeTableEntity);
		TimeTableEntity timeTableEntitySaved = timeTableJpaRepository.save(timeTableEntity);
		return timeTableServiceMapper.mapTimeTableEntityToTimeTable(timeTableEntitySaved);
	}

	@Override
	public void delete(Long id) {
		timeTableJpaRepository.delete(id);
	}

	public TimeTableJpaRepository getTimeTableJpaRepository() {
		return timeTableJpaRepository;
	}

	public void setTimeTableJpaRepository(TimeTableJpaRepository timeTableJpaRepository) {
		this.timeTableJpaRepository = timeTableJpaRepository;
	}

	public TimeTableServiceMapper getTimeTableServiceMapper() {
		return timeTableServiceMapper;
	}

	public void setTimeTableServiceMapper(TimeTableServiceMapper timeTableServiceMapper) {
		this.timeTableServiceMapper = timeTableServiceMapper;
	}

	@Override
	public List<TimeTable> findByCenterId(Long centerId) {
		Iterable<TimeTableEntity> entities = timeTableJpaRepository.findByCenterId(centerId);
		List<TimeTable> beans = new ArrayList<TimeTable>();
		for(TimeTableEntity timeTableEntity : entities) {
			beans.add(timeTableServiceMapper.mapTimeTableEntityToTimeTable(timeTableEntity));
		}
		return beans;
	}

	@Override
	public List<Map<String, Object>> findAllClassInstancesByCenter(Long centerId) {
		Iterable<TimeTableEntity> entities = timeTableJpaRepository.findByCenterId(centerId);
		List<Map<String, Object>> beans = new ArrayList<Map<String, Object>>();
		for(TimeTableEntity timeTableEntity : entities) {
			beans.add(timeTableServiceMapper.mapTimeTableEntityToJSONMap(timeTableEntity));
		}
		return beans;
	}

	@Override
	public List<Map<String, Object>> findAllClassInstancesByClass(Long classId) {
		Iterable<TimeTableEntity> entities = timeTableJpaRepository.findAllByclassesId(classId);
		List<Map<String, Object>> beans = new ArrayList<Map<String, Object>>();
		for(TimeTableEntity timeTableEntity : entities) {
			beans.add(timeTableServiceMapper.mapTimeTableEntityToJSONMap(timeTableEntity));
		}
		return beans;
	}

}
