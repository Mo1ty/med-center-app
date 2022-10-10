package com.mo1ty.medcenterapp.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreatmentVO {

    private int treatmentId;
    private String treatmentName;
    private int price;
}
