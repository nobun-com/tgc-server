package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.CenterEntity;

public interface CenterJpaRepository extends PagingAndSortingRepository<CenterEntity, Long> {

}
