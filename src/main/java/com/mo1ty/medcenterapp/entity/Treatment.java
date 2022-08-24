package com.mo1ty.medcenterapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    private int treatmentId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "treatment_type_id")
    private TreatmentType treatmentType;

    @Column(name = "treatment_name")
    private String treatmentName;

    @Column(name = "price")
    private int price;

    @Column(name = "required_qualification")
    private int requiredQualification;

    public Treatment(TreatmentType treatmentTypeId, String treatmentName, int price, int requiredQualification) {
        this.treatmentType = treatmentTypeId;
        this.treatmentName = treatmentName;
        this.price = price;
        this.requiredQualification = requiredQualification;
    }
}
