package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findAllByOrganizationEntity(OrganizationEntity organization, Pageable pageable);

}
