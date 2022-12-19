package com.mo1ty.medcenterapp.entity.internal;

import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.ShiftType;
import com.mo1ty.medcenterapp.entity.Speciality;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private int doctorId;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Column
    private String description;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @Column(name = "qualification_level")
    private int qualificationLevel;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftType;

}
