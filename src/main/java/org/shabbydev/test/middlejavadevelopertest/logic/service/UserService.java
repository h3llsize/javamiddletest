package org.shabbydev.test.middlejavadevelopertest.logic.service;

import org.apache.catalina.User;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        return userRepository.save(user);
    }

    public boolean validateLogin(UserDTO userDTO) {
        return findByEmail(userDTO.getEmail()).getPassword().equals(userDTO.getPassword());
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }
}
