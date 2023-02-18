package org.shabbydev.test.middlejavadevelopertest.logic.token;
import lombok.SneakyThrows;
import org.shabbydev.test.middlejavadevelopertest.data.entity.AuthorizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.repo.AuthorizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Service
public class TokenGenerator {
    private final static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);

    private final AuthorizationRepository authorizationRepository;

    public TokenGenerator(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public String genToken(UserEntity user, String remoteAddr) {
        logger.debug("Request to generate token...");
        return MD5(user.getEmail() + ":" + user.getPassword() + ":" + remoteAddr);
    }

    public AuthorizationEntity genAuth(UserEntity user, String remoteAddr) {

        if(authorizationRepository.existsByHash(genToken(user, remoteAddr)))
            authorizationRepository.delete(authorizationRepository.findByHash(genToken(user, remoteAddr)));

        AuthorizationEntity authorization = new AuthorizationEntity();
        authorization.setHash(genToken(user, remoteAddr));
        authorization.setUser(user);

        return authorizationRepository.save(authorization);
    }

    @SneakyThrows
    public String MD5(String data) {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(data.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
