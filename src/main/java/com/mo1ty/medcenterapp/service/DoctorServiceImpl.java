package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findByTreatmentTypeAndQualificationLevel(Treatment treatment, int qualificationLevel) {
        /*
        List<Doctor> result =
                doctorRepository.findByTreatmentTypeAndQualificationLevel(treatmentType, qualificationLevel);

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new DataNotFoundException
                    ("There are no doctors with Treatment Type: " + treatmentType.getTreatmentType()
                    + "and qualification level: " + qualificationLevel);
        }

         */
        return null;

    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Optional<Doctor> result = doctorRepository.findById(doctor.getId());

        if(result.isPresent()){
            doctorRepository.save(doctor);
            return doctor;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Doctor> findAll() {

        List<Doctor> result = doctorRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No doctors were found in the table!");
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
            throw new DataNotFoundException("Doctor with this id was not found!");
        }

    }

    @Override
    public void deleteClient(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }
}
