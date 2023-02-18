package org.shabbydev.test.middlejavadevelopertest.logic.service;

import javax.transaction.Transactional;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.AuthorizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.UserMapper;
import org.shabbydev.test.middlejavadevelopertest.data.repo.AuthorizationRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.UserRepository;
import org.shabbydev.test.middlejavadevelopertest.logic.token.TokenGenerator;
import org.shabbydev.test.middlejavadevelopertest.logic.utils.AuthHeadersGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegisterService {

    private final UserService userService;

    private final UserMapper userMapper;

    private final TokenGenerator tokenGenerator;

    private final AuthorizationRepository authorizationRepository;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    public RegisterService(UserService userService, UserMapper userMapper, TokenGenerator tokenGenerator, AuthorizationRepository authorizationRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenGenerator = tokenGenerator;
        this.authorizationRepository = authorizationRepository;
    }

    // I don't save users passwords in MD5 hash
    public ResponseEntity<String> register(UserDTO userDTO, String remoteAddr) {

        String hash = tokenGenerator.genAuth(
                userService.save(userMapper.toEntity(userDTO)),
                remoteAddr
        ).getHash();

        return ResponseEntity.ok()
                .headers(new AuthHeadersGenerator(hash))
                .body("User was successful saved");
    }

    public ResponseEntity<String> login(UserDTO userDTO, String remoteAddr) {

        return userService.validateLogin(userDTO) ? ResponseEntity.ok().headers(new AuthHeadersGenerator(
                tokenGenerator.genAuth(userService.findByEmail(userDTO.getEmail()), remoteAddr).getHash()
        )).body("Login was successful") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad login");
    }
}
