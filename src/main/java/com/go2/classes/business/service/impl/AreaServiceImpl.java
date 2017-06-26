package com.go2.classes.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import java.util.LinkedHashMap;
import com.go2.classes.business.service.AreaService;
import com.go2.classes.business.service.mapping.AreaServiceMapper;
import com.go2.classes.data.repository.jpa.AreaJpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AreaServiceImpl implements AreaService {

	@Resource
	private AreaJpaRepository areaJpaRepository;

	@Resource
	private AreaServiceMapper areaServiceMapper;

	@Override
	public Map<String, List<String>> getDistrictByArea() {
		List<String> areas = areaJpaRepository.getAllDistinctArea();
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
		result.put("allAreas", areas);
		for(String area : areas) {
			result.put(area, areaJpaRepository.getAllDistricts(area));
		}
		return result;
	}
	
}
