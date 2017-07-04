package com.go2.classes.business.service;

import java.util.List;
import java.util.Map;

import com.go2.classes.models.Center;
import com.go2.classes.models.ClassesSearch;

public interface CenterService { 

	Center findById( Long id  ) ;

	List<Center> findAll();

	Center save(Center entity);

	Center update(Center entity);

	Center create(Center entity);

	void delete( Long id );

	List<Map<String, Object>> getCentersSearchResult(ClassesSearch classesSearch);
}
