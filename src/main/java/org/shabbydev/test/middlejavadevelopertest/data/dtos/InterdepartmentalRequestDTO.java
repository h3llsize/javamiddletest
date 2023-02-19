package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class InterdepartmentalRequestDTO {
    private Long Id;

    private InterdepartmentalTypeDTO type;

    private String name;

    private Instant date;

    private UserDtoRequest user;

    private MunicipalServDTO municipalServ;

    //0 - wait for subscribe
    //1 - subscribed, in doing
    private int status;
}
