/*
 * Created on 17 Jul 2017 ( Time 15:08:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.PromoService;
import com.go2.classes.business.service.mapping.PromoServiceMapper;
import com.go2.classes.data.repository.jpa.PromoJpaRepository;
import com.go2.classes.models.Promo;
import com.go2.classes.models.jpa.PromoEntity;

/**
 * Implementation of PromoService
 */
@Component
@Transactional
public class PromoServiceImpl implements PromoService {

	@Resource
	private PromoJpaRepository promoJpaRepository;

	@Resource
	private PromoServiceMapper promoServiceMapper;

	@Override
	public Promo findById(Long id) {
		PromoEntity promoEntity = promoJpaRepository.findOne(id);
		return promoServiceMapper.mapPromoEntityToPromo(promoEntity);
	}

	@Override
	public List<Promo> findAll() {
		Iterable<PromoEntity> entities = promoJpaRepository.findAll();
		List<Promo> beans = new ArrayList<Promo>();
		for (PromoEntity promoEntity : entities) {
			beans.add(promoServiceMapper.mapPromoEntityToPromo(promoEntity));
		}
		return beans;
	}

	@Override
	public Promo save(Promo promo) {
		return update(promo);
	}

	@Override
	public Promo create(Promo promo) {
		PromoEntity promoEntity =  new PromoEntity();
		promoServiceMapper.mapPromoToPromoEntity(promo, promoEntity);
		PromoEntity promoEntitySaved = promoJpaRepository.save(promoEntity);
		return promoServiceMapper.mapPromoEntityToPromo(promoEntitySaved);
	}

	@Override
	public Promo update(Promo promo) {
		PromoEntity promoEntity = promoJpaRepository.findOne(promo.getId());
		promoServiceMapper.mapPromoToPromoEntity(promo, promoEntity);
		PromoEntity promoEntitySaved = promoJpaRepository.save(promoEntity);
		return promoServiceMapper.mapPromoEntityToPromo(promoEntitySaved);
	}

	@Override
	public void delete(Long id) {
		promoJpaRepository.delete(id);
	}

	public PromoJpaRepository getPromoJpaRepository() {
		return promoJpaRepository;
	}

	public void setPromoJpaRepository(PromoJpaRepository promoJpaRepository) {
		this.promoJpaRepository = promoJpaRepository;
	}

	public PromoServiceMapper getPromoServiceMapper() {
		return promoServiceMapper;
	}

	public void setPromoServiceMapper(PromoServiceMapper promoServiceMapper) {
		this.promoServiceMapper = promoServiceMapper;
	}

}
