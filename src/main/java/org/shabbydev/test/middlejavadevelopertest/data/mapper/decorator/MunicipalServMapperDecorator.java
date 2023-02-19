package org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.MunicipalServDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.InterdepartmentalDocumentMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.InterdepartmentalRequestMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = OrganizationMapper.class)
public abstract class MunicipalServMapperDecorator {

    @Autowired
    private InterdepartmentalRequestMapper interdepartmentalRequestMapper;

    @Autowired
    private InterdepartmentalDocumentMapper interdepartmentalDocumentMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @AfterMapping
    public void toDTO(MunicipalServ municipalServ, @MappingTarget MunicipalServDTO municipalServDTO) {
        if(municipalServ.getInterdepartmentalDocuments() != null)
            municipalServDTO.setInterdepartmentalDocuments(municipalServ.getInterdepartmentalDocuments()
                .stream().map(interdepartmentalDocumentMapper::toDTO).collect(Collectors.toSet()));

        if(municipalServ.getInterdepartmentalRequestEntities() != null)
            municipalServDTO.setInterdepartmentalRequestEntities(municipalServ.getInterdepartmentalRequestEntities()
                .stream().map(interdepartmentalRequestMapper::toDTO).collect(Collectors.toSet()));

        municipalServDTO.setOrganization(organizationMapper.toDTO(municipalServ.getOrganizationEntity()));
    }

    @AfterMapping
    public void toEntity(MunicipalServDTO municipalServDTO, @MappingTarget MunicipalServ municipalServ) {
        municipalServ.setTime(LocalDateTime.parse(municipalServDTO.getTime(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US)).toInstant(ZoneOffset.UTC));
        municipalServ.setOrganizationEntity(organizationMapper.toEntity(municipalServDTO.getOrganization()));
    }
}
