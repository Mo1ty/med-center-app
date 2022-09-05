package com.mo1ty.medcenterapp.prototype;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Requires Address and proper InternalLogin. Must be implemented after mentioned prototypes.
public class DoctorPrototype {

    public static Doctor makeCzechDoctor(String czechEmail, Address address){
        return new Doctor("Jan", "Novotny",
                czechEmail, address);
    }

    public static Doctor makeGermanDoctor(String germanEmail, Address address){
        return new Doctor("Hans", "Muller",
                germanEmail, address);
    }

    public static Doctor makeFrenchDoctor(String frenchEmail, Address address){
        return new Doctor("Emmanuel", "Garnier",
                frenchEmail, address);
    }

    public static Doctor makeDummyDoc(String email, Address address, Treatment treatment){
        Doctor doctor = new Doctor("Jan", "Novotny", email, address);
        doctor.setAllTreatments(Collections.singletonList(treatment));
        return doctor;
    }

    public static List<Doctor> makeAllDoctors(List<String> emails, List<Address> addresses){
        ArrayList<Doctor> arrayList = new ArrayList<>();
        arrayList.add(makeCzechDoctor(emails.get(0), addresses.get(0)));
        arrayList.add(makeGermanDoctor(emails.get(1), addresses.get(1)));
        arrayList.add(makeFrenchDoctor(emails.get(2), addresses.get(2)));
        return arrayList;
    }
}
