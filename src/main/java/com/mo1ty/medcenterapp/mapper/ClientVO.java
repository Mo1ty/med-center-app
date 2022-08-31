package com.mo1ty.medcenterapp.mapper;

import com.mo1ty.medcenterapp.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
    private int addressId;
    private List<Integer> allVisitsIds;

}
