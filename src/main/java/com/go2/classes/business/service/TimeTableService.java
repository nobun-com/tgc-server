package com.go2.classes.business.service;

import java.util.List;

import java.util.Map;

import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.TimeTable;

public interface TimeTableService { 

	TimeTable findById( Long id  ) ;

	List<TimeTable> findAll();

	TimeTable save(TimeTable entity);

	TimeTable update(TimeTable entity);

	TimeTable create(TimeTable entity);

	void delete( Long id );

	List<TimeTable>  findByCenterId(Long centerId);

	List<Map<String, Object>> findAllClassInstancesByClass(Long classId);

	List<Map<String, Object>> findAllClassInstancesByCenter(Long centerId);

	List<Map<String, Object>> getClassesSearchResult(ClassesSearch classesSearch, Long centerId);

	Object getAllClassesInCart(Long userId);
	Integer getUserCartSize(Long userId);

	Double findFeesFromClases(Long timeTableId);

	void invalidByClass(Long id);
}
