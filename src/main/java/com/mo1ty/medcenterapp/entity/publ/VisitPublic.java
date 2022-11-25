package com.mo1ty.medcenterapp.entity.publ;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Treatment;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="visits")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VisitPublic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id")
    private int visitId;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "doctor_id")
    private DoctorPublic doctorPublic;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @Column
    private int totalPrice;

    @Column
    private Timestamp datetime;

    @Column
    private String illnessDescription;

}
