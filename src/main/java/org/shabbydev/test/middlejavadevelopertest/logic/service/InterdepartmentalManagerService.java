package org.shabbydev.test.middlejavadevelopertest.logic.service;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.*;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalRequestEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.*;
import org.shabbydev.test.middlejavadevelopertest.data.repo.InterdepartmentalRequestRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.InterdepartmentalTypeRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.MunicipalServRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InterdepartmentalManagerService {
    private final InterdepartmentalRequestRepository interdepartmentalRequestRepository;

    private final InterdepartmentalRequestMapper interdepartmentalRequestMapper;

    private final InterdepartmentalTypeRepository interdepartmentalTypeRepository;

    private final InterdepartmentalTypeMapper interdepartmentalTypeMapper;

    private final MunicipalServRepository municipalServRepository;

    private final MunicipalServMapper municipalServMapper;

    private final ValidateService validateService;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserRequestMapper userRequestMapper;

    private final OrganizationMapper organizationMapper;

    public InterdepartmentalManagerService(InterdepartmentalRequestRepository interdepartmentalRequestRepository, InterdepartmentalRequestMapper interdepartmentalRequestMapperDecorator, InterdepartmentalTypeRepository interdepartmentalTypeRepository, InterdepartmentalTypeMapper interdepartmentalTypeMapper, MunicipalServRepository municipalServRepository, MunicipalServMapper municipalServMapper, ValidateService validateService, UserMapper userMapper, UserRepository userRepository, UserRequestMapper userRequestMapper, OrganizationMapper organizationMapper) {
        this.interdepartmentalRequestRepository = interdepartmentalRequestRepository;
        this.interdepartmentalRequestMapper = interdepartmentalRequestMapperDecorator;
        this.interdepartmentalTypeRepository = interdepartmentalTypeRepository;
        this.interdepartmentalTypeMapper = interdepartmentalTypeMapper;
        this.municipalServRepository = municipalServRepository;
        this.municipalServMapper = municipalServMapper;
        this.validateService = validateService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userRequestMapper = userRequestMapper;
        this.organizationMapper = organizationMapper;
    }

    public List<UserResponsibleRequestDTO> getUserResponsible(String token) {
        UserEntity user = validateService.findByHashToken(token);

        List<MunicipalServ> municipalServs = municipalServRepository.findAllByResponsibleUser(user);
        List<UserResponsibleRequestDTO> userResponsibleRequestDTOS = municipalServs.stream().map(municipalServ -> {
            UserResponsibleRequestDTO userResponsibleRequestDTO = new UserResponsibleRequestDTO();
            userResponsibleRequestDTO.setOrganizationDTO(organizationMapper.toDTO(municipalServ.getOrganizationEntity()));
            userResponsibleRequestDTO.setInterdepartmentalRequestDTO(municipalServ
                    .getInterdepartmentalRequestEntities().stream().filter(irq -> irq.getAuthor().getId().equals(user.getId()))
                    .map(interdepartmentalRequestMapper::toDTO).collect(Collectors.toList()));
            return userResponsibleRequestDTO;
        }).collect(Collectors.toList());

        return userResponsibleRequestDTOS;
    }

    public Page<MunicipalServDTO> findMsWithRequests() {
        return municipalServRepository.findMsWithRequests(Pageable.unpaged()).map(municipalServMapper::toDTO);
    }

    public Page<InterdepartmentalTypeDTO> findAll() {
        return interdepartmentalTypeRepository.findAll(Pageable.unpaged()).map(interdepartmentalTypeMapper::toDTO);
    }

    public Page<InterdepartmentalRequestDTO> getAllWhoWaitForSuccess() {
        return interdepartmentalRequestRepository.findAllByStatus(0, Pageable.unpaged()).map(interdepartmentalRequestMapper::toDTO);
    }

    public ResponseEntity<String> acceptIDMRequest(Long id) {
       interdepartmentalRequestRepository.save(interdepartmentalRequestRepository.findById(id).orElseGet(null).status(1));
        return ResponseEntity.ok("Статус запроса изменён");
    }

    public ResponseEntity<String> save(InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        interdepartmentalRequestDTO.setStatus(0);
        interdepartmentalRequestDTO.setDate(Instant.now());
        interdepartmentalRequestRepository.save(interdepartmentalRequestMapper.toEntity(interdepartmentalRequestDTO));
        return ResponseEntity.ok("Межведомственный запрос был успешно добавлен!");
    }

    public Page<MunicipalServDTO> hasRequest() {
        return municipalServRepository.findAllHasRequest(Pageable.unpaged()).map(municipalServMapper::toDTO);
    }

    public Page<MunicipalServDTO> findAllByOrganization(String token) {
        OrganizationEntity organization = validateService.findByHashToken(token).getOrganizationEntity();

        if(organization == null)
            return null;

        Page<MunicipalServDTO> municipalServDTOS = municipalServRepository.findAllByOrganizationEntity(organization, Pageable.unpaged())
                .map(municipalServMapper::toDTO);

        for(MunicipalServDTO municipalServ : municipalServDTOS) {
            municipalServ.setInterdepartmentalRequestEntities(municipalServ.getInterdepartmentalRequestEntities()
                    .stream().filter(ir -> ir.getStatus() > 0).collect(Collectors.toSet()));
        }

        return municipalServDTOS;
    }

    public ResponseEntity<String> createIdm(InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        interdepartmentalRequestDTO.setDate(Instant.now());

        InterdepartmentalRequestEntity interdepartmentalRequestEntity = interdepartmentalRequestRepository.save(interdepartmentalRequestMapper.toEntity(interdepartmentalRequestDTO)
                .user(userRepository.findById(interdepartmentalRequestDTO.getUser().getId()).orElse(null)));

        municipalServRepository.save(municipalServRepository.findById(interdepartmentalRequestDTO.getMunicipalServ()
                .getId()).orElse(null).addRequest(interdepartmentalRequestEntity));


        userRepository.save(userRepository.findById(interdepartmentalRequestDTO.getUser().getId())
                .orElseGet(null).interdepartmentalRequest(interdepartmentalRequestEntity));

        return ResponseEntity.ok("Success created");
    }

    public ResponseEntity<String> createMunicipalServ(MunicipalServDTO municipalServDTO) {

        municipalServRepository.save(municipalServMapper.toEntity(municipalServDTO));
        return ResponseEntity.ok("Success sended");
    }

    public Page<UserDtoRequest> findAllUsers() {
        return userRepository.findAll(Pageable.unpaged()).map(userRequestMapper::toDto);
    }

    public Set<UserDTO> findAllStaffByOrg(String token) {
        UserEntity user = validateService.findByHashToken(token);
        return user.getOrganizationEntity().getStaff().stream().map(userMapper::toDTO).collect(Collectors.toSet());
    }

    public MunicipalServDTO findById(String id) {
        return municipalServRepository.findById(Long.valueOf(id)).map(municipalServMapper::toDTO).orElse(null);
    }
}
