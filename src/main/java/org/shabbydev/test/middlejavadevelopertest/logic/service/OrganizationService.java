package org.shabbydev.test.middlejavadevelopertest.logic.service;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.OrganizationDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.OrganizationMapper;
import org.shabbydev.test.middlejavadevelopertest.data.repo.OrganizationRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.UserRepository;
import org.shabbydev.test.middlejavadevelopertest.logic.utils.AuthHeadersGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    private final ValidateService validateService;

    private final UserRepository userRepository;

    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, ValidateService validateService, UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.validateService = validateService;
        this.userRepository = userRepository;
    }

    public OrganizationEntity save(OrganizationEntity organization) {
        if(organizationRepository.existsByName(organization.getName()) || organizationRepository.existsByInn(organization.getInn()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        return organizationRepository.save(organization);
    }

    public ResponseEntity<String> create(OrganizationDTO organizationDTO, String token) {
        if(validateService.findByHashToken(token).getOrganizationEntity() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User have organization");

        userRepository.save(validateService.findByHashToken(token).organization(
                save(organizationMapper.toEntity(organizationDTO).owner(validateService.findByHashToken(token)))));

        return ResponseEntity.ok("Successful created organization");
    }

    public Page<OrganizationDTO> getAllOrganization() {
        return organizationRepository.findAll(Pageable.unpaged()).map(organizationMapper::toDTO);
    }

    public ResponseEntity<String> addStaff(UserDTO userDTO, String token) {
        organizationRepository.findByOwner(validateService.findByHashToken(token)).orElseThrow(() ->
                HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Organization not found",
                new AuthHeadersGenerator(token), null, null)).getStaff().add(userRepository.findById(userDTO.getId()).orElse(null));

        return ResponseEntity.ok("User was successful added");
    }
}
