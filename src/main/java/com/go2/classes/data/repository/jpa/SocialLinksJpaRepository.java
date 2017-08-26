package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.SocialLinksEntity;

/**
 * Repository : SocialLinks.
 */
public interface SocialLinksJpaRepository extends PagingAndSortingRepository<SocialLinksEntity, Long> {

}
