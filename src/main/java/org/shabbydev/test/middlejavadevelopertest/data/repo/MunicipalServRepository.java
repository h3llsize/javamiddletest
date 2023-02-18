package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalServRepository extends JpaRepository<MunicipalServ, Long> {
    @Query(
            "select ms from MunicipalServ ms" +
                    " where ms.interdepartmentalRequestEntities.size > 0"
    )
    Page<MunicipalServ> findAllHasRequest(Pageable pageable);
}
