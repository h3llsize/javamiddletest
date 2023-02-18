package org.shabbydev.test.middlejavadevelopertest.logic.service;

import org.apache.catalina.User;
import org.shabbydev.test.middlejavadevelopertest.data.entity.AuthorizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.repo.AuthorizationRepository;
import org.shabbydev.test.middlejavadevelopertest.logic.token.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * I know that this service has bad realisation, but I haven't time to
 * make is good, sry :(
 */
@Service
public class ValidateService {

    private final AuthorizationRepository authorizationRepository;

    private final TokenGenerator tokenGenerator;

    public ValidateService(AuthorizationRepository authorizationRepository, TokenGenerator tokenGenerator) {
        this.authorizationRepository = authorizationRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public boolean validate(String token, String remoteAddr) {
        AuthorizationEntity authorization = authorizationRepository.findByHash(token);

        return authorization.getHash().equals(
                tokenGenerator.MD5(authorization.getUser().getEmail() + ":" + authorization.getUser().getPassword() + ":" +
                        remoteAddr
        ));
    }

    public UserEntity findByHashToken(String token) {
        return authorizationRepository.findByHash(token).getUser();
    }
}
