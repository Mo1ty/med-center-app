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
    private int treatmentDoneId;
    private int clientVisitedId;
    private int doctorAcceptedId;
    private Date date;
    private Time time;
}
