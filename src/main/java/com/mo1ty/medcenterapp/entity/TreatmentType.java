package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="treatment_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreatmentType {

    // before continuing, check how are joins and relations shown in JPA & Java itself

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="treatment_type_id")
    private int treatmentTypeId;

    @Column(name="treatment_type")
    private String treatmentType;

    @Column(name="description")
    private String description;


    public TreatmentType(String treatmentType, String description) {
        this.treatmentType = treatmentType;
        this.description = description;
    }
}
