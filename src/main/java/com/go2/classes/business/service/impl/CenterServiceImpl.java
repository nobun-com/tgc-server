package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.mapping.AddressServiceMapper;
import com.go2.classes.business.service.mapping.CenterServiceMapper;
import com.go2.classes.data.repository.jpa.AddressJpaRepository;
import com.go2.classes.data.repository.jpa.CenterJpaRepository;
import com.go2.classes.models.Center;
import com.go2.classes.models.ClassesSearch;
import com.go2.classes.models.jpa.AddressEntity;
import com.go2.classes.models.jpa.CenterEntity;

@Component
@Transactional
public class CenterServiceImpl implements CenterService {

	@Resource
	private CenterJpaRepository centerJpaRepository;

	@Resource
	private CenterServiceMapper centerServiceMapper;

	@Resource
	private AddressJpaRepository addressJpaRepository;

	@Resource
	private AddressServiceMapper addressServiceMapper;

	@Resource
	private AddressServiceImpl addressServiceImpl;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Center findById(Long id) {
		CenterEntity centerEntity = centerJpaRepository.findOne(id);
		return centerServiceMapper.mapCenterEntityToCenter(centerEntity);
	}

	@Override
	public List<Center> findAll() {
		Iterable<CenterEntity> entities = centerJpaRepository.findAll();
		List<Center> beans = new ArrayList<Center>();
		for (CenterEntity centerEntity : entities) {
			beans.add(centerServiceMapper.mapCenterEntityToCenter(centerEntity));
		}
		return beans;
	}

	@Override
	public Center save(Center center) {
		return update(center);
	}

	@Override
	public Center create(Center center) {
		CenterEntity centerEntity = null;
		if (center.getId() != null) {
			centerEntity = centerJpaRepository.findOne(center.getId());
		}
		if (centerEntity != null) {
			throw new IllegalStateException("already.exists");
		}
		centerEntity = new CenterEntity();

		if (!Objects.isNull(center.getAddress())) {
			AddressEntity addressEntity = new AddressEntity();
			addressServiceMapper.mapAddressToAddressEntity(center.getAddress(), addressEntity);
			addressJpaRepository.save(addressEntity);
			center.setAddress(addressServiceMapper.mapAddressEntityToAddress(addressEntity));
		}
		centerServiceMapper.mapCenterToCenterEntity(center, centerEntity);
		CenterEntity centerEntitySaved = centerJpaRepository.save(centerEntity);
		return centerServiceMapper.mapCenterEntityToCenter(centerEntitySaved);
	}

	@Override
	public Center update(Center center) {
		if (Objects.isNull(center.getId())) {
			throw new IllegalStateException("id.not.found");
		}
		CenterEntity centerEntity = centerJpaRepository.findOne(center.getId());
		if (Objects.isNull(centerEntity)) {
			throw new IllegalStateException("center.not.found.with.id." + center.getId());
		}
		if (!Objects.isNull(center.getAddress())) {
			center.setAddress(addressServiceImpl.update(center.getAddress()));
		}
		centerServiceMapper.mapCenterToCenterEntity(center, centerEntity);
		CenterEntity centerEntitySaved = centerJpaRepository.save(centerEntity);
		return centerServiceMapper.mapCenterEntityToCenter(centerEntitySaved);
	}

	@Override
	public void delete(Long id) {
		CenterEntity centerEntity = centerJpaRepository.findOne(id);
		if (Objects.isNull(centerEntity)) {
			throw new IllegalStateException("center.not.found.with.id." + id);
		}
		if (!Objects.isNull(centerEntity.getAddress())) {
			addressJpaRepository.delete(centerEntity.getAddress().getId());
			;
		}

		centerJpaRepository.delete(id);
	}

	public CenterJpaRepository getCenterJpaRepository() {
		return centerJpaRepository;
	}

	public void setCenterJpaRepository(CenterJpaRepository centerJpaRepository) {
		this.centerJpaRepository = centerJpaRepository;
	}

	public CenterServiceMapper getCenterServiceMapper() {
		return centerServiceMapper;
	}

	public void setCenterServiceMapper(CenterServiceMapper centerServiceMapper) {
		this.centerServiceMapper = centerServiceMapper;
	}

	@Override
	public List<Map<String, Object>> getCentersSearchResult(ClassesSearch classesSearch) {
		Query query = entityManager.createNativeQuery(classesSearch.getSearchByCenterQuery());
		List<Object[]> entities = query.getResultList();
		List<Map<String, Object>> beans = new ArrayList<Map<String, Object>>();
		for (Object[] entitie : entities) {
			beans.add(centerServiceMapper.mapResultToJSONMap(entitie));
		}
		return beans;
	}

	@Override
	public Integer getCentersCount() {
		return centerJpaRepository.getCentersCount();
	}

}
