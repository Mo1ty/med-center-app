package com.mo1ty.medcenterapp.entity.publ;

import com.mo1ty.medcenterapp.entity.ShiftType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "doctors_public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorPublic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private int doctorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name="speciality_name")
    private String specialityName;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftType;
}
