package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.UserEntity;

public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

}
