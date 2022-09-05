package com.mo1ty.medcenterapp.mapper;

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
    private int addressId; // not mapped but used to retrieve from DB


}
