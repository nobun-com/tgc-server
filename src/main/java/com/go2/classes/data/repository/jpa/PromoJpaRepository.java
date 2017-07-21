package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.PromoEntity;


/**
 * Repository : Promo.
 */
public interface PromoJpaRepository extends PagingAndSortingRepository<PromoEntity, Long> {

}
