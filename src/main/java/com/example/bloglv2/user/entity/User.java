package com.example.bloglv2.user.entity;

import com.example.bloglv2.global.entity.Timestamped;
import jakarta.persistence.*;

@Entity(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
