package com.freebestresume.user_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @OneToOne(mappedBy = "User", cascade = CascadeType.ALL)
    private UserPersonalDetail userPersonalDetail;



}
