package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDtoRequest;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;

@Mapper(componentModel = "spring", uses = OrganizationMapper.class)
public interface UserRequestMapper {
    @Mapping(target = "organization", ignore = true)
    UserEntity toEntity(UserDtoRequest userDTO);

    @Mapping(target = "organization", ignore = true)
    UserDtoRequest toDto(UserEntity user);
}
