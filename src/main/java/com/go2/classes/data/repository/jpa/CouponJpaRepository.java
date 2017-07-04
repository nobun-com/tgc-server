package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.CouponEntity;

public interface CouponJpaRepository extends PagingAndSortingRepository<CouponEntity, String> {

	@Query(value = "select * from coupon where status != 'Expired'", nativeQuery = true)
	Iterable<CouponEntity> findAllNonExpired();

}
