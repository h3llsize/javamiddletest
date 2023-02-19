package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalRequestDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalRequestEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator.InterdepartmentalRequestMapperDecorator;

@Mapper(componentModel = "spring", uses = InterdepartmentalRequestMapperDecorator.class)
public interface InterdepartmentalRequestMapper {

    @Mapping(target = "user", ignore = true)
    InterdepartmentalRequestDTO toDTO(InterdepartmentalRequestEntity interdepartmentalRequestEntity);
    @Mapping(target = "author", ignore = true)
    InterdepartmentalRequestEntity toEntity(InterdepartmentalRequestDTO interdepartmentalRequestDTO);

}
