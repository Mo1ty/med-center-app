package com.mo1ty.medcenterapp.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientVO {

    private int clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int addressId; // not mapped but used to retrieve from DB

}
