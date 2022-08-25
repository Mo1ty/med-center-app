package com.mo1ty.medcenterapp.prototype.support;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;

import java.util.ArrayList;

public class ClientPrototype {

    public static Client makeClient(String firstName){

        return new Client(firstName, "TestSurnamov", "test_surname@email.com", "{plain}batman", new Address());
    }

}
