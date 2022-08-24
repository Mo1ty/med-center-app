package com.mo1ty.medcenterapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id")
    private int visitId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="service_id")
    private Treatment treatmentDone;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="client_id")
    private Client clientVisited;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="doctor_id")
    private Doctor doctorAccepted;

    @Column(name="date")
    private Date date;

    @Column(name="time")
    private Time time;

    public Visit(Treatment treatmentDone, Client clientVisited, Doctor doctorAccepted, Date date, Time time) {
        this.treatmentDone = treatmentDone;
        this.clientVisited = clientVisited;
        this.doctorAccepted = doctorAccepted;
        this.date = date;
        this.time = time;
    }
}
