package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authorization {
    private String token;
}
