package com.mo1ty.medcenterapp.mapper;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private int qualificationLevel;
    private List<VisitVO> allVisits;
}
