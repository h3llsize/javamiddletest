package org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDtoRequest;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.OrganizationMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.UserRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class UserRequestMapperDecorator {

    @Autowired
    private OrganizationMapper organizationMapper;
    @AfterMapping
    public void toDto(UserEntity user, @MappingTarget UserDtoRequest userDtoRequest) {
        userDtoRequest.setOrganization(organizationMapper.toDTO(user.getOrganizationEntity()));
    }
}
