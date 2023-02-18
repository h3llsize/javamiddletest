package org.shabbydev.test.middlejavadevelopertest.logic.controller;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.OrganizationDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.OrganizationMapper;
import org.shabbydev.test.middlejavadevelopertest.logic.service.OrganizationService;
import org.shabbydev.test.middlejavadevelopertest.logic.service.ValidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("api/org/")
@RestController
public class OrganizationController {

    private final ValidateService validateService;

    private final OrganizationService organizationService;

    public OrganizationController(ValidateService validateService, OrganizationService organizationService) {
        this.validateService = validateService;
        this.organizationService = organizationService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationDTO organizationDTO, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad token");

        return organizationService.create(organizationDTO, httpServletRequest.getHeader("Authorization"));
    }

    @PostMapping("add-staff")
    public ResponseEntity<String> addStaff(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad token");

        return organizationService.addStaff(userDTO, httpServletRequest.getHeader("Authorization"));
    }
}
