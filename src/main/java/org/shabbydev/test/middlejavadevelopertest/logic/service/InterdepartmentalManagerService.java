package org.shabbydev.test.middlejavadevelopertest.logic.service;

import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalRequestDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalTypeDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.MunicipalServDTO;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.InterdepartmentalRequestMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.InterdepartmentalTypeMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.MunicipalServMapper;
import org.shabbydev.test.middlejavadevelopertest.data.repo.InterdepartmentalRequestRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.InterdepartmentalTypeRepository;
import org.shabbydev.test.middlejavadevelopertest.data.repo.MunicipalServRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InterdepartmentalManagerService {
    private final InterdepartmentalRequestRepository interdepartmentalRequestRepository;

    private final InterdepartmentalRequestMapper interdepartmentalRequestMapper;

    private final InterdepartmentalTypeRepository interdepartmentalTypeRepository;

    private final InterdepartmentalTypeMapper interdepartmentalTypeMapper;

    private final MunicipalServRepository municipalServRepository;

    private final MunicipalServMapper municipalServMapper;

    public InterdepartmentalManagerService(InterdepartmentalRequestRepository interdepartmentalRequestRepository, InterdepartmentalRequestMapper interdepartmentalRequestMapperDecorator, InterdepartmentalTypeRepository interdepartmentalTypeRepository, InterdepartmentalTypeMapper interdepartmentalTypeMapper, MunicipalServRepository municipalServRepository, MunicipalServMapper municipalServMapper) {
        this.interdepartmentalRequestRepository = interdepartmentalRequestRepository;
        this.interdepartmentalRequestMapper = interdepartmentalRequestMapperDecorator;
        this.interdepartmentalTypeRepository = interdepartmentalTypeRepository;
        this.interdepartmentalTypeMapper = interdepartmentalTypeMapper;
        this.municipalServRepository = municipalServRepository;
        this.municipalServMapper = municipalServMapper;
    }

    public Page<InterdepartmentalTypeDTO> findAll() {
        return interdepartmentalTypeRepository.findAll(Pageable.unpaged()).map(interdepartmentalTypeMapper::toDTO);
    }

    public ResponseEntity<String> save(InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        interdepartmentalRequestRepository.save(interdepartmentalRequestMapper.toEntity(interdepartmentalRequestDTO));
        return ResponseEntity.ok("Межведомственный запрос был успешно добавлен!");
    }

    public Page<MunicipalServDTO> hasRequest() {
        return municipalServRepository.findAllHasRequest(Pageable.unpaged()).map(municipalServMapper::toDTO);
    }
}
