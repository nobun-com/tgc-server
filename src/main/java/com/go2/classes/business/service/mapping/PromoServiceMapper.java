/*
 * Created on 17 Jul 2017 ( Time 15:08:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.Promo;
import com.go2.classes.models.jpa.CenterEntity;
import com.go2.classes.models.jpa.PromoEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class PromoServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;

	/**
	 * Constructor.
	 */
	public PromoServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'PromoEntity' to 'Promo'
	 * 
	 * @param promoEntity
	 */
	public Promo mapPromoEntityToPromo(PromoEntity promoEntity) {
		if (promoEntity == null) {
			return null;
		}

		// --- Generic mapping
		Promo promo = map(promoEntity, Promo.class);

		return promo; 
	}

	/**
	 * Mapping from 'Promo' to 'PromoEntity'
	 * 
	 * @param promo
	 * @param promoEntity
	 */
	public void mapPromoToPromoEntity(Promo promo, PromoEntity promoEntity) {
		if (promo == null) {
			return;
		}

		// --- Generic mapping
		map(promo, promoEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}