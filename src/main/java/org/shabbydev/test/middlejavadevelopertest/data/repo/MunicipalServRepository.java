package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalServRepository extends JpaRepository<MunicipalServ, Long> {
    @Query(
            "select ms from MunicipalServ ms" +
                    " where ms.interdepartmentalRequestEntities.size > 0"
    )
    Page<MunicipalServ> findAllHasRequest(Pageable pageable);

    Page<MunicipalServ> findAllByOrganizationEntity(OrganizationEntity organization, Pageable pageable);

    @Query("select ms from MunicipalServ ms join ms.interdepartmentalRequestEntities ir " +
            "where ir.author = :user")
    List<MunicipalServ> findAllByResponsibleUser(UserEntity user);

    @Query("select ms from MunicipalServ ms " +
            "where ms.interdepartmentalRequestEntities.size > 0")
    Page<MunicipalServ> findMsWithRequests(Pageable pageable);
}
