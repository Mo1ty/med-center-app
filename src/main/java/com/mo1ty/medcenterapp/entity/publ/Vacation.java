package com.mo1ty.medcenterapp.entity.publ;

import com.mo1ty.medcenterapp.entity.internal.Doctor;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="vacations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vacation_id")
    private int vacationId;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name="reason_id")
    private VacationReason vacationReason;

    @Column(name="starting_time")
    private Timestamp starts_on;

    @Column(name="ending_time")
    private Timestamp ends_on;
}
