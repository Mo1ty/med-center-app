package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "treatments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="treatment_id")
    private int treatmentId;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @Column
    private String name;

    @Column(name = "regular_price")
    private int regularPrice;

    @Column(name = "required_qualification")
    private int requiredQualification;

}
