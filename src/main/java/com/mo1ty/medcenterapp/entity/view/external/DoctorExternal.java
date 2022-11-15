package com.mo1ty.medcenterapp.entity.view.external;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "doctors_public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorExternal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name="speciality_name")
    private String specialityName;
}
