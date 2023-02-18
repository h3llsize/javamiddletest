package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalRequestEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterdepartmentalRequestRepository extends JpaRepository<InterdepartmentalRequestEntity, Long> {
//    @Query("select ir from InterdepartmentalRequestEntity ir , MunicipalServ ms " +
//            "where ms.organizationEntity = :organization " +
//            "and ir = ANY (ms.interdepartmentalRequestEntities)")
//    Page<InterdepartmentalRequestEntity> findAllByOrganization(OrganizationEntity organization, Pageable pageable);
}
