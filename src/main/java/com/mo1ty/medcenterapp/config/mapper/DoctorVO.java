package com.mo1ty.medcenterapp.config.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String description;


}
