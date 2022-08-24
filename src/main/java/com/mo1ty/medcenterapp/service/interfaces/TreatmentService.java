package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Treatment;

import java.util.List;

public interface TreatmentService {

    void createOrUpdateTreatment(Treatment treatment);

    List<Treatment> findAll();

    Treatment findById(int treatmentId);

    void deleteTreatment(int treatmentId);

}
