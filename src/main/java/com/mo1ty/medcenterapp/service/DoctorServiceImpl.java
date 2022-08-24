package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findByServiceTypeAndQualiLevel(int serviceTypeId, int qualificationLevel) {

        List<Doctor> result =
                doctorRepository.findByServiceTypeAndQualiLevel(serviceTypeId, qualificationLevel);

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new RuntimeException();
        }

        return result;

    }

    @Override
    public void createOrUpdateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findAll() {

        List<Doctor> result = doctorRepository.findAll();

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new RuntimeException();
        }

        return result;
    }

    @Override
    public Doctor findById(int doctorId) {

        Optional<Doctor> result = doctorRepository.findById(doctorId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            // add DataNotFoundException later
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteClient(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }
}
