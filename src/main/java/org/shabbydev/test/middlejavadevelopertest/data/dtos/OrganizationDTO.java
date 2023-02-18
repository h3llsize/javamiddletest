package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class OrganizationDTO {
    private Long Id;

    private String name;

    private String INN;

    private String subdivision;

    private String KPP;

    private UserDTO owner;

    private Set<UserDTO> staff;
}
