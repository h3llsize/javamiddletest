package org.shabbydev.test.middlejavadevelopertest.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.shabbydev.test.middlejavadevelopertest.data.repo.OrganizationRepository;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class OrganizationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String inn;

    @Column(nullable = false)
    private String subdivision;

    @Column(nullable = false, unique = true)
    private String kpp;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<UserEntity> staff = new HashSet<>();

    public OrganizationEntity owner(UserEntity user) {
        this.owner = user;
        return this;
    }
}
