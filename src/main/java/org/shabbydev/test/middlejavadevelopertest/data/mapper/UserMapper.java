package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.UserDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;

@Mapper(componentModel = "spring", uses = OrganizationMapper.class)
public interface UserMapper {
    UserEntity toEntity(UserDTO userDTO);
    UserDTO toDTO(UserEntity user);
}
