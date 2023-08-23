package com.example.football.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String phone;

    @NotNull
    private String password;

    private String name;


    private String address;

    private boolean status;

    @NotNull
    private String role;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Buisiness buisiness;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Rent> rentList;
}