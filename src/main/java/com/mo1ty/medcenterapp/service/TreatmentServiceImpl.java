package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> findByName(String name) {

        return treatmentRepository.findByTreatmentName(name);
    }

    @Override
    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment updateTreatment(Treatment treatment) {
        Optional<Treatment> result = treatmentRepository.findById(treatment.getTreatmentId());

        if(result.isPresent()){
            treatmentRepository.save(treatment);
            return treatment;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment findById(int treatmentId) {
        Optional<Treatment> result = treatmentRepository.findById(treatmentId);

        return result.orElse(null);
    }

    @Override
    public void deleteTreatment(int treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }
}
