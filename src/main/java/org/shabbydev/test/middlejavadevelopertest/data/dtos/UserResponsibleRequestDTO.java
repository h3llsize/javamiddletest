package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserResponsibleRequestDTO {
    private OrganizationDTO organizationDTO;

    private List<InterdepartmentalRequestDTO> interdepartmentalRequestDTO;
}
