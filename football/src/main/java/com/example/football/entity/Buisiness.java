package com.example.football.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "BUISINESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Buisiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "TIME_OPEN")
    private Time time_open;

    @NotNull
    @Column(name = "TIME_CLOSE")
    private Time time_close;

    @NotNull
    @Column(name = "DESCRIBES")
    private String describes;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LIKES")
    private long likes;

    @Column(name = "VIEWS")
    private long views;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "DATE_CREATE")
    private Date dateCreate;

    @Column(name = "IMG")
    private String img;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "buisiness",fetch = FetchType.LAZY)
    private List<Pitch> pitches;
}