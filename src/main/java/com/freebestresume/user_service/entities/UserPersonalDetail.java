package com.freebestresume.user_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_pers_details")
public class UserPersonalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne()
    @JoinColumn(name="user_id")
    private User user;




}
