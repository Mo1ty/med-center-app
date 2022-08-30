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

    @Column(name = "treatment_name")
    private String treatmentName;

    @Column(name = "price")
    private int price;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="treatments_has_doctors",
            joinColumns = @JoinColumn(name="treatment_id"),
            inverseJoinColumns = @JoinColumn(name="doctor_id")
    )
    private List<Doctor> doctors;

    public Treatment(String treatmentName, int price, int requiredQualification) {
        this.price = price;
    }
}
