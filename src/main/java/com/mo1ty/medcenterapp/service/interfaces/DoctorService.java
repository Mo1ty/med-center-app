package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;

import java.util.List;

public interface DoctorService {

    List<Doctor> findByTreatmentTypeAndQualificationLevel(Treatment treatment, int qualificationLevel);

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    List<Doctor> findAll();

    Doctor findById(int doctorId);

    void deleteClient(int doctorId);

}
