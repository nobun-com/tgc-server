package com.go2.classes.business.service;

import java.util.List;
import java.util.Map;

import com.go2.classes.models.ChildInterests;

public interface ChildInterestsService { 

	ChildInterests findById( Long id  ) ;

	List<ChildInterests> findAll();

	ChildInterests save(ChildInterests entity);

	ChildInterests update(ChildInterests entity);

	ChildInterests create(ChildInterests entity);

	void delete( Long id );
	
	List<ChildInterests> getAllChildInterestsByChild(Long childId);
	
	Map<String, List<String>> getAllChildInterestsByChild();
}
