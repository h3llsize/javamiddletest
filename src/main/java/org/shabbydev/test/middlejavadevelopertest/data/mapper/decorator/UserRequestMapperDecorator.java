package org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDtoRequest;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;

@Mapper(componentModel = "spring")
public class UserRequestMapperDecorator {
    @AfterMapping
    public void toDto(UserEntity user, @MappingTarget UserDtoRequest userDtoRequest) {
        userDtoRequest.setOrganization(user.getOrganizationEntity().getName());
    }
}
