package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.List;

public interface TreatmentTypeService {

    TreatmentType createTreatmentType(TreatmentType treatmentType);

    TreatmentType updateTreatmentType(TreatmentType treatmentType);

    List<TreatmentType> findAll();

    TreatmentType findById(int treatmentTypeId);

    void deleteTreatmentType(int treatmentTypeId);

}
