package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.MunicipalServDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator.MunicipalServMapperDecorator;

@Mapper(componentModel = "spring", uses = MunicipalServMapperDecorator.class)
public interface MunicipalServMapper {

    @Mapping(target = "interdepartmentalRequestEntities[].type", ignore = true )
    MunicipalServ toEntity(MunicipalServDTO municipalServDTO);
    MunicipalServDTO toDTO(MunicipalServ municipalServ);
}
