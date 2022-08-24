package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    @Override
    public List<Doctor> findByServiceTypeAndQualiLevel(int serviceTypeId, int qualificationLevel) {
        return null;
    }

    @Override
    public void createNewClient(Doctor doctor) {

    }

    @Override
    public List<Doctor> findAll() {
        return null;
    }

    @Override
    public Doctor findById(int doctorId) {
        return null;
    }

    @Override
    public void updateClient(Doctor doctor, int doctorId) {

    }

    @Override
    public void deleteClient(int doctorId) {

    }
}
