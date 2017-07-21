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
	 * @param promoEntity
	 */
	public Promo mapPromoEntityToPromo(PromoEntity promoEntity) {
		if(promoEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Promo promo = map(promoEntity, Promo.class);

		//--- Link mapping ( link to Category )
		if(promoEntity.getCenterOne() != null) {
			promo.setCenterOneId(promoEntity.getCenterOne().getId());
		}
		//--- Link mapping ( link to Category )
		if(promoEntity.getCenterTwo() != null) {
			promo.setCenterTwoId(promoEntity.getCenterTwo().getId());
		}
		//--- Link mapping ( link to Category )
		if(promoEntity.getCenterThree() != null) {
			promo.setCenterThreeId(promoEntity.getCenterThree().getId());
		}
		//--- Link mapping ( link to Category )
		if(promoEntity.getCenterFour() != null) {
			promo.setCenterFourId(promoEntity.getCenterFour().getId());
		}
		return promo;
	}
	
	/**
	 * Mapping from 'Promo' to 'PromoEntity'
	 * @param promo
	 * @param promoEntity
	 */
	public void mapPromoToPromoEntity(Promo promo, PromoEntity promoEntity) {
		if(promo == null) {
			return;
		}


		//--- Link mapping ( link : promo )
		if( hasLinkToCenterOne(promo) ) {
			CenterEntity centerOne = new CenterEntity();
			centerOne.setId( promo.getCenterOneId() );
			promoEntity.setCenterOne( centerOne );
		} else {
			promoEntity.setCenterOne( null );
		}

		//--- Link mapping ( link : promo )
		if( hasLinkToCenterTwo(promo) ) {
			CenterEntity centerTwo = new CenterEntity();
			centerTwo.setId( promo.getCenterTwoId() );
			promoEntity.setCenterTwo( centerTwo );
		} else {
			promoEntity.setCenterOne( null );
		}

		//--- Link mapping ( link : promo )
		if( hasLinkToCenterThree(promo) ) {
			CenterEntity centerThree = new CenterEntity();
			centerThree.setId( promo.getCenterThreeId() );
			promoEntity.setCenterThree( centerThree );
		} else {
			promoEntity.setCenterThree( null );
		}

		//--- Link mapping ( link : promo )
		if( hasLinkToCenterFour(promo) ) {
			CenterEntity centerFour = new CenterEntity();
			centerFour.setId( promo.getCenterFourId() );
			promoEntity.setCenterFour( centerFour );
		} else {
			promoEntity.setCenterFour( null );
		}

		//--- Generic mapping 
		map(promo, promoEntity);
	}
	
	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean. 
	 */
	private boolean hasLinkToCenterFour(Promo promo) {
		if(promo.getCenterFourId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean
	 */
	private boolean hasLinkToCenterOne(Promo promo) {
		if(promo.getCenterOneId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean
	 */
	private boolean hasLinkToCenterTwo(Promo promo) {
		if(promo.getCenterTwoId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean
	 */
	private boolean hasLinkToCenterThree(Promo promo) {
		if(promo.getCenterThreeId() != null) {
			return true;
		}
		return false;
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