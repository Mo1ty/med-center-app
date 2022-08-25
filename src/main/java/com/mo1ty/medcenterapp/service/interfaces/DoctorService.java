package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;

import java.util.List;

public interface DoctorService {

    List<Doctor> findByTreatmentTypeAndQualificationLevel(TreatmentType treatmentTypeId, int qualificationLevel);

    void createOrUpdateDoctor(Doctor doctor);

    List<Doctor> findAll();

    Doctor findById(int doctorId);

    void deleteClient(int doctorId);

}
