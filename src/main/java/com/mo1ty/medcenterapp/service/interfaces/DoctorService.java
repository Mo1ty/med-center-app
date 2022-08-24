package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> findByServiceTypeAndQualiLevel(int serviceTypeId, int qualificationLevel);

    void createNewClient(Doctor doctor);

    List<Doctor> findAll();

    Doctor findById(int doctorId);

    void updateClient(Doctor doctor, int doctorId);

    void deleteClient(int doctorId);

}
