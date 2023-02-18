package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.OrganizationDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.OrganizationEntity;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrganizationMapper {
    OrganizationEntity toEntity(OrganizationDTO organizationDTO);

    OrganizationDTO toDTO(OrganizationEntity organizationEntity);
}
