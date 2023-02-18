package org.shabbydev.test.middlejavadevelopertest.data.mapper;

import org.mapstruct.Mapper;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalDocumentDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalDocument;

@Mapper(componentModel = "spring")
public interface InterdepartmentalDocumentMapper {
    InterdepartmentalDocumentDTO toDTO(InterdepartmentalDocument interdepartmentalDocument);

    InterdepartmentalDocument toEntity(InterdepartmentalDocumentDTO interdepartmentalDocumentDTO);
}
