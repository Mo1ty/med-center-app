package com.mo1ty.medcenterapp.config.mapper;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientVO {

    private int id;
    private int contactId;
    private int totalSpent;
    private int loyaltyLevelId;

}
