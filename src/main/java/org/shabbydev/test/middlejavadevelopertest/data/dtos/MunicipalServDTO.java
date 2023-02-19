package org.shabbydev.test.middlejavadevelopertest.data.dtos;


import lombok.Data;
import java.util.Set;

@Data
public class MunicipalServDTO {

    private Long id;

    private String name;

    private Set<InterdepartmentalRequestDTO> interdepartmentalRequestEntities;

    private Set<InterdepartmentalDocumentDTO> interdepartmentalDocuments;

    private OrganizationDTO organization;

    private String time;
}
