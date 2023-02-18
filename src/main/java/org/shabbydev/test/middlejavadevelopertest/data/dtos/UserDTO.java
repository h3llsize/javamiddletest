package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private Long Id;

    private String email;

    private String name;

    private String surname;

    private OrganizationDTO organizationDTO;

    private String password;
}
