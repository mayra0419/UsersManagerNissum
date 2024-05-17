package com.mayra0419.usersmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Column(name = "number")
    private String number;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "contrycode")
    private String contrycode;

}
