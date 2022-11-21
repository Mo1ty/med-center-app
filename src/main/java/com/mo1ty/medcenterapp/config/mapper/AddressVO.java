package com.mo1ty.medcenterapp.config.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {

    private int id;
    private String city;
    private String postalCode;
    private String street;
    private int houseNumber;

}
