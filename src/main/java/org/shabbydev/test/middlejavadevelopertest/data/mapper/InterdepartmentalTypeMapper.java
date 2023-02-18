package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalTypeDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalTypeEntity;

@Mapper(componentModel = "spring")
public interface InterdepartmentalTypeMapper {
    InterdepartmentalTypeEntity toEntity(InterdepartmentalTypeDTO interdepartmentalTypeDTO);

    InterdepartmentalTypeDTO toDTO(InterdepartmentalTypeEntity interdepartmentalTypeEntity);
}
