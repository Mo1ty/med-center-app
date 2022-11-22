package com.mo1ty.medcenterapp.config.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactVO {

    private int id;

    private int loginDataId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private int addressId;

}
