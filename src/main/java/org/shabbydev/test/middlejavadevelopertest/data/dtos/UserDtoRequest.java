package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;

@Data
public class UserDtoRequest {
    private Long id;

    private String name;

    private OrganizationDTO organization;
}
