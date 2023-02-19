package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class OrganizationDTO {
    private Long Id;

    private String name;

    private String inn;

    private String subdivision;

    private String kpp;

    private UserDTO owner;

    private Set<UserDTO> staff;
}
