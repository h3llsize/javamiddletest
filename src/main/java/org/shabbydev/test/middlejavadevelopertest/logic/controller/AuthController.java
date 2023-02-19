package org.shabbydev.test.middlejavadevelopertest.logic.controller;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.Authorization;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.logic.service.RegisterService;
import org.shabbydev.test.middlejavadevelopertest.logic.service.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(RegisterService registerService, ValidateService validateService) {
        this.registerService = registerService;
        this.validateService = validateService;
    }
    private final RegisterService registerService;

    private final ValidateService validateService;

    @PostMapping("register")
    public ResponseEntity<Authorization> register(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        logger.debug("Request to register user : " + userDTO.getEmail());
        return registerService.register(userDTO, httpServletRequest.getRemoteAddr());
    }

    @GetMapping("validate")
    public ResponseEntity<String> validate(HttpServletRequest httpServletRequest) {
        return validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()) ?
                ResponseEntity.ok("Validate was successful") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad token");
    }

    @PostMapping("login")
    public ResponseEntity<Authorization> login (@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        logger.debug("Request to login user : " + userDTO.getEmail());
        return registerService.login(userDTO, httpServletRequest.getRemoteAddr());
    }

    @GetMapping("access")
    public ResponseEntity<String> hasAccess(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        if(validateService.hasAccess(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad token");

        return ResponseEntity.ok("Success");
    }

}
