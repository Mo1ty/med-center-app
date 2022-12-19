package com.mo1ty.medcenterapp.config.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitVO {

    private int visitId;
    private int treatmentDoneId;
    private int clientVisitedId;
    private int doctorAcceptedId;
    private int price;
    private Date datetime;
    private String illnessDescription;
}
