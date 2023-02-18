package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalTypeEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.MunicipalServ;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
public class InterdepartmentalRequestDTO {
    private Long Id;

    private InterdepartmentalTypeDTO type;

    private String name;

    private Instant date;

    private UserDTO userDTO;

    private MunicipalServDTO municipalServ;
}
