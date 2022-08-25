package com.mo1ty.medcenterapp.prototype.support;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreatmentTypePrototype {

    public static TreatmentType makeTreatmentType(String name){

        // List<String> types = new ArrayList<>(Arrays.asList("Cleaning",  "Light Surgery", "Removing", "Plastics"));



        return new TreatmentType(name,"Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                "Duis vestibulum sollicitudin" +
                "lectus quis commodo. Nunc justo diam, finibus sed urna non, finibus pharetra elit.");

    }
}
