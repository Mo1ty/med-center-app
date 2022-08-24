package com.mo1ty.medcenterapp.prototype;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreatmentPrototype {

    public static Treatment makeTreatment(int id, TreatmentType treatmentType, String name){
        return new Treatment(id, treatmentType, name,600, 8);
    }
}
