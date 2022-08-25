package com.mo1ty.medcenterapp.prototype.support;

import com.mo1ty.medcenterapp.entity.Address;

public class AddressPrototype {

    public static Address makeAddress(){
        return new Address("Brno", "TESTCODE", "TESTSTREET", 312);
    }

}
