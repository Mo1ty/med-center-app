package com.mo1ty.medcenterapp.mapper;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VisitVO {

    private int visitId;
    private int treatmentDone;
    private int clientVisited;
    private int doctorAccepted;
    private Date date;
    private Time time;
}