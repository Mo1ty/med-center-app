package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
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
    //@OneToOne(cascade = CascadeType.ALL) referencedColumnName = "email"
    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    private Address address;

    /*@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "treatment_type_id")
    private TreatmentType treatmentType;*/

    @OneToMany(mappedBy = "doctorAccepted", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Visit> allVisits;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="treatments_has_doctors",
            joinColumns = @JoinColumn(name="doctor_id"),
            inverseJoinColumns = @JoinColumn(name="treatment_id")
    )
    private List<Treatment> allTreatments;

    public Doctor(String firstName, String lastName, String email,
                  Address addressId, int qualificationLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = addressId;
    }

    public Doctor(int id, String firstName, String lastName, String email,
                  Address addressId, int qualificationLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = addressId;
    }

    public List<Visit> allPendingVisits(Date date, Time time){
        List<Visit> pendingVisits = new ArrayList<>();

        for(Visit visit : this.allVisits){
            if(!(date.after(visit.getDate()))){
                pendingVisits.add(visit);
            }
        }

        return pendingVisits;
    }
}
