package org.shabbydev.test.middlejavadevelopertest.logic.controller;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalRequestDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalTypeDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.MunicipalServDTO;
import org.shabbydev.test.middlejavadevelopertest.logic.service.InterdepartmentalManagerService;
import org.shabbydev.test.middlejavadevelopertest.logic.service.ValidateService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/idm/")
@RestController
public class InterdepartmentalManagerController {

    private final InterdepartmentalManagerService interdepartmentalManagerService;

    private final ValidateService validateService;

    public InterdepartmentalManagerController(InterdepartmentalManagerService interdepartmentalManagerService, ValidateService validateService) {
        this.interdepartmentalManagerService = interdepartmentalManagerService;
        this.validateService = validateService;
    }

    @PostMapping("send-to-smev")
    public ResponseEntity<String> sendToSmev(@RequestBody InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        return interdepartmentalManagerService.save(interdepartmentalRequestDTO);
    }

    @GetMapping("/type-list")
    public Page<InterdepartmentalTypeDTO> getTypes(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;


        return interdepartmentalManagerService.findAll();
    }

    @GetMapping("/accounting")
    public Page<MunicipalServDTO> municipalServDTOS(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.hasRequest();
    }

    @GetMapping
    public Page<MunicipalServDTO> getAllServByOrganization(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findAllByOrganization(httpServletRequest.getHeader("Authorization"));
    }

    @GetMapping("{id}")
    private MunicipalServDTO findById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findById(id);
    }

}
