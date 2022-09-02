package com.mo1ty.medcenterapp.mapper;

import lombok.*;

import java.util.Date;

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
    private Date datetime;
}
