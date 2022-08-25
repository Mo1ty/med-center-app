package com.mo1ty.medcenterapp.prototype;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.ArrayList;
import java.util.List;

public class DoctorsPrototypes {

    public static List<Doctor> createDoctors(Address address, List<String> emails, List<TreatmentType> treatmentType){
        String[] firstNames = {"Dave", "Helena", "Olga", "Martin"};
        String[] lastNames = {"Stevens", "Miles", "Novakova", "Steinbauer"};

        int[] index = {0, 1, 0, 1};
        int[] qualificationLevel = {3, 2, 8, 9};

        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < firstNames.length && i < emails.size(); i++){
            doctors.add(new Doctor(firstNames[i], lastNames[i], emails.get(i),
                    address, treatmentType.get(index[i]), qualificationLevel[i]));
        }

        return doctors;
    }



}
