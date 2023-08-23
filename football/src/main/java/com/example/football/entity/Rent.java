package com.example.football.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "DATE_RENT")
    private Date dateRent;

    @NotNull
    @Column(name = "TIME_START")
    private Time timeStart;

    @NotNull
    @Column(name = "TIME_END")
    private Time timeEnd;

    @NotNull
    @Column(name = "DATE_CREATE")
    private Date dateCreate;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pitch_id")
    private Pitch pitch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
