package com.mo1ty.medcenterapp.entity.publ;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="vacation_reasons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VacationReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reason_id")
    private int reasonId;

    @Column(name = "short_name")
    private String name;

    @Column
    private String description;

}
