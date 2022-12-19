package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speciality_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
