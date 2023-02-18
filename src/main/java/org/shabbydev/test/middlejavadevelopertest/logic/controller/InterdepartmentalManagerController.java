package org.shabbydev.test.middlejavadevelopertest.logic.controller;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalRequestDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalTypeDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.MunicipalServDTO;
import org.shabbydev.test.middlejavadevelopertest.logic.service.InterdepartmentalManagerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/idm/")
@RestController
public class InterdepartmentalManagerController {

    private final InterdepartmentalManagerService interdepartmentalManagerService;

    public InterdepartmentalManagerController(InterdepartmentalManagerService interdepartmentalManagerService) {
        this.interdepartmentalManagerService = interdepartmentalManagerService;
    }

    @PostMapping("send-to-smev")
    public ResponseEntity<String> sendToSmev(@RequestBody InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        return interdepartmentalManagerService.save(interdepartmentalRequestDTO);
    }

    @GetMapping("/type-list")
    public Page<InterdepartmentalTypeDTO> getTypes() {
        return interdepartmentalManagerService.findAll();
    }

    @GetMapping("/accounting")
    public Page<MunicipalServDTO> municipalServDTOS() {
        return interdepartmentalManagerService.hasRequest();
    }
}
