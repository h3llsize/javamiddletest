package org.shabbydev.test.middlejavadevelopertest.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class AuthorizationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Id
    private Long Id;

    @Column(unique = true)
    private String hash;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity user;
}
