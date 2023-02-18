package org.shabbydev.test.middlejavadevelopertest.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.OrganizationDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_data")
@Getter
@Setter
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private Set<InterdepartmentalRequestEntity> requestEntities = new HashSet<>();

    @OneToOne
    private OrganizationEntity organizationEntity;

    public UserEntity organization(OrganizationEntity organization) {
        this.organizationEntity = organization;
        return this;
    }
}
