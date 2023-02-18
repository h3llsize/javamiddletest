package org.shabbydev.test.middlejavadevelopertest.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class InterdepartmentalTypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;
    private String Type;
}
