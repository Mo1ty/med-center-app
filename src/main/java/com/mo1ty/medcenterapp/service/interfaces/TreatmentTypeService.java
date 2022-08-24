package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.List;

public interface TreatmentTypeService {

    void createOrUpdateTreatmentType(TreatmentType treatmentType);

    List<TreatmentType> findAll();

    TreatmentType findById(int treatmentTypeId);

    void deleteClient(int treatmentTypeId);

}
