package com.mo1ty.medcenterapp.mapper;

import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoyaltyLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
