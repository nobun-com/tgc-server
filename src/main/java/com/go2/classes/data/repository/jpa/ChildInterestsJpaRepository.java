package com.go2.classes.data.repository.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ChildInterestsEntity;

public interface ChildInterestsJpaRepository extends PagingAndSortingRepository<ChildInterestsEntity, Long> {

	List<ChildInterestsEntity> findAllChildInterestsByChildId(Long childId);

	@Query(value = "select cc.category from child_interests ci inner join classes_category cc on ci.classes_category_id=cc.id where ci.child_id =:childId)", nativeQuery = true)
	List<String> findAllChildInterestsByChild(@Param("childId") Long childId);

    @Transactional
    @Modifying
    @Query(value = "delete from child_interests where child_id = :childId", nativeQuery = true)
	void deleteAllByChildId(@Param("childId") Long childId);

}
