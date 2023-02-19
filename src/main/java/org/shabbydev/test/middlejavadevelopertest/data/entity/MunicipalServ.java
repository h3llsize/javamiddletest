package org.shabbydev.test.middlejavadevelopertest.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class MunicipalServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String name;

    @OneToMany
    private Set<InterdepartmentalRequestEntity> interdepartmentalRequestEntities;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<InterdepartmentalDocument> interdepartmentalDocuments;

    @ManyToOne(fetch = FetchType.EAGER)
    private OrganizationEntity organizationEntity;

    @Column(nullable = false)
    private Instant time;
}
