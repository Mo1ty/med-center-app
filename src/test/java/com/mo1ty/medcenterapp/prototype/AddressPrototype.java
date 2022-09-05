package com.mo1ty.medcenterapp.prototype;

import com.mo1ty.medcenterapp.entity.Address;
import org.assertj.core.util.Arrays;

import java.util.ArrayList;
import java.util.List;

// First priority Prototype. Is required to create Client & Doctor prototype.
public class AddressPrototype {

    public static Address makeCzechAddress(){
        return new Address("Brno", "TESTCODE_CZ", "TESTSTREET_CZ", 312);
    }

    public static Address makeGermanAddress(){
        return new Address("Dresden", "TESTCODE_DE", "TESTSTREET_DE", 213);
    }

    public static Address makeFrenchAddress(){
        return new Address("Lyon", "TESTCODE_FR", "TESTSTREET_DE", 781);
    }

    public static List<Address> makeAllAddresses(){
        ArrayList<Address> addressArrayList = new ArrayList<>();
        addressArrayList.add(makeCzechAddress());
        addressArrayList.add(makeGermanAddress());
        addressArrayList.add(makeFrenchAddress());
        return addressArrayList;
    }

}
