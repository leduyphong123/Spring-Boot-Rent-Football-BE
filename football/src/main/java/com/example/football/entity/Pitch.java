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
public class Pitch {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private int min_people;

    @NotNull
    private int max_people;

    @NotNull
    private long price;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buisiness_id")
    private Buisiness buisiness;

    @OneToMany(mappedBy = "pitch",fetch = FetchType.LAZY)
    private List<Rent> rentList;
}
