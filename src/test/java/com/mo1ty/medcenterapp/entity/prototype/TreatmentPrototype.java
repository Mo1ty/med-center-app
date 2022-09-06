package com.mo1ty.medcenterapp.entity.prototype;


import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mo1ty.medcenterapp.entity.prototype.DoctorPrototype.makeAllDoctors;

// First priority prototype. Further connection with Doctor prototype is required.
public class TreatmentPrototype {

    static ModelMapper modelMapper = new ModelMapper();

    public static Treatment makeCzechTreatment(Doctor czechDoctor){
        Treatment treatment = new Treatment();

        treatment.setTreatmentName("TREATMENT_CZECH");
        treatment.setPrice(400);
        treatment.setDoctors(Collections.singletonList(czechDoctor));

        return treatment;
    }

    public static Treatment makeGermanTreatment(Doctor germanDoctor){
        Treatment treatment = new Treatment();

        treatment.setTreatmentName("TREATMENT_GERMAN");
        treatment.setPrice(700);
        treatment.setDoctors(Collections.singletonList(germanDoctor));

        return treatment;
    }

    public static Treatment makeFrenchTreatment(Doctor frenchDoctor){
        Treatment treatment = new Treatment();

        treatment.setTreatmentName("TREATMENT_FRENCH");
        treatment.setPrice(550);
        treatment.setDoctors(Collections.singletonList(frenchDoctor));

        return treatment;
    }

    public static Treatment makeDummyTreatment(){
        Treatment treatment = new Treatment();

        treatment.setTreatmentName("TREATMENT_CZECH");
        treatment.setPrice(2000);

        return treatment;
    }

    public static List<Treatment> makeAllTreatments(List<Doctor> doctors){
        ArrayList<Treatment> arrayList = new ArrayList<>();
        arrayList.add(makeCzechTreatment(doctors.get(0)));
        arrayList.add(makeGermanTreatment(doctors.get(1)));
        arrayList.add(makeFrenchTreatment(doctors.get(2)));
        return arrayList;
    }

    public static List<TreatmentVO> mapAllTreatments(List<Doctor> doctors){
        List<Treatment> treatments = makeAllTreatments(doctors);
        return treatments.stream()
                .map(treatment -> modelMapper.map(treatment, TreatmentVO.class))
                .collect(Collectors.toList());
    }
}
