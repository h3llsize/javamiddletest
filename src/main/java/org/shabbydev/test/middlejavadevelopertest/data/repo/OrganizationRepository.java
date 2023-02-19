package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    boolean existsByInn(String INN);
    boolean existsByName(String name);

    Optional<OrganizationEntity> findByOwner(UserEntity user);

}
