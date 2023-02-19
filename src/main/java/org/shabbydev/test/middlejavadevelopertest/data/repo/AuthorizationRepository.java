package org.shabbydev.test.middlejavadevelopertest.data.repo;

import org.shabbydev.test.middlejavadevelopertest.data.entity.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, Long> {
    AuthorizationEntity findByHash(String hash);
    boolean existsByHash(String hash);
}
