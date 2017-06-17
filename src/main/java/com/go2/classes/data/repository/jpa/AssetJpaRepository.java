package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.AssetEntity;

public interface AssetJpaRepository extends PagingAndSortingRepository<AssetEntity, Long> {

}
