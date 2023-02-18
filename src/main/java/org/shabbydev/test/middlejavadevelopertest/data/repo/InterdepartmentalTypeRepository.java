package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalTypeDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterdepartmentalTypeRepository extends JpaRepository<InterdepartmentalTypeEntity, Long> {
    Page<InterdepartmentalTypeEntity> findAll(Pageable pageable);
}
