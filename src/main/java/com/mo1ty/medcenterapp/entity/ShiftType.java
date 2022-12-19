package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="shift_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shift_type_id")
    private int shiftId;

    @Column(name="start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;
}
