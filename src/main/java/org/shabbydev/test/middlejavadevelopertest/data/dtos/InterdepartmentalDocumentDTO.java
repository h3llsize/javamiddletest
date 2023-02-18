package org.shabbydev.test.middlejavadevelopertest.data.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class InterdepartmentalDocumentDTO {
    private Long Id;

    private byte[] data;

    private String name;

    private Instant endTime;

}
