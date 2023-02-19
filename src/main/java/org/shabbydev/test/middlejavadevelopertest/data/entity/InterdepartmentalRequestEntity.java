package org.shabbydev.test.middlejavadevelopertest.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class InterdepartmentalRequestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    private InterdepartmentalTypeEntity type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant date;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @Column(nullable = false)
    private int status;

    public InterdepartmentalRequestEntity status(int status) {
        this.status = status;
        return this;
    }
}
