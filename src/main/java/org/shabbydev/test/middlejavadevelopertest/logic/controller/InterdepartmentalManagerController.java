package org.shabbydev.test.middlejavadevelopertest.logic.controller;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.*;
import org.shabbydev.test.middlejavadevelopertest.logic.service.InterdepartmentalManagerService;
import org.shabbydev.test.middlejavadevelopertest.logic.service.ValidateService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/idm")
@RestController
public class InterdepartmentalManagerController {

    private final InterdepartmentalManagerService interdepartmentalManagerService;

    private final ValidateService validateService;

    public InterdepartmentalManagerController(InterdepartmentalManagerService interdepartmentalManagerService, ValidateService validateService) {
        this.interdepartmentalManagerService = interdepartmentalManagerService;
        this.validateService = validateService;
    }

    @GetMapping("/get-user-responsible")
    public List<UserResponsibleRequestDTO> userResponsibleRequestDTOS(HttpServletRequest httpServletRequest) {

        return interdepartmentalManagerService.getUserResponsible(httpServletRequest.getHeader("Authorization"));
    }

    @PostMapping("/send-to-smev")
    public ResponseEntity<String> sendToSmev(@RequestBody InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        return interdepartmentalManagerService.save(interdepartmentalRequestDTO);
    }

    @GetMapping("/type-list")
    public Page<InterdepartmentalTypeDTO> getTypes(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;


        return interdepartmentalManagerService.findAll();
    }

    @GetMapping("/accept")
    public ResponseEntity<String> acceptIDMRequest(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.acceptIDMRequest(Long.parseLong(id));
    }

    @PostMapping("/create-idm")
    public ResponseEntity<String> createIdm(@RequestBody InterdepartmentalRequestDTO interdepartmentalRequestDTO, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;
        return interdepartmentalManagerService.createIdm(interdepartmentalRequestDTO);
    }

    @PostMapping("/create-ms")
    public ResponseEntity<String> createMunicipalServ(@RequestBody MunicipalServDTO municipalServDTO, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;
        return interdepartmentalManagerService.createMunicipalServ(municipalServDTO);
    }

    @GetMapping("/users")
    public Page<UserDtoRequest> getAllUsers(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findAllUsers();
    }

    @GetMapping("/get-staff")
    public Set<UserDTO> getStaff(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findAllStaffByOrg(httpServletRequest.getHeader("Authorization"));
    }

    @GetMapping("/get-all-wait-success")
    public Page<InterdepartmentalRequestDTO> getAllWhoWaitToSuccess(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        if(!validateService.hasAccess(httpServletRequest.getHeader("Authorization")))
            return null;

        Page<InterdepartmentalRequestDTO> interdepartmentalRequestDTOS = interdepartmentalManagerService.getAllWhoWaitForSuccess();

        return interdepartmentalManagerService.getAllWhoWaitForSuccess();
    }

    @GetMapping("/accounting")
    public Page<MunicipalServDTO> municipalServDTOS(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.hasRequest();
    }

    @GetMapping("/")
    public Page<MunicipalServDTO> getAllServByOrganization(HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findAllByOrganization(httpServletRequest.getHeader("Authorization"));
    }

    @GetMapping("/{id}")
    private MunicipalServDTO findById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        if(!validateService.validate(httpServletRequest.getHeader("Authorization"), httpServletRequest.getRemoteAddr()))
            return null;

        return interdepartmentalManagerService.findById(id);
    }

}
