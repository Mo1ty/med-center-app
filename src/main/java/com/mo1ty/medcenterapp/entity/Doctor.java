package com.mo1ty.medcenterapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // email is a username
    // provide check if is an actual email
    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @Column(name = "qualification_level")
    private int qualificationLevel;

    @OneToMany(mappedBy = "doctorAccepted", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Visit> allVisits;

    public Doctor(String firstName, String lastName, String email,
                  Address addressId, ServiceType serviceTypeId, int qualificationLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = addressId;
        this.serviceType = serviceTypeId;
        this.qualificationLevel = qualificationLevel;
    }
}
