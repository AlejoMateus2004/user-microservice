package com.salessytem.usermicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`User`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "userName", unique = true, nullable = false )
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

}
