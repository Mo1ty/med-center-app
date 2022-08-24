package com.mo1ty.medcenterapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int treatmentId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "service_type_id")
    private TreatmentType treatmentType;

    @Column(name = "service_name")
    private String treatmentName;

    @Column(name = "price")
    private int price;

    @Column(name = "required_qualification")
    private int requiredQualification;

    @OneToMany(mappedBy = "serviceDone", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Visit> allVisits;

    public Treatment(TreatmentType treatmentTypeId, String treatmentName, int price, int requiredQualification) {
        this.treatmentType = treatmentTypeId;
        this.treatmentName = treatmentName;
        this.price = price;
        this.requiredQualification = requiredQualification;
    }
}
