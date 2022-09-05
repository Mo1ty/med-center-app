package com.mo1ty.medcenterapp.prototype;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;

import java.util.ArrayList;
import java.util.List;

// First priority Prototype. Is required to create Doctor prototype.
public class InternalLoginPrototype {

    public static InternalLogin createCzechInternalLogin(String email){
        return new InternalLogin(email,
                "{plain}batman", "ROLE_DOCTOR");
    }

    public static InternalLogin createGermanInternalLogin(String email){
        return new InternalLogin(email,
                "{plain}batman", "ROLE_DOCTOR");
    }

    public static InternalLogin createFrenchInternalLogin(String email){
        return new InternalLogin(email,
                "{plain}batman", "ROLE_DOCTOR");
    }

    public static List<InternalLogin> makeAllLogins(List<String> emails){
        ArrayList<InternalLogin> arrayList = new ArrayList<>();
        arrayList.add(createCzechInternalLogin(emails.get(0)));
        arrayList.add(createGermanInternalLogin(emails.get(1)));
        arrayList.add(createFrenchInternalLogin(emails.get(2)));
        return arrayList;
    }
}
