package org.shabbydev.test.middlejavadevelopertest.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class InterdepartmentalDocument {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;

    @Lob
    private byte[] data;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private MunicipalServ interdepartmentalManager;

}
