package com.freebestresume.user_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_other_pers_details")
public class UserOtherPersonalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name="user_pers_detail_id", nullable = false)
    private Long userPersonalDetailId;








}
